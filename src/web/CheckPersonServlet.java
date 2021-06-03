package web;

import com.google.gson.Gson;
import com.sun.deploy.net.HttpRequest;
import entity.*;
import service.CheckPersonService;
import service.HouseholdService;
import service.PersonService;
import service.impl.CheckPersonServiceImpl;
import service.impl.HouseholdServiceImpl;
import service.impl.PersonServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description
 * @user summerHouAnNing
 * @creatTime 2021/5/6--17:33
 */
public class CheckPersonServlet extends BaseServlet {
    private CheckPersonService checkPersonService = new CheckPersonServiceImpl();
    private PersonService personService = new PersonServiceImpl();
    private HouseholdService householdService = new HouseholdServiceImpl();

    /**
     * @description: 查找所有记录
     * @author: summerHouAnNing
     * @creatTime: 2021/5/6--17:34
     * @Param:
     * @return:
     **/
    public void queryAllCheckPerson(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String pageNo = req.getParameter("pageNo");
        String pageSize = req.getParameter("pageSize");
        String param = req.getParameter("param");
        param = param == null ? " " : param;
        Page<CheckPerson> page = checkPersonService.queryAllPerson(Integer.parseInt(pageNo), Integer.parseInt(pageSize), param);
        System.out.println(page.getItems());
        req.setAttribute("page", page);
        List<Person> list = new ArrayList<>();
        List<Household> list1 = new ArrayList<>();
        for (CheckPerson item : page.getItems()) {
            Person person = personService.queryPersonById(item.getPersonId());

            Household household = householdService.queryListHouseholdById(item.getPersonId());
            list1.add(household);
            list.add(person);
//            System.out.println(person);
        }
        System.out.println("******************");
        list1.forEach(System.out::println);
        System.out.println("*****************");
        req.setAttribute("person", list);
        req.setAttribute("house", list1);
        req.getRequestDispatcher("/admin/checkPerson.jsp").forward(req, resp);
    }

    /**
     * @description: 审查人员
     * @author: summerHouAnNing
     * @creatTime: 2021/5/6--19:06
     * @Param:
     * @return:
     **/
    public void checkPerson(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User user = (User) req.getSession().getAttribute("user");
        String opinion = req.getParameter("opinion");
        int personId = Integer.parseInt(req.getParameter("personId"));
        int checkStatus = Integer.parseInt(req.getParameter("checkStatus"));
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        CheckPerson checkPerson = new CheckPerson();
        checkPerson.setPersonId(personId);
        checkPerson.setCheckOpinion(opinion);
        checkPerson.setCheckStatus(checkStatus);
        checkPerson.setUserId(user.getUserId());
        checkPerson.setCheckTime(format.format(date));
        checkPersonService.checkPerson(checkPerson);

        Map<String, String> map = new HashMap<>();
        map.put("message", "人员审查成功");
        Gson gson = new Gson();
        String s = gson.toJson(map);
        resp.getWriter().write(s);
    }
}
