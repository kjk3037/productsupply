package cn.zq.shiro;

import cn.zq.utils.BeanUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/*
* 自定义sessionDAO方式
* 通过redis实现持久化，且集群内共享session
* 已弃用
* */
public class NewSessionDao extends AbstractSessionDAO {
    private static Logger logger = LoggerFactory.getLogger(NewSessionDao.class);
    private RedisTemplate redisTemplate;
    private String keyPrefix ="shiro.redis.session:";
    public NewSessionDao(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    private String getKey(String key){
        return keyPrefix + key;
    }
    private void saveSession(Session session)throws UnknownSessionException {
        if(session != null && session.getId()!= null){
            String key =this.getKey(session.getId().toString());
            session.setTimeout(30*60*1000);
            redisTemplate.opsForValue().set(key, session,30*60*1000, TimeUnit.MILLISECONDS);
        }else{
            logger.error("session or session id is null");
        }
    }
    @Override
    public void update(Session session)throws UnknownSessionException {
        this.saveSession(session);
    }
    @Override
    public void delete(Session session) {
        try{
            String key =getKey(session.getId().toString());
            redisTemplate.delete(key);
        }catch(Exception e) {
            logger.info(e.getMessage());

        }
    }

    @Override
    public Collection<Session>getActiveSessions(){
        Set<Session> sessions =new HashSet<>();
        Set<String> keys = redisTemplate.keys(getKey("*"));
        if(keys != null && keys.size()>0){
            for(String key : keys){
                Session s =(Session) redisTemplate.opsForValue().get(key);
                sessions.add(s);
            }
        }
        return sessions;
    }
    @Override
    protected Serializable doCreate(Session session){
        Serializable sessionId =this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.saveSession(session);
        return sessionId;
    }
    @Override
    protected Session doReadSession(Serializable sessionId) {
        Session readSession = null;
        try {
            readSession = (Session) redisTemplate.opsForValue().get(getKey(sessionId.toString()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return readSession;
    }
    public RedisTemplate getRedisTemplate(){
        RedisTemplate redisTemplate= (RedisTemplate) BeanUtils.getBean("shiroRedisTemplate");
        return redisTemplate;
    }

}
