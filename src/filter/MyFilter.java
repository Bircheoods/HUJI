package filter;

import entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @description
 * @user summerHouAnNing
 * @creatTime 2021/4/28--8:34
 */
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;

        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");

        String url = req.getServletPath();

        ArrayList allowPathList = new ArrayList();
        //需要放行的页面
        allowPathList.add("/user/register.jsp");
        allowPathList.add("/user/findPassword.jsp");
        allowPathList.add("/userServlet");

        if (allowPathList.contains(url) || user != null){
            filterChain.doFilter(servletRequest,servletResponse);
        } else if(user == null){
            req.setAttribute("message","请先登录！");
            req.getRequestDispatcher("/index.jsp").forward(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
