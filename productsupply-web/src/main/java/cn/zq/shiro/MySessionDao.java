package cn.zq.shiro;

import cn.zq.cache.RedisCacheManager;
import cn.zq.utils.BeanUtils;
import cn.zq.utils.MyByteSource;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.sql.Time;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
/*
* 自定义sessionDAO方式
* 通过redis实现持久化，且集群内共享session
* */
public class MySessionDao extends AbstractSessionDAO {
    //private static final String SESSION_PREFIX="session:";
    private static final String CACHE_NAME="shiro-activeSessionCache";
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId=generateSessionId(session);
        //System.out.println("sessionDAO -> 生成id:"+sessionId.toString());
        this.assignSessionId(session, sessionId);
        getRedisTemplate().opsForHash().put(CACHE_NAME,sessionId,session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        //System.out.println("sessionDAO -> 执行读取 :"+serializable.toString());
        Session session = (Session) getRedisTemplate().opsForHash().get(CACHE_NAME,serializable.toString());
        //System.out.println(session);
        return session;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        //System.out.println("sessionDAO -> 执行修改:"+session.getId());
        Session s = (Session) getRedisTemplate().opsForHash().get(CACHE_NAME, session.getId());
        if (s!=null){
            getRedisTemplate().opsForHash().put(CACHE_NAME,session.getId(),session);
        }
    }

    @Override
    public void delete(Session session) {
        //System.out.println("sessionDAO -> 执行删除:"+session.getId());
        getRedisTemplate().opsForHash().delete(CACHE_NAME,session.getId());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        //System.out.println("sessionDAO -> 执行返回所有session");
        return getRedisTemplate().opsForHash().values(CACHE_NAME);
    }

    public RedisTemplate getRedisTemplate(){
        RedisTemplate redisTemplate= (RedisTemplate) BeanUtils.getBean("shiroRedisTemplate");
        return redisTemplate;
    }
}
