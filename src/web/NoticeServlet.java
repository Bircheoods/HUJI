package web;

import entity.Notice;
import entity.Page;
import entity.Person;
import entity.User;
import service.NoticeService;
import service.PersonService;
import service.impl.NoticeServiceImpl;
import service.impl.PersonServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @description
 * @user summerHouAnNing
 * @creatTime 2021/4/29--13:11
 */
public class NoticeServlet extends BaseServlet {
    private NoticeService noticeService = new NoticeServiceImpl();
    private PersonService personService = new PersonServiceImpl();

    /**
     * @description: 添加一条公告
     * @author: summerHouAnNing
     * @creatTime: 2021/4/29--13:11
     * @Param:
     * @return:
     **/
    public void insertNotice(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取用户提交的信息
        String noticeTitle = req.getParameter("noticeTitle");
        String noticeContent = req.getParameter("noticeContent");
//        System.out.println("准备插入");
        //获取当前登录用户
        User user = (User) req.getSession().getAttribute("user");
        //封装notice实体
        Notice notice = new Notice();
        notice.setNoticeTitle(noticeTitle);
        notice.setNoticeContent(noticeContent);
        notice.setReleaseId(user.getUserId());

        int i = noticeService.insertNotice(notice);
        if (i > 0) {
//            System.out.println("准备跳转");
            req.getSession().setAttribute("message", "公告添加成功");
            resp.sendRedirect(req.getContextPath() + "/admin/notice.jsp");
        } else {
            req.getSession().setAttribute("message", "公告添加失败");
            resp.sendRedirect(req.getContextPath() + "/admin/notice_add.jsp");
        }

    }

    /**
     * @description: 按照当前页查询公告并跳转到管理员页面
     * @author: summerHouAnNing
     * @creatTime: 2021/4/30--14:14
     * @Param:
     * @return:
     **/
    public void queryOneNoticeByLimit(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String param = req.getParameter("param");
        System.out.println("param: " + param);
        param = param == null ? "" : param;
        int pageNo = Integer.parseInt(req.getParameter("pageNo"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        Page<Notice> page = noticeService.queryNoticeNowPage(pageNo, pageSize, param);

        //根据notice中的release_id查询发布人员的名字
        List<Person> personList = new ArrayList<>();
        for (Notice item : page.getItems()) {
            Person person = personService.queryPersonById(item.getReleaseId());
            personList.add(person);
        }


        req.setAttribute("person", personList);
        //请求转发到admin_index.jsp页面
        if (pageSize == 1) {
            //将page和person对象保存到request中
            req.setAttribute("page", page);
            req.getRequestDispatcher("/admin/admin_index.jsp").forward(req, resp);
            return;
        } else if (pageSize == 10) {
            //清除内容所带的样式
            for (Notice item : page.getItems()) {
                item.setNoticeContent(item.getNoticeContent().replaceAll("</?[a-zA-Z]+[^><]*>", ""));
            }//将page和person对象保存到request中
            req.setAttribute("page", page);
            req.getRequestDispatcher("/admin/notice.jsp").forward(req, resp);
            return;
        }
    }

    /**
     * @description: 按照当前页查询公告并跳转到用户页面
     * @author: summerHouAnNing
     * @creatTime: 2021/5/4--8:43
     * @Param:
     * @return:
     **/
    public void queryOneNoticeByLimitToUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String param = req.getParameter("param");
        System.out.println("param: " + param);
        param = param == null ? "" : param;
        int pageNo = Integer.parseInt(req.getParameter("pageNo"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        Page<Notice> page = noticeService.queryNoticeNowPage(pageNo, pageSize, param);

        //根据notice中的release_id查询发布人员的名字
        List<Person> personList = new ArrayList<>();
        for (Notice item : page.getItems()) {
            Person person = personService.queryPersonById(item.getReleaseId());
            personList.add(person);
        }


        req.setAttribute("person", personList);
        //将page和person对象保存到request中
        req.setAttribute("page", page);
        req.getRequestDispatcher("/user/user_index.jsp").forward(req, resp);
        return;


    }

    /**
     * @description: 删除一条公告
     * @author: summerHouAnNing
     * @creatTime: 2021/5/2--7:36
     * @Param:
     * @return:
     **/
    public void deleteOneNotice(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//        System.out.println("删除一条公告");
        String noticeId = req.getParameter("noticeId");
//        System.out.println("公告id为"+noticeId);
        int i = noticeService.deleteOneNotice(noticeId);
        if (i > 0) {
            req.getSession().setAttribute("message", "删除成功");
        } else {
            req.getSession().setAttribute("message", "删除失败");
        }
        resp.sendRedirect(req.getContextPath() + "/noticeServlet?action=queryOneNoticeByLimit&pageNo=1&pageSize=10");
    }

    /**
     * @description: 根据id查找一条公告
     * @author: summerHouAnNing
     * @creatTime: 2021/5/3--2:20
     * @Param:
     * @return:
     **/
    public void findNoticeById(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id = Integer.parseInt(req.getParameter("noticeId"));
        Notice noticeById = noticeService.findNoticeById(id);
        req.setAttribute("notice", noticeById);
        req.getRequestDispatcher("/admin/notice_add.jsp").forward(req, resp);
    }

    /**
     * @description: 根据id修改公告内容
     * @author: summerHouAnNing
     * @creatTime: 2021/5/3--2:31
     * @Param:
     * @return:
     **/
    public void updateNoticeById(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取用户提交的信息
        String noticeTitle = req.getParameter("noticeTitle");
        String noticeContent = req.getParameter("noticeContent");
        int id = Integer.parseInt(req.getParameter("id"));

        Notice notice = new Notice();
        notice.setId(id);
        notice.setNoticeTitle(noticeTitle);
        notice.setNoticeContent(noticeContent);

        int i = noticeService.updateNoticeById(notice);
//        if (i > 0){
//            req.getSession().setAttribute("message","修改成功");
//        }else {
//            req.getSession().setAttribute("message","修改失败");
//        }
        resp.sendRedirect(req.getContextPath() + "/noticeServlet?action=queryOneNoticeByLimit&pageNo=1&pageSize=10");
    }

}
