package eshop.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Евгений
 */
public class InitialFilter implements Filter {
    private String encoding;
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        filterChain.doFilter(request, response);
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
