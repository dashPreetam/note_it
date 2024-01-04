/*

 * Date : 03/01/24

 * Author : SWASTIK PREETAM DASH

 */

package speer.assesment.note_it.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import speer.assesment.note_it.constant.AppConstants;
import speer.assesment.note_it.utility.DateTimeUtility;

import java.io.IOException;

@Slf4j
@Component
public class RequestFilter implements Filter {
    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        log.info("Initializing filter :{}", this);
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        MDC.put(AppConstants.MDC_TRACKER, String.valueOf(DateTimeUtility.getCurrentTimeUnix()));
        log.info("API hit -> {} at {}", req.getRequestURI(), DateTimeUtility.getCurrentDateTimeISTString());


        chain.doFilter(request, response);

        log.info("API responded with code {} at {}", res.getStatus(), DateTimeUtility.getCurrentDateTimeISTString());
        MDC.clear();
    }

    @Override
    public void destroy() {
        log.warn("Destructing filter :{}", this);
    }
}