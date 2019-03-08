package recruitment.config;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.Locale;

/**
 * Loads the configuration for the recruitment application except the security configuration {@link SecurityConfig}
 * @see SecurityConfig
 */
@EnableTransactionManagement
@EnableWebMvc
@Configuration
public class RecruitmentConfig implements WebMvcConfigurer, ApplicationContextAware {
    private ApplicationContext applicationContext;


    /**
     * @param applicationContext The application context used by the running
     *                           application.
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Create a <code>org.springframework.web.servlet .ViewResolver</code> bean
     * that delegates all views to thymeleaf's template engine. There is no need
     * to specify view name patterns since the will be the only existing view
     * resolver.
     */
    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setContentType("text/html; charset=UTF-8");
        viewResolver.setTemplateEngine(templateEngine());
        return viewResolver;
    }

    /**
     * Create a <code>org.thymeleaf.ITemplateEngine</code> bean that manages
     * thymeleaf template integration with Spring. All template resolution will
     * be delegated to the specified template resolver.
     */
    @Bean(name = "recruitmentTemplateEngine")
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        templateEngine.addDialect(new LayoutDialect());
        templateEngine.addDialect(new SpringSecurityDialect());
        return templateEngine;
    }

    /**
     * Create a <code>org.thymeleaf.templateresolver.ITemplateResolver</code>
     * that can handle thymeleaf template integration with Spring. This will be
     * the only existing template resolver.
     */
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(this.applicationContext);
        templateResolver.setPrefix("classpath:/web-root/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(true);
        return templateResolver;
    }

    /**
     * Configuration of requests for static files.
     **/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        int cachePeriodForStaticFilesInSecs = 1;
        String rootDirForStaticFiles = "classpath:/web-root/";
        registry.addResourceHandler("/**")
                .addResourceLocations(rootDirForStaticFiles)
                .setCachePeriod(cachePeriodForStaticFilesInSecs)
                .resourceChain(true).addResolver(new PathResourceResolver());
    }
    /**

     * Register the i18n interceptor.

     */

    @Override

    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(localeChangeInterceptor());

    }



    /**

     * Create a <code>org.springframework.web.servlet.i18n

     * .LocaleChangeInterceptor</code> for locale management.

     */

    @Bean

    public LocaleChangeInterceptor localeChangeInterceptor() {

        String nameOfHttpParamForLangCode = "lang";

        String[] allowedHttpMethodsForLocaleChange = {"GET", "POST"};



        LocaleChangeInterceptor i18nBean = new LocaleChangeInterceptor();

        i18nBean.setParamName(nameOfHttpParamForLangCode);

        i18nBean.setHttpMethods(allowedHttpMethodsForLocaleChange);

        i18nBean.setIgnoreInvalidLocale(true);

        return i18nBean;

    }



    /**

     * Create a <code>org.springframework.web.servlet.i18n

     * .SessionLocaleResolver</code> that stores the user's current locale in

     * the session object.

     */

    @Bean

    public LocaleResolver localeResolver()

    {

        SessionLocaleResolver localeResolver = new SessionLocaleResolver();

//        localeResolver.setDefaultLocale(new Locale("en"));

        return localeResolver;

    }



    /**

     * Create a <code>org.springframework.context.support.

     * ReloadableResourceBundleMessageSource</code> that loads resource

     * bundles for i18n.

     */

    @Bean

    public ReloadableResourceBundleMessageSource messageSource() {

        String l10nMsgDir = "classpath:/i18n/Strings";

        String l10nValidationMsgDir = "classpath:/i18n/ValidationStrings";

        ReloadableResourceBundleMessageSource resource =

                new ReloadableResourceBundleMessageSource();

        resource.addBasenames(l10nMsgDir, l10nValidationMsgDir);

        resource.setDefaultEncoding("UTF-8");

        resource.setFallbackToSystemLocale(false);

        return resource;

    }


    /**

     * Use the bean defined by {@link #messageSource()} for validation messages.

     */

    @Override

    public Validator getValidator() {

        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();

        validator.setValidationMessageSource(messageSource());

        return validator;

    }
}
