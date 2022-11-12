package ru.sfu.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

@Configuration
//@ComponentScan("ru.sfu.controller")
@ComponentScan({"ru.sfu.*"})
@EnableWebMvc
public class SpringConfig implements WebMvcConfigurer {
    private final ApplicationContext applicationContext;

    @Autowired
    public SpringConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8");
        registry.viewResolver(resolver);
    }

    /*@Override
    public void configureContentNegotiation(ContentNegotiationConfigurer
                                                    configurer) {
        configurer
                .favorPathExtension(true)
                .ignoreAcceptHeader(false)
                .useJaf(false)
                .defaultContentType(MediaType.TEXT_HTML)
                .mediaTypes(new HashMap<String, MediaType>() {
                    {
                        put("json", MediaType.APPLICATION_JSON);
                        put("html", MediaType.TEXT_HTML);
                    }
                });
    }

    @Bean
    @Autowired
    public ContentNegotiatingViewResolver viewResolver(
            ContentNegotiationManager cnManager, SpringTemplateEngine templateEngine) {
        ContentNegotiatingViewResolver cnResolver = new ContentNegotiatingViewResolver();
        cnResolver.setContentNegotiationManager(cnManager);
        ThymeleafViewResolver htmlResolver = new ThymeleafViewResolver();
        htmlResolver.setTemplateEngine(templateEngine);
        List<ViewResolver> resolvers = new ArrayList<>();
        resolvers.add(new ThymeleafViewResolver() {{
            setTemplateEngine(templateEngine);
        }});
        cnResolver.setViewResolvers(resolvers);
        return cnResolver;
    }

    private class StudentJsonResolver implements ViewResolver {
        public View resolveViewName(String viewName, Locale locale) throws
                Exception {
            MappingJackson2JsonView view = new MappingJackson2JsonView();
            view.setObjectMapper(new ObjectMapper());
            return view;
        }
    }*/
}
