package web;

import com.google.gson.Gson;
import entity.Household;
import entity.Person;
import entity.User;
import service.HouseholdService;
import service.PersonService;
import service.impl.HouseholdServiceImpl;
import service.impl.PersonServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description
 * @user summerHouAnNing
 * @creatTime 2021/4/27--18:18
 */
public class HouseholdServlet extends BaseServlet{
    private HouseholdService householdService = new HouseholdServiceImpl();
    private PersonService personService = new PersonServiceImpl();
    /**
    *@description: 添加户主信息
    *@author: summerHouAnNing
    *@creatTime: 2021/4/27--18:18
    *@Param:
    *@return:
    **/
    public void insertHousehold(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        //获取用户的提交信息
        String householdType = req.getParameter("householdType");
        String isHousehold = req.getParameter("isHousehold");
        String houseNum = req.getParameter("houseNum");
        String relation = req.getParameter("relation");

        System.out.println("户主类型: "+householdType);
        System.out.println("是否户主: "+isHousehold);
        System.out.println("户主号："+houseNum);
        System.out.println("与户主之间的关系："+relation);

        if ("是".equals(isHousehold)){//如果是户主，随机生成户主号
            int num = (int)(Math.random()*60000+10000);
            String newNum = String.valueOf(num);
            char c=(char)('A'+Math.random()*('Z'-'A'+1));//随机生成A-Z的字母
            houseNum = c + "--" + num;

            //与户主关系设为本人
            relation = "本人";
        }else {//如果不是户主，根据户主号查询该户是否存在
            List<Household> households = householdService.queryListHouseholdByNum(houseNum);
            if (households == null || households.size() == 0){//没有查到
                req.setAttribute("message","该户主号不存在，请检查输入情况或者联系管理员");
                req.getRequestDispatcher("/user/household.jsp").forward(req,resp);
                return;
            }
        }
        Household household = new Household();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        household.setPersonId(user.getUserId());
        household.setHouseholdNum(houseNum);
        household.setHouseholdType(householdType);
        household.setHouseholdRelation(relation);
        household.setCheckTime(null);

        int i = householdService.insertOneHousehold(household);
        if (i > 0){
            req.setAttribute("message","户籍信息登记成功，请等待管理员审核");
            req.getRequestDispatcher("/user/user_index.jsp").forward(req,resp);
            return;
        }else {
            req.setAttribute("message","户籍信息登记失败,请检查填写情况或者联系管理员");
            req.getRequestDispatcher("/user/household.jsp").forward(req,resp);
            return;
        }
    }
    /**
    *@description: 根据id查询当前户信息
    *@author: summerHouAnNing
    *@creatTime: 2021/4/28--15:57
    *@Param:
    *@return:
    **/
    public void queryHouseholdById(HttpServletRequest req,HttpServletResponse resp) throws Exception{
        //获取当前登录人员id，查找户号
        User user = (User) req.getSession().getAttribute("user");
        Household household = householdService.queryListHouseholdById(user.getUserId());

        //查询登陆人员信息

        Person person1 = personService.queryPersonById(user.getUserId());
        //根据户号查询本户人员
        List<Household> households = householdService.queryListHouseholdByNum(household.getHouseholdNum());


        //根据人员id查询基本信息
        List<Person> list = new ArrayList<Person>();
        for (Household household1 : households) {
            Person person = personService.queryPersonById(household1.getPersonId());
            person.setAddress(household1.getHouseholdRelation());
            list.add(person);
        }
//        list.forEach(System.out::println);
        req.setAttribute("person",list);
        req.setAttribute("myPerson",person1);
        req.setAttribute("household",household);
        if ("emigration".equals(req.getParameter("name")))
            req.getRequestDispatcher("/user/emigration.jsp").forward(req,resp);
        else if ("death".equals(req.getParameter("name")))
            req.getRequestDispatcher("/user/death.jsp").forward(req,resp);
        else if ("myHousehold".equals(req.getParameter("name")))
            req.getRequestDispatcher("/user/myHousehold.jsp").forward(req,resp);
    }
    /**
    *@description: 修改与户主之间的关系
    *@author: summerHouAnNing
    *@creatTime: 2021/5/6--13:57
    *@Param:
    *@return:
    **/
    public void updateRelation(HttpServletRequest req,HttpServletResponse resp)throws Exception{

        Map<String,String> map = new HashMap<>();
        Gson gson = new Gson();

        //获取用户提交的数据
        String personId = req.getParameter("personId");
        String newRelation = req.getParameter("newRelation");

        Household household = householdService.queryListHouseholdById(Integer.parseInt(personId));
        household.setHouseholdRelation(newRelation);

        //根据户号查关系
        List<Household> households = householdService.queryListHouseholdByNum(household.getHouseholdNum());

        boolean flag = true;
        for (Household household1 : households) {
            flag = household1.getHouseholdRelation().equals(newRelation);
            if (flag){
                break;
            }
        }
        if (flag){
            map.put("message","人员关系不可重复，请重新修改");
        }else {
            householdService.updatePersonHouseholdByUserId(household);
            map.put("message","修改人员关系成功!");
        }
        String s = gson.toJson(map);
        resp.getWriter().write(s);
    }
}
