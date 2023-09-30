package com.elms.auth.config;

import feign.Contract;
import feign.Feign;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;

@Configuration
@ConditionalOnClass({Feign.class})
@Slf4j
public class CustomFeignConfig {

    @Bean
    public Contract feignContract() {
        return new SpringMvcContract();
    }

//    @Configuration
//    public class FeignFormatterRegister implements FeignFormatterRegistrar {
//        @Override
//        public void registerFormatters(FormatterRegistry registry) {
//            registry.addFormatter(new DateFormatter());
//        }
//    }
//
//    @Component
//    public class DateFormatter implements Formatter<Date> {
//
//        SimpleDateFormat formatter = new SimpleDateFormat(WebMvcConfig.DATE_TIME_FORMAT);
//
//        @Override
//        public Date parse(String text, Locale locale) throws ParseException {
//            return formatter.parse(text);
//        }
//
//        @Override
//        public String print(Date date, Locale locale) {
//            return formatter.format(date);
//        }
//    }

}
