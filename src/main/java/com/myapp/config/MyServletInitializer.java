package com.myapp.config;

import com.myapp.MyFilter;
import com.myapp.MyServlet;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.*;

public class MyServletInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {
        //서블릿 등록
        ServletRegistration.Dynamic myServlet = servletContext.addServlet("myServlet", (Class<? extends Servlet>) MyServlet.class);

        myServlet.addMapping("/custom/**");

        //필터 등록
        FilterRegistration.Dynamic filter = servletContext.addFilter("myFilter", (Class<? extends Filter>) MyFilter.class);

        filter.addMappingForUrlPatterns(null, false, "/custom/*");
    }
}
