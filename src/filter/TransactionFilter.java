package filter;

import utils.JDBCUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @description
 * @user summerHouAnNing
 * @creatTime 2021/4/28--8:29
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);
            JDBCUtils.commitAndClose();//提交事务并且关闭连接
        } catch (Exception e) {
            System.out.println("拦截器中抛出异常");
            JDBCUtils.rollBackAndClose();//回滚事务并且关闭连接
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }
}
