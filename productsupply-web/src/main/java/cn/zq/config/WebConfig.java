package cn.zq.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import sun.util.logging.PlatformLogger;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);
    // 设置允许跨域的源
    private static String[] originsVal = new String[]{
            "127.0.0.1:8080",
            "localhost:8080",
            "localhost:8081",
            "127.0.0.1:8081",
    };

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600)
                .allowCredentials(true);
    }
    /**
     * 跨域过滤器
     *
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration());
        logger.info("init corsFilter");
        return new CorsFilter(source);
    }
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration());
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);//提升优先级
        return bean;
    }
    @Bean
    public CorsConfiguration corsConfiguration(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        //corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.setAllowCredentials(true);
        this.addAllowedOrigins(corsConfiguration);
        return corsConfiguration;
    }
    private void addAllowedOrigins(CorsConfiguration corsConfiguration) {
        for (String origin : originsVal) {
            corsConfiguration.addAllowedOrigin("http://" + origin);
            corsConfiguration.addAllowedOrigin("https://" + origin);
        }
    }
}
