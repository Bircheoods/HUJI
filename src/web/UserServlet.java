package web;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import entity.CheckPerson;
import entity.Page;
import entity.Person;
import entity.User;
import service.CheckPersonService;
import service.PersonService;
import service.UserService;
import service.impl.CheckPersonServiceImpl;
import service.impl.PersonServiceImpl;
import service.impl.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @description
 * @user summerHouAnNing
 * @creatTime 2021/4/24--4:33
 */
public class UserServlet extends BaseServlet{

    private UserService userService = new UserServiceImpl();
    private PersonService personService = new PersonServiceImpl();
    private CheckPersonService checkPersonService = new CheckPersonServiceImpl();
    /**
    *@description: 用户登录
    *@author: summerHouAnNing
    *@creatTime: 2021/4/24--13:01
    *@Param:
    *@return:
    **/
    public void userLogin(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取用户登录提交信息
        String userIdNum =  req.getParameter("userIdNum");
        String password = req.getParameter("userPassword");
        String role = req.getParameter("userRole");

        HttpSession session = req.getSession();


        //定义userRole传给前端用于在导航栏显示
        String  userRole = "0".equals(role) ? "普通用户":"管理人员";
        //使用request存储数据
        session.setAttribute("role",userRole);
        //获取从数据库中查询出来的数据
        User user = userService.queryUserByNumAndPsw(userIdNum, password,Integer.parseInt(role));
//        System.out.println(user.toString());

//        if (attribute != null){
            if (user != null ){//如果user不为空，说明登陆成功

                //根据userId判断当前人员是否死亡
                Person person = personService.queryPersonById(user.getUserId());
               if (person.getStatus() == 0){
                   //将user相关信息存入到session，方便后期使用
                   session.setAttribute("user",user);
                   //请求转发到user_index.jsp页面
                   req.setAttribute("message","登录成功");
//                //根据角色权限进行页面跳转
                if("0".equals(role)){//跳转到普通用户页面
                    req.getRequestDispatcher("/noticeServlet?action=queryOneNoticeByLimitToUser&pageNo=1&pageSize=1").forward(req,resp);
                    return;
                }else{//跳转到管理员页面
                   req.getRequestDispatcher("/noticeServlet?action=queryOneNoticeByLimit&pageNo=1&pageSize=1").forward(req,resp);
                   return;
                }
               }else{
                   //System.out.println("登陆失败");
                   req.setAttribute("message","当前人员已经被注销，无法登录");
                   //转发到首页并提示用户信息输入有误或当前帐号与身份不匹配
                   req.getRequestDispatcher("/index.jsp").forward(req,resp);
                   return;
               }
            }else {//登陆失败
                //System.out.println("登陆失败");
                req.setAttribute("message","当前帐号与身份不符或输入的账号密码有误，请重新登录");
                //转发到首页并提示用户信息输入有误或当前帐号与身份不匹配
                req.getRequestDispatcher("/index.jsp").forward(req,resp);
                return;
            }
//        }



    }

    /**
    *@description: 用户注册
    *@author: summerHouAnNing
    *@creatTime: 2021/4/24--13:01
    *@Param:
    *@return:
    **/
    public void userRegister(HttpServletRequest req,HttpServletResponse resp) throws Exception {
        //获取用户提交的信息
        String identityNum = req.getParameter("identityNum");
        String userPassword = req.getParameter("userPassword");
        String suerUserPassword = req.getParameter("suerUserPassword");
        String userPhone = req.getParameter("userPhone");
        String userEmail = req.getParameter("userEmail");
        String userProblem = req.getParameter("userProblem");
        String userAnswer = req.getParameter("userAnswer");

        //判断两次密码是否相等
        if (!userPassword.equals(suerUserPassword)){
            req.setAttribute("message","两次密码输入不一致，请重新确认");
            req.getRequestDispatcher("/user/register.jsp").forward(req,resp);
            return;
        }else {
           //根据身份证号查询person，判断注册人员是否已经进行过户籍登记
            Person person = personService.queryOnePersonByIdNum(identityNum);
            System.out.println(person);
            if (person != null){

                CheckPerson person1 = checkPersonService.queryCheckUserById(person.getId());
                System.out.println(person1);
                if (person1.getCheckStatus() == 0){
                    req.setAttribute("message","当前人员正在审核中，暂时无法注册，请耐心等待");
                    req.getRequestDispatcher("/user/register.jsp").forward(req,resp);
                    return;
                }

                //获取当前日期并
                Date date=new Date();//获取一个java.util包下的Date对象
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String s = format.format(date);
                //说明已经进行过户籍登记，向user表中插入数据
                //根据personID查询当前身份证号是否已经进行过注册
                User user1 = userService.queryOneUser(person.getId());
                if (user1 != null){
                    req.setAttribute("message","当前身份证号已经被使用，不可重复注册");
                    req.getRequestDispatcher("/user/register.jsp").forward(req,resp);
                    return;
                }else {
                    User user = new User();
                    //封装user对象
                    user.setEmail(userEmail);
                    user.setUserAnswer(userAnswer);
                    user.setUserId(person.getId());
                    user.setUserPassword(userPassword);
                    user.setPhone(userPhone);
                    user.setUserProblem(userProblem);
                    user.setPower(0);
                    user.setRegisteredTime(s);

                    //调用相应的方法插入数据
                    int i = userService.insertOneUser(user);

                    if (i != -1){//说明插入成功
                        //请求转发到系统首页
                        req.setAttribute("message","注册成功，欢迎使用！");
                        req.getRequestDispatcher("/index.jsp").forward(req,resp);
                        return;
                    }else {
                        //插入失败，请求转发到当前页面
                        req.setAttribute("message","人员添加失败！");
                        //请求转发到当前页面‘
                        req.getRequestDispatcher("/user/register.jsp");
                        return;
                    }
                }

            }
            else {//当前人员没有进行过户籍登记
                req.setAttribute("message","当前人员还未进行户籍登记，请先登记之后进行注册");
                req.getRequestDispatcher("/user/register.jsp").forward(req,resp);
                return;
            }
        }


    }

    /**
    *@description: 用户找回密码功能
    *@author: summerHouAnNing
    *@creatTime: 2021/4/26--6:38
    *@Param:
    *@return:
    **/
    public void findPassword(HttpServletRequest req,HttpServletResponse resp)throws Exception{
        //获取用户提交的数据
        String userProblem = req.getParameter("userProblem");
        String userAnswer = req.getParameter("userAnswer");
        String newPassword = req.getParameter("newPassword");
        String sureNewPassword = req.getParameter("sureNewPassword");

        System.out.println("用户提交的问题为："+userProblem);
        System.out.println("用户提交的答案为："+userAnswer);
        System.out.println("用户提交的新密码为："+newPassword);
        System.out.println("用户提交的确认密码为："+sureNewPassword);

        //判断两次密码输入是否一致
        if (!newPassword.equals(sureNewPassword)){
            req.setAttribute("message","两次密码输入不一致，请重新确认");
            req.getRequestDispatcher("/user/findPassword.jsp").forward(req,resp);
            return;
        }else {
            //根据问题和答案查询user
            User user = userService.queryUserByProblemAndAnswer(userProblem, userAnswer);
            if (user != null){ //找到之后，修改密码
                int i = userService.updatePassword(newPassword, user.getId());
                if (i >0 ){//修改成功
                    req.setAttribute("message","密码修改成功");
                    req.getRequestDispatcher("/index.jsp").forward(req,resp);
                    return;
                }else {//修改失败
                    req.setAttribute("message","密码修改失败");
                    req.getRequestDispatcher("/user/findPassword.jsp").forward(req,resp);
                    return;
                }
            }else {//没有找到，提示用户密保问题不对
                req.setAttribute("message","两次密码输入不一致，请重新确认");
                req.getRequestDispatcher("/user/findPassword.jsp").forward(req,resp);
                return;
            }
        }

    }
    /**
    *@description: 查找所有人员信息
    *@author: summerHouAnNing
    *@creatTime: 2021/4/30--23:27
    *@Param:
    *@return:
    **/
    public void queryUserByLimit(HttpServletRequest req,HttpServletResponse resp)throws Exception{
        int pageNo = Integer.parseInt(req.getParameter("pageNo"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        String userName = req.getParameter("userName");
        userName = userName == null ? "" : userName;


        Page<User> page = userService.queryAllUser(pageNo, pageSize, userName);


        System.out.println("查询的人员为"+page.getItems());
        List<Person> personList = new ArrayList<>();
        for (User user : page.getItems()) {
            //处理密码
            String s = user.getUserPassword().replaceAll("^[1-9]\\d*$", "******");
            user.setUserPassword(s);
            //处理手机号
            StringBuilder phone = new StringBuilder( user.getPhone()).replace(3,7,"****");
            user.setPhone(phone.toString());
            //处理邮箱
            String email = user.getEmail().replaceAll("^[1-9]\\d*$","*");
            user.setEmail(email);

            //根据id查人员姓名和身份证号
            Person person = personService.queryPersonById(user.getUserId());
            //处理身份证号
            StringBuilder idNum = new StringBuilder(person.getIdentityNum()).replace(6,14,"********");
            person.setIdentityNum(idNum.toString());
            //存放person信息
            personList.add(person);
        }
        req.setAttribute("user",page);
        req.setAttribute("person",personList);
        req.getRequestDispatcher("/admin/manager.jsp").forward(req,resp);
    }
    /**
    *@description: 设为管理员
    *@author: summerHouAnNing
    *@creatTime: 2021/5/4--7:46
    *@Param:
    *@return:
    **/
    public void updateUserPower(HttpServletRequest req,HttpServletResponse resp)throws Exception{
        int id = Integer.parseInt(req.getParameter("id"));
        int i = userService.updateUserPower(id);
        if (i > 0){
            req.setAttribute("message","修改成功");
        }else {
            req.setAttribute("message","修改失败");
        }
        req.getRequestDispatcher("/userServlet?action=queryUserByLimit&pageNo=1&pageSize=10").forward(req,resp);
    }
    /**
    *@description: 删除普通用户
    *@author: summerHouAnNing
    *@creatTime: 2021/5/4--7:53
    *@Param:
    *@return:
    **/
    public void deleteUserById(HttpServletRequest req,HttpServletResponse resp)throws Exception{
        int id = Integer.parseInt(req.getParameter("id"));
        int i = userService.deleteUserById(id);

        if (i > 0){
            req.setAttribute("message","删除成功");
        }else {
            req.setAttribute("message","删除失败");
        }
        req.getRequestDispatcher("/userServlet?action=queryUserByLimit&pageNo=1&pageSize=10").forward(req,resp);
    }
    /**
    *@description: 查询当前登陆人员的基本信息
    *@author: summerHouAnNing
    *@creatTime: 2021/5/6--11:24
    *@Param:
    *@return:
    **/
    public void queryNowPerson(HttpServletRequest req,HttpServletResponse resp)throws Exception{
        User user = (User) req.getSession().getAttribute("user");

        Person person = personService.queryPersonById(user.getUserId());
        User user1 = userService.queryOneUser(user.getUserId());

        req.setAttribute("person",person);
        req.setAttribute("user",user1);

//        System.out.println(person.toString());
//        System.out.println(user1.toString());
        //根据当前人员登录的身份跳转到相应的页面
        if ( 1 == (user1.getPower())){
            //System.out.println("管理员页面");
            req.getRequestDispatcher("/admin/personalInfo.jsp").forward(req,resp);
        }else if (0 == (user1.getPower())){
//            System.out.println("用户页面");
            req.getRequestDispatcher("/user/personalInfo.jsp").forward(req,resp);
        }
    }
    /**
    *@description: 修改用户的基本信息（手机号、邮箱、密码）
    *@author: summerHouAnNing
    *@creatTime: 2021/5/6--12:04
    *@Param:
    *@return:
    **/
    public void updateUserInfo(HttpServletRequest req,HttpServletResponse resp)throws Exception{
        //获取用户提交的信息
        String userPhone = req.getParameter("userPhone");
        String userEmail = req.getParameter("userEmail");
        String userPassword = req.getParameter("userPassword");
        String userProblem = req.getParameter("userProblem");
        String userAnswer = req.getParameter("userAnswer");
        //获取当前登录用户信息
        User user = (User) req.getSession().getAttribute("user");

        user.setPhone(userPhone);
        user.setEmail(userEmail);
        user.setUserPassword(userPassword);
        user.setUserProblem(userProblem);
        user.setUserAnswer(userAnswer);

        System.out.println(user.getId());
        int i = userService.updateUserInfo(user);
        if (i > 0) {
            req.setAttribute("message", "修改个人信息成功");
            req.getRequestDispatcher("userServlet?action=queryNowPerson").forward(req, resp);
        }
    }
}
