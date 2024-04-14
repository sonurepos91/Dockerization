package com.devops.dockerization.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Configuration
public class LocaleConfiguragtion implements WebMvcConfigurer {

    /**
     * LocaleResolver Bean is used to set the current Locale from session, cookies, headers.
     *
     * @return
     */
    @Bean
    public LocaleResolver localeResolver () {
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.ENGLISH);
        return localeResolver;
    }

    /**
     * LocaleChangeInterceptor is required to switch to a new Locale
     * based on language parameter appended to request
     *
     * @param registry
     */
    @Override
    public void addInterceptors (InterceptorRegistry registry) {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("language");
        registry.addInterceptor(localeChangeInterceptor);
    }

    /**
     * MessageSource is used for resolving messages, with support for the parameterization and
     * internationalization of the messages
     *
     * @return
     */
    @Bean("messageSource")
    public MessageSource messageSource () {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        return messageSource;
    }

    /**
     * To Override the JSR-303 Normal Validation Error Messages
     */
    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean () {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(messageSource());
        return localValidatorFactoryBean;
    }
}
