package br.com.crud.cars.api.filters;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class CarFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if(req.getMethod().equalsIgnoreCase("GET")){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            ((HttpServletResponse) servletResponse).setStatus(500);
            servletResponse.getOutputStream().write("Erro no spring".getBytes());
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

