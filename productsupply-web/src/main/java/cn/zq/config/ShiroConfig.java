package cn.zq.config;

import cn.zq.cache.RedisCacheManager;
import cn.zq.shiro.CustomRealm;
import cn.zq.shiro.MySessionDao;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);
    @Qualifier("shiroRedisTemplate")
    @Autowired
    RedisTemplate redisTemplate;
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/user/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/notRole");
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/user/login", "anon");
        //filterChainDefinitionMap.put("/module/getList", "anon");
        filterChainDefinitionMap.put("/user/register", "anon");
//        filterChainDefinitionMap.put("/user/account/test", "anon");
        filterChainDefinitionMap.put("/attachment/upload", "anon");
        filterChainDefinitionMap.put("/attachment/uploads", "anon");
        //filterChainDefinitionMap.put("/sale/order/createOrder", "anon");
        filterChainDefinitionMap.put("/user/**", "authc");
        //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截 剩余的都需要认证
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;

    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(customRealm());
        defaultSecurityManager.setSessionManager(sessionsSecurityManager());
        return defaultSecurityManager;
    }

    @Bean
    public DefaultWebSessionManager sessionsSecurityManager(){
        DefaultWebSessionManager defaultWebSessionManager=new DefaultWebSessionManager();
        defaultWebSessionManager.setSessionIdUrlRewritingEnabled(false);
        defaultWebSessionManager.setSessionDAO(new MySessionDao());
        defaultWebSessionManager.setSessionIdCookie(new SimpleCookie(ShiroHttpSession.DEFAULT_SESSION_ID_NAME));
        //全局会话超时时间（单位毫秒），默认30分钟  暂时设置为10秒钟 用来测试
        defaultWebSessionManager.setGlobalSessionTimeout(86400000);
        //是否开启删除无效的session对象  默认为true
        defaultWebSessionManager.setDeleteInvalidSessions(true);
        //是否开启定时调度器进行检测过时session 默认为true
        defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);
        //设置session失效的扫描时间, 清理用户直接关闭浏览器形成的孤立会话 默认为 1个小时
        //设置该属性 就不须要设置 ExecutorServiceSessionValidationScheduler 底层也是默认自动调用ExecutorServiceSessionValidationScheduler
        //暂时设置为 5秒 用来测试
        defaultWebSessionManager.setSessionValidationInterval(3600000);
        //取消url 后面的 JSESSIONID
        //defaultWebSessionManager.setSessionIdUrlRewritingEnabled(false);
        return defaultWebSessionManager;
    }

    //开启shiro aop注解支持
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    @Bean
    public CustomRealm customRealm() {
        CustomRealm customRealm = new CustomRealm();
        //设置校验加密方式
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(512);
        customRealm.setCredentialsMatcher(matcher);
        //开启缓存
        customRealm.setCachingEnabled(true);
        customRealm.setAuthenticationCacheName("authenticationCache");
        customRealm.setAuthenticationCachingEnabled(true);
        customRealm.setAuthorizationCacheName("authorizationCache");
        customRealm.setAuthorizationCachingEnabled(true);
        //注入自定义缓存管理器
        customRealm.setCacheManager(new RedisCacheManager());
        logger.info("init ShiroRealm configuration");
        return customRealm;
    }

}
