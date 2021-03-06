package cn.zq.config;

import cn.zq.cache.RedisCacheManager;
import cn.zq.shiro.CustomRealm;
import cn.zq.shiro.MySessionDao;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
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
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@DependsOn("shiroRedisTemplate")
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
        // <!-- authc:??????url????????????????????????????????????; anon:??????url????????????????????????-->
        filterChainDefinitionMap.put("/user/login", "anon");
        //filterChainDefinitionMap.put("/module/getList", "anon");
        filterChainDefinitionMap.put("/user/register", "anon");
//        filterChainDefinitionMap.put("/user/account/test", "anon");
        filterChainDefinitionMap.put("/attachment/getFile", "anon");
        filterChainDefinitionMap.put("/attachment/uploads", "anon");
        //filterChainDefinitionMap.put("/sale/order/createOrder", "anon");
        filterChainDefinitionMap.put("/user/**", "authc");
        //????????????????????????????????????????????????????????????????????????????????? url ???????????? ????????????????????????
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
        //defaultWebSessionManager.setSessionDAO(new EnterpriseCacheSessionDAO());
        defaultWebSessionManager.setSessionIdCookie(new SimpleCookie(ShiroHttpSession.DEFAULT_SESSION_ID_NAME));
        //???????????????????????????????????????????????????30??????  ???????????????10?????? ????????????
        defaultWebSessionManager.setGlobalSessionTimeout(86400000);
        //???????????????????????????session??????  ?????????true
        defaultWebSessionManager.setDeleteInvalidSessions(true);
        //?????????????????????????????????????????????session ?????????true
        defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);
        //??????session?????????????????????, ?????????????????????????????????????????????????????? ????????? 1?????????
        //??????????????? ?????????????????? ExecutorServiceSessionValidationScheduler ??????????????????????????????ExecutorServiceSessionValidationScheduler
        //??????????????? 5??? ????????????
        defaultWebSessionManager.setSessionValidationInterval(3600000);
        //??????url ????????? JSESSIONID
        //defaultWebSessionManager.setSessionIdUrlRewritingEnabled(false);
        return defaultWebSessionManager;
    }

    //??????shiro aop????????????
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
        //????????????????????????
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(512);
        customRealm.setCredentialsMatcher(matcher);
        //????????????
        customRealm.setCachingEnabled(true);
        customRealm.setAuthenticationCacheName("authenticationCache");
        customRealm.setAuthenticationCachingEnabled(true);
        customRealm.setAuthorizationCacheName("authorizationCache");
        customRealm.setAuthorizationCachingEnabled(true);
        //??????????????????????????????
        customRealm.setCacheManager(new RedisCacheManager());
        logger.info("init ShiroRealm configuration");
        return customRealm;
    }

}
