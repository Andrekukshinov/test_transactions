package by.kukshinov.test.app.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherServletInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    public static final String ALL_REQUESTS = "/";

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ConfigurationClass.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{ALL_REQUESTS};
    }
}
