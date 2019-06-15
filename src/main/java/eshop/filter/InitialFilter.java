package eshop.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Евгений
 */
public class InitialFilter implements Filter {
    private String encoding;
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("filter init");
        encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void destroy() {
    }
}
