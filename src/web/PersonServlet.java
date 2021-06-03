package web;

import com.google.gson.Gson;
import entity.*;
import service.CheckPersonService;
import service.HouseholdService;
import service.PersonService;
import service.ResultService;
import service.impl.CheckPersonServiceImpl;
import service.impl.HouseholdServiceImpl;
import service.impl.PersonServiceImpl;
import service.impl.ResultServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description
 * @user summerHouAnNing
 * @creatTime 2021/4/24--12:56
 */
public class PersonServlet extends BaseServlet {
    private ResultService resultService = new ResultServiceImpl();
    private PersonService personService = new PersonServiceImpl();
    private CheckPersonService checkPersonService = new CheckPersonServiceImpl();
    private HouseholdService householdService = new HouseholdServiceImpl();


    /**
    *@description: 初始化省份地区信息
    *@author: summerHouAnNing
    *@creatTime: 2021/4/24--12:56
    *@Param:
    *@return:
    **/
    public void initProvinces(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        //初始化时查找省份，父id和爷爷id都为0
        List<Area> areas = personService.queryArea("0", "0");
//        area.forEach(System.out::println);
        areas.forEach(System.out::println);
        Gson gson =new Gson();
        String s = gson.toJson(areas);

        resp.getWriter().write(s);
    }
    /**
    *@description: 根据省份地区num初始化市区
    *@author: summerHouAnNing
    *@creatTime: 2021/4/26--10:03
    *@Param:
    *@return:
    **/
    public void initCity(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
      //  System.out.println("通过ajax执行了该方法");
        String provincesNum = req.getParameter("provincesNum");
      //  System.out.println("省份的编号为："+provincesNum);
        //根据父亲id：provincesNum和爷爷id：0查找省份信息
        List<Area> areas = personService.queryArea(provincesNum, "0");
//        areas.forEach(System.out::println);
       //使用gson封装成json传给jsp页面
        Gson gson =new Gson();
        String s = gson.toJson(areas);

        resp.getWriter().write(s);
    }
    /**
    *@description: 根据省份和市区id初始化县区
    *@author: summerHouAnNing
    *@creatTime: 2021/4/27--7:20
    *@Param:
    *@return:
    **/
    public void initCounty(HttpServletRequest req,HttpServletResponse resp) throws Exception{
        String provincesNum = req.getParameter("provincesNum");
        String cityNum = req.getParameter("cityNum");

        //根据爷爷id：provincesNum和父亲id：cityNum查找省份信息
        List<Area> areas = personService.queryArea(cityNum, provincesNum);
//        areas.forEach(System.out::println);
        //使用gson封装成json传给jsp页面
        Gson gson =new Gson();
        String s = gson.toJson(areas);

        resp.getWriter().write(s);
    }

    /**
     * @description: 出生证明办理
     * @author: summerHouAnNing
     * @creatTime: 2021/4/27--8:03
     * @Param:
     * @return:
     **/
    public void insertOnePerson(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        //获取当前登录用户的信息
        User user = (User) req.getSession().getAttribute("user");
        Person person2 = personService.queryPersonById(user.getUserId());
        Household household = householdService.queryListHouseholdById(user.getUserId());


        //获取用户提交的数据
        String personName = req.getParameter("personName");
        String oldName = req.getParameter("oldName");
        String sex = req.getParameter("sex");
        String birth = req.getParameter("birth");
        String nation = req.getParameter("nation");
        String political = req.getParameter("political");
        String professional = req.getParameter("professional");
        String education = req.getParameter("education");
        String religious = req.getParameter("religious");
        String bloodType = req.getParameter("bloodType");
        String militaryService = req.getParameter("militaryService");
        String marriage = req.getParameter("marriage");
        String relation = req.getParameter("relation");


        //将string类型的date转成sql类型
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = format.parse(birth);


        //根据地区、出生日期和性别生成身份证号
        StringBuilder builder = new StringBuilder();
        //截取当前登录用户身份证的前六位
        String idNum = person2.getIdentityNum().substring(0, 6);
        builder.append(idNum);
        //将生日按照“-”截取
        String[] split = birth.split("-");
        for (String s : split) {//将截取之后的字符拼接
            builder.append(s);
        }
        //判断性别
        if ("0".equals(sex)) {//如果是女生，随机生成一个三位偶数
            int random = (int) (Math.random() * 900 + 100);
            if (random % 2 == 0)
                builder.append(random);
            else
                builder.append((random + 1));
        } else {//如果是男生，随机生成一个三位奇数
            int random = (int) (Math.random() * 900 + 100);
            if (random % 2 == 0)
                builder.append((random + 1));
            else
                builder.append(random);
        }
        //随机生成最后一位识别码（真实情况应该是计算出来的，这里用随机数代替）
        int lastNum = (int) (Math.random() * 10) + 1;
        if (lastNum == 10)//如果最后一位为10，则用X替代
            builder.append("X");
        else
            builder.append(lastNum);

        //封装person对象
        Person person = new Person();
        person.setName(personName);
        person.setOldName(oldName);
        person.setSex(Integer.parseInt(sex));//0女1男
        person.setIdentityNum(builder.toString());
        person.setBloodType(bloodType);
        person.setEducation(education);
        person.setMarriage(Integer.parseInt(marriage));//0未婚，1已婚，2离异
        person.setMilitaryService(Integer.parseInt(militaryService));//0已服兵役，1未服兵役
        person.setReligious(religious);
        person.setProfessional(professional);
        person.setPolitical(political);
        person.setNation(nation);
        person.setBirthday(new java.sql.Date(parse.getTime()));
        person.setStatus(0);//状态默认为0，未审核
        person.setNativePlace(person2.getNativePlace());
        person.setAddress(person2.getAddress());

        //像数据表中插入数据
        int i = personService.insertOnePerson(person);
        Person personByIdNum = personService.queryOnePersonByIdNum(person.getIdentityNum());

        CheckPerson checkPerson = new CheckPerson();
        checkPerson.setPersonId(personByIdNum.getId());
        checkPerson.setCheckStatus(0);
        checkPerson.setCheckTime(null);
        checkPersonService.insertOneData(checkPerson);

        household.setPersonId(personByIdNum.getId());
        household.setHouseholdRelation(relation);
        householdService.insertOneHousehold(household);


        req.setAttribute("message", "人员户籍申请成功，请等待管理员审核");
        req.getRequestDispatcher("/user/birth.jsp").forward(req, resp);
        return;

    }

    /**
    *@description: 死亡登记页面根据id查询人员信息
    *@author: summerHouAnNing
    *@creatTime: 2021/5/5--11:15
    *@Param:
    *@return:
    **/
    public void deathQueryPersonById(HttpServletRequest req,HttpServletResponse resp)throws Exception{
        //获取选择的id
        String personId = req.getParameter("personId");

        Person person = personService.queryPersonById(Integer.parseInt(personId));

        Household household = householdService.queryListHouseholdById(Integer.parseInt(personId));

        Gson gson = new Gson();

        Map<String,Object> map = new HashMap<>();
        map.put("household",household);
        map.put("person",person);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String newDate = format.format(person.getBirthday());

        map.put("birthday",newDate);

        String s = gson.toJson(map);

        resp.getWriter().write(s);

    }

    /**
     * @description: 死亡人员登记
     * @author: summerHouAnNing
     * @creatTime: 2021/4/27--13:40
     * @Param:
     * @return:
     **/
    public void death(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取用户提交的信息
        String householdPerson = req.getParameter("householdPerson");
        int personId = Integer.parseInt(householdPerson);

        Person person = new Person();
        person.setId(personId);
        person.setStatus(1);
        //修改人员状态
        personService.updatePersonStatus(person);

        //删除人员在户主表中的信息
        householdService.deleteOneData(personId);

        req.getSession().setAttribute("message", "人员已经成功注销！");
        resp.sendRedirect(req.getContextPath() + "/user/user_index.jsp");
        return;
    }

    /**
     * @description: 根据id查询人员信息
     * @author: summerHouAnNing
     * @creatTime: 2021/4/27--15:22
     * @Param:
     * @return:
     **/
//    public void houseHoldDetails(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//        //根据session获取到当前登录人员的信息
//        HttpSession session = req.getSession();
//        User user = (User) session.getAttribute("user");
//
//        if (user == null) {
//            req.setAttribute("message", "当前登录已过期，请重新登录！");
//            req.getRequestDispatcher("/index.jsp").forward(req, resp);
//            return;
//        }
//
//        //根据当前登陆人员的id查找人员信息
//        Person person = personService.queryPersonById(user.getUserId());
//        Household households = householdService.queryListHouseholdById(user.getUserId());
//        if (households != null) {
//            //根据户号查询出与当前人员有关的户
//            List<Household> households1 = householdService.queryListHouseholdByNum(households.getHouseholdNum());
//            req.setAttribute("households", households1);
//        } else {
//            req.setAttribute("households", households);
//        }
//        req.setAttribute("person", person);
//        req.getRequestDispatcher("/user/household.jsp").forward(req, resp);
//        return;
//    }

    /**
     * @description: 根据姓名模糊查找person
     * @author: summerHouAnNing
     * @creatTime: 2021/5/4--6:23
     * @Param:
     * @return:
     **/
    public void queryPersonByName(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int pageNo = Integer.parseInt(req.getParameter("pageNo"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        String param = req.getParameter("param");
    }

    /**
     * @description: 获取当前登录人员的信息
     * @author: summerHouAnNing
     * @creatTime: 2021/5/4--9:07
     * @Param:
     * @return:
     **/
    public void queryUserInfo(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取当前登录人员id，查找户号
        User user = (User) req.getSession().getAttribute("user");
        Household household = householdService.queryListHouseholdById(user.getUserId());
        Person person = personService.queryPersonById(user.getUserId());
        if (household == null) {
            req.getSession().setAttribute("message", "您当前不属于任何一户，无法进行出生登记，请立即联系管理员进行登记");
            resp.sendRedirect(req.getContextPath() + "/user/user_index.jsp");
            return;
        }
        req.setAttribute("holdNum", household.getHouseholdNum());
        req.setAttribute("person", person);
        //请求转发到登记页面
        req.getRequestDispatcher("/user/birth.jsp").forward(req, resp);
        return;
    }
}
