package web;

import com.google.gson.Gson;
import entity.*;
import service.CheckPersonService;
import service.HouseholdService;
import service.MigrationService;
import service.PersonService;
import service.impl.CheckPersonServiceImpl;
import service.impl.HouseholdServiceImpl;
import service.impl.MigrationServiceImpl;
import service.impl.PersonServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description
 * @user summerHouAnNing
 * @creatTime 2021/5/4--10:55
 */
public class MigrationServlet extends BaseServlet {

    private PersonService personService = new PersonServiceImpl();
    private CheckPersonService checkPersonService = new CheckPersonServiceImpl();
    private HouseholdService householdService = new HouseholdServiceImpl();
    private MigrationService migrationService = new MigrationServiceImpl();


    /**
     * @description: 人员迁入
     * @author: summerHouAnNing
     * @creatTime: 2021/5/4--11:31
     * @Param:
     * @return:
     **/
    public void Immigration(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取用户输入的信息
        String userName = req.getParameter("userName");
        String userIdNum = req.getParameter("userIdNum");
        String relation = req.getParameter("relation");

        //获取当前登录用户信息
        User user = (User) req.getSession().getAttribute("user");
        Person personById = personService.queryPersonById(user.getUserId());
        Household householdById = householdService.queryListHouseholdById(user.getUserId());

        //根据姓名和身份证号查询人员，判断是否存在
        Person person = personService.queryPersonByNameAndNum(userName, userIdNum);

        if (person == null || person.getStatus() == 1) {
            req.setAttribute("message", "迁入人员不存在，请检查输入信息");
            req.getRequestDispatcher("/user/immigration.jsp").forward(req, resp);
            return;
        } else {
            //判断当前人员是否被审核
            CheckPerson checkPerson = checkPersonService.queryCheckUserById(person.getId());
            if (checkPerson.getCheckStatus() == 0) {
                req.setAttribute("message", "人员信息正在审核中，暂时不能进行迁入操作");
                req.getRequestDispatcher("/user/immigration.jsp").forward(req, resp);
                return;
            } else {
                //判断当前人员的户籍信息是否正常
                Household household = householdService.queryListHouseholdById(person.getId());
                if (household == null) {
                    req.setAttribute("message", "当前人员的户籍信息不存在，请尽快联系管理员办理");
                    req.getRequestDispatcher("/user/immigration.jsp").forward(req, resp);
                    return;
                } else if (household.getCheckTime() == null) {
                    req.setAttribute("message", "当前人员的户籍信息正在审查中，暂时不能进行迁入操作");
                    req.getRequestDispatcher("/user/immigration.jsp").forward(req, resp);
                    return;
                } else {//修改当前人员的相关信息

                    //修改人员户户籍地
                    person.setNativePlace(personById.getNativePlace());
                    personService.updatePersonNativePlace(person);

                    //修改人员相关户籍信息
                    household.setHouseholdNum(householdById.getHouseholdNum());
                    household.setHouseholdRelation(relation);
                    household.setHouseholdType(householdById.getHouseholdType());
                    householdService.updatePersonHouseholdByUserId(household);

                    //向迁徙表中新添加数据
                    Migration migration = new Migration();
                    migration.setMigrationAddress(person.getNativePlace());
                    migration.setMigrationType("户籍迁入");//户籍迁入
                    Date date = new Date();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    migration.setMigrationTime(format.format(date));
                    migration.setPersonId(person.getId());
                    migrationService.insertMigration(migration);

                    req.setAttribute("message", "户籍迁入成功，不要忘了修改原户籍关系哦！");
                    req.getRequestDispatcher("/user/immigration.jsp").forward(req, resp);
                    return;
                }
            }
        }
    }

    /**
     * @description: 指定户号的人员迁出
     * @author: summerHouAnNing
     * @creatTime: 2021/5/4--13:42
     * @Param:
     * @return:
     **/
    public void emigrationSureHousehold(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        System.out.println("指定户籍号迁出");
        //获取用户输入的信息
        int emigrationPersonId = Integer.parseInt(req.getParameter("emigrationPerson"));
        String newNum = req.getParameter("newNum");
        String newRelation = req.getParameter("newRelation");

        //查询出迁出人员的基本信息
        Person person = personService.queryPersonById(emigrationPersonId);
        //原本的户号信息
        Household household = householdService.queryListHouseholdById(emigrationPersonId);

        //根据新户号查询迁出地的信息
        List<Household> households = householdService.queryListHouseholdByNum(newNum);
        //判断迁出户号是否存在
        if (households == null || households.size() == 0) {
            req.setAttribute("message", "迁出户号不存在，请重新输入");
            req.getRequestDispatcher("/householdServlet?action=queryHouseholdById").forward(req, resp);
            return;
        } else {
            //更改户籍地
            Person personById = personService.queryPersonById(households.get(0).getPersonId());
            person.setNativePlace(personById.getNativePlace());
            personService.updatePersonNativePlace(person);

            //更改户口信息
            household.setHouseholdType(households.get(0).getHouseholdType());
            household.setHouseholdNum(households.get(0).getHouseholdNum());
            household.setHouseholdRelation(newRelation);
            householdService.updatePersonHouseholdByUserId(household);
            //向迁徙表中新添加数据
            Migration migration = new Migration();
            migration.setMigrationAddress(person.getNativePlace());
            migration.setMigrationType("指定户号迁出");//指定户号迁出
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            migration.setMigrationTime(format.format(date));
            migration.setPersonId(person.getId());
            migrationService.insertMigration(migration);

            req.setAttribute("message", "户籍迁出成功，不要忘了修改原户籍关系哦！");
            req.getRequestDispatcher("/user/emigration.jsp").forward(req, resp);
            return;
        }


    }

    /**
     * @description: 指定地址的人员迁出
     * @author: summerHouAnNing
     * @creatTime: 2021/5/5--10:06
     * @Param:
     * @return:
     **/
    public void emigrationSurePlace(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取用户输入的信息
        String emigrationPerson1 = req.getParameter("emigrationPerson1");//迁出人员id
        String provinces = req.getParameter("provinces");//省份
        String city = req.getParameter("city");//市区
        String country = req.getParameter("country");//县
        String detailPlace = req.getParameter("detailPlace");//详细地址"

        String provincesName = personService.queryOneArea("0", "0", provinces).getAreaName();//获取省份名
        String cityName = personService.queryOneArea(provinces, "0", city).getAreaName();//获取市级名
        String countryName = personService.queryOneArea(city, provinces, country).getAreaName();//获取县级名

        //根据迁出人员id查找原本的户号
        Household household = householdService.queryListHouseholdById(Integer.parseInt(emigrationPerson1));
        Person person = personService.queryPersonById(Integer.parseInt(emigrationPerson1));

        //随机生成新的户号
        StringBuilder builder = new StringBuilder();
        int num = (int) (Math.random() * 600 + 100);
        String newNum = String.valueOf(num);
        char c = (char) ('A' + Math.random() * ('Z' - 'A' + 1));//随机生成A-Z的字母

        builder.append(c + provinces + city + country + newNum);

        System.out.println(builder);

        //更改迁出人员的用户信息
        person.setNativePlace(provincesName+cityName+countryName+detailPlace);//修改籍贯
        household.setHouseholdRelation("户主");//修改关系为户主
        household.setHouseholdNum(builder.toString());//修改新户号

        //调用相应的方法
        personService.updatePersonNativePlace(person);
        householdService.updatePersonHouseholdByUserId(household);
        //向迁徙表中新添加数据
        Migration migration = new Migration();
        migration.setMigrationAddress(person.getNativePlace());
        migration.setMigrationType("指定地址迁出");//指定地址迁出
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        migration.setMigrationTime(format.format(date));
        migration.setPersonId(person.getId());
        migrationService.insertMigration(migration);

        req.setAttribute("message","户籍迁出成功，不要忘了修改原户籍关系哦！");
        req.getRequestDispatcher("/user/emigration.jsp").forward(req,resp);
        return;
    }

    /**
    *@description: 查询所有迁出记录
    *@author: summerHouAnNing
    *@creatTime: 2021/5/7--17:50
    *@Param:
    *@return:
    **/
    public void queryAllMigration(HttpServletRequest req,HttpServletResponse resp)throws Exception{
        int pageNo = Integer.parseInt(req.getParameter("pageNo"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        String param = req.getParameter("param");
        param = param == null ? " ": param;

        Page<Migration> migrationPage = migrationService.queryAllMigration(pageNo, pageSize, param);

        List<Person> personList = new ArrayList<>();
        for (Migration item : migrationPage.getItems()) {

            System.out.println(item);

            Person person = personService.queryPersonById(item.getPersonId());
            personList.add(person);
        }

        req.setAttribute("person",personList);
        req.setAttribute("migrationPage",migrationPage);
        req.getRequestDispatcher("/admin/checkMigration.jsp").forward(req,resp);
    }
    /**
    *@description: 审查
    *@author: summerHouAnNing
    *@creatTime: 2021/5/7--19:19
    *@Param:
    *@return:
    **/
    public void updateMigration(HttpServletRequest req,HttpServletResponse resp)throws Exception{
        User user = (User) req.getSession().getAttribute("user");
        String checkOpinion = req.getParameter("checkOpinion");
        int migrationId = Integer.parseInt(req.getParameter("personId"));


        Migration migration = migrationService.queryMigrationById(migrationId);

        migration.setCheckOpinion(checkOpinion);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        migration.setCheckTime(format.format(date));
        migration.setUserId(user.getUserId());

        int i = migrationService.updateMigration(migration);
        Map<String,String> map = new HashMap<>();
        map.put("message","审查成功");
        Gson gson = new Gson();
        String s = gson.toJson(map);
        resp.getWriter().write(s);
    }
}
