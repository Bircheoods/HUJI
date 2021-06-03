package web;

import entity.Opinion;
import entity.Page;
import entity.Person;
import entity.User;
import service.OpinionService;
import service.PersonService;
import service.impl.OpinionServiceImpl;
import service.impl.PersonServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description
 * @user summerHouAnNing
 * @creatTime 2021/5/5--16:21
 */
public class OpinionServlet extends BaseServlet{

    private OpinionService opinionService = new OpinionServiceImpl();
    private PersonService personService = new PersonServiceImpl();
    /**
    *@description: 新增一条意见
    *@author: summerHouAnNing
    *@creatTime: 2021/5/5--17:02
    *@Param:
    *@return:
    **/
    public void insertOpinion(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        //获取用户输入的数据
        String opinionTitle = req.getParameter("opinionTitle");
        String opinionContent = req.getParameter("opinionContent");

        //获取当前登录用户的信息
        User user = (User) req.getSession().getAttribute("user");
        //获取当前日期并转成date格式
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        //封装opinion对象
        Opinion opinion = new Opinion();
        opinion.setOpinionTime(format.format(date));
        opinion.setOpinionId(user.getUserId());
        opinion.setOpinionContent(opinionContent);
        opinion.setOpinionTitle(opinionTitle);

        opinionService.insertOpinion(opinion);

        req.setAttribute("message","意见提交成功，请您耐心等待回复");
        req.getRequestDispatcher("/user/opinion_add.jsp").forward(req,resp);
    }
    /**
    *@description: 分页查询登录用户的所有意见
    *@author: summerHouAnNing
    *@creatTime: 2021/5/5--17:02
    *@Param:
    *@return:
    **/
    public void queryAllOpinion(HttpServletRequest req,HttpServletResponse resp)throws Exception{
        //获取当前登录用户
        User user = (User) req.getSession().getAttribute("user");
        //获取参数
        int pageNo = Integer.parseInt(req.getParameter("pageNo"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        String param = req.getParameter("param");
        String name = req.getParameter("name");
        name = name == null ? "": name;
        param = param == null ? "":param;


        Page<Opinion> opinionPage = opinionService.queryAllOpinion(pageNo, pageSize, param, user.getUserId());

        //去除格式
        for (Opinion item : opinionPage.getItems()) {
            item.setOpinionContent(item.getOpinionContent().replaceAll("</?[a-zA-Z]+[^><]*>", ""));
        }

        req.setAttribute("opinion",opinionPage);
        req.getRequestDispatcher("/user/opinion.jsp").forward(req,resp);
    }
    /**
    *@description: 分页查询所有用户的所有意见
    *@author: summerHouAnNing
    *@creatTime: 2021/5/8--12:38
    *@Param:
    *@return:
    **/
    public void queryAllOpinionAllUser(HttpServletRequest req,HttpServletResponse resp)throws Exception{
        //获取参数
        int pageNo = Integer.parseInt(req.getParameter("pageNo"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        String param = req.getParameter("param");
        param = param == null ? "":param;


        Page<Opinion> opinionPage = opinionService.queryAllOpinion(pageNo, pageSize, param);

        List<Person> personList = new ArrayList<>();
        //去除格式
        for (Opinion item : opinionPage.getItems()) {
//            System.out.println(item);
            item.setOpinionContent(item.getOpinionContent().replaceAll("</?[a-zA-Z]+[^><]*>", ""));
            Person person = personService.queryPersonById(item.getOpinionId());
            personList.add(person);
        }

//        personList.forEach(System.out::println);

        req.setAttribute("person",personList);
        req.setAttribute("myOpinion",opinionPage);
        req.getRequestDispatcher("/admin/checkOpinion.jsp").forward(req,resp);
    }
    /**
    *@description: 删除一条意见
    *@author: summerHouAnNing
    *@creatTime: 2021/5/5--18:30
    *@Param:
    *@return:
    **/
    public void deleteOpinion(HttpServletRequest req,HttpServletResponse resp)throws Exception{
        String opinionId = req.getParameter("opinionId");
        int i = opinionService.deleteOpinion(Integer.parseInt(opinionId));
        if (i >0)
            req.setAttribute("message","删除意见成功");
        else
            req.setAttribute("message","删除意见失败");
        req.getRequestDispatcher("/opinionServlet?action=queryAllOpinion&pageNo=1&pageSize=10").forward(req,resp);
    }
    /**
    *@description: 根据id查找一条意见
    *@author: summerHouAnNing
    *@creatTime: 2021/5/5--18:37
    *@Param:
    *@return:
    **/
    public void findOpinionById(HttpServletRequest req,HttpServletResponse resp)throws Exception{
        String opinionId = req.getParameter("opinionId");
        Opinion opinion = opinionService.queryOneOpinionById(Integer.parseInt(opinionId));
        req.setAttribute("opinion", opinion);
        if ("search".equals(req.getParameter("name"))) {
            Person person = personService.queryPersonById(opinion.getSolveId());
            req.setAttribute("myPerson",person);
            req.getRequestDispatcher("/user/opinion_details.jsp").forward(req, resp);
        }else if ("update".equals(req.getParameter("name"))){
            req.getRequestDispatcher("/user/opinion_add.jsp").forward(req, resp);
        }else if ("opinionDetails".equals(req.getParameter("name"))){
            req.getRequestDispatcher("/admin/opinionDetails.jsp").forward(req,resp);
        }

    }
    /**
    *@description: 修改一条意见
    *@author: summerHouAnNing
    *@creatTime: 2021/5/5--18:49
    *@Param:
    *@return:
    **/
    public void updateOpinion(HttpServletRequest req,HttpServletResponse resp)throws Exception{
        String id = req.getParameter("id");
        String opinionTitle = req.getParameter("opinionTitle");
        String opinionContent = req.getParameter("opinionContent");

        Opinion opinion = new Opinion();
        opinion.setOpinionTitle(opinionTitle);
        opinion.setOpinionContent(opinionContent);

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        opinion.setOpinionTime(format.format(date));
        opinion.setId(Integer.parseInt(id));
        int i = opinionService.updateOpinion(opinion);

        if (i > 0)
        {
            req.setAttribute("message","修改意见成功");
            req.getRequestDispatcher("/opinionServlet?action=queryAllOpinion&pageNo=1&pageSize=10").forward(req,resp);
        }
        else
        {
            req.setAttribute("message","修改意见失败");
            req.getRequestDispatcher("/opinionServlet?action=findOpinionById&opinionId="+id).forward(req,resp);
        }
    }
    /**
    *@description: 审查一条意见
    *@author: summerHouAnNing
    *@creatTime: 2021/5/8--13:42
    *@Param:
    *@return:
    **/
    public void checkOpinion(HttpServletRequest req,HttpServletResponse resp)throws Exception{
        int id = Integer.parseInt(req.getParameter("id"));
        String solveContent = req.getParameter("solveContent");
        User user = (User) req.getSession().getAttribute("user");

        Opinion opinion = opinionService.queryOneOpinionById(id);

        opinion.setSolveContent(solveContent);
        opinion.setSolveId(user.getUserId());
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        opinion.setSolveTime(format.format(date));

        opinionService.checkOpinion(opinion);

        req.setAttribute("message","意见回复成功");
        req.getRequestDispatcher("/opinionServlet?action=queryAllOpinionAllUser&pageNo=1&pageSize=10").forward(req,resp);
    }
}
