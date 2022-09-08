package com.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */

@Configuration
public class AppInit extends
        AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { AppContext.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebAppConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
