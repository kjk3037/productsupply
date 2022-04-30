package cn.zq.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
/*
* 弃用
* */
public class NewRedisCacheManager implements CacheManager {

        private final ConcurrentMap<String, Cache > caches = new ConcurrentHashMap<String, Cache>();
        private RedisTemplate redisTemplate;
        public NewRedisCacheManager(RedisTemplate redisTemplate) {
            this.redisTemplate = redisTemplate;
        }
        @Override
        public<K, V > Cache < K, V > getCache(String name)throws CacheException {
        Cache cache = this.caches.get(name);
        if (cache == null) {
            cache = new ShiroRedisCache<K, V>();
            this.caches.put(name, cache);
        }
        return cache;
    }
        private class ShiroRedisCache<K, V >implements Cache<K, V> {
        private long cacheLive = 30 * 60 * 1000;
        private String cacheKeyPrefix = "shiro.redis.cacheKey:";
        @Override
        public V get (K k)throws CacheException {
            return (V) redisTemplate.opsForValue().get(this.getRedisCacheKey(k));
        }
        @Override
        public V put (K k, V v)throws CacheException {
            redisTemplate.opsForValue().set(this.getRedisCacheKey(k), v, cacheLive, TimeUnit.MINUTES);
            return v;
        }
        @Override
        public V remove (K k)throws CacheException {
            V obj = (V) redisTemplate.opsForValue().get(this.getRedisCacheKey(k));
            redisTemplate.delete(this.getRedisCacheKey(k));
            return obj;
        }
        @Override
        public void clear()throws CacheException {
            Set keys = redisTemplate.keys(this.cacheKeyPrefix + "*");
            if (null != keys && keys.size() > 0) {
                Iterator itera = keys.iterator();
                redisTemplate.delete(itera.next());
            }
        }
        @Override
        public int size() {
            Set<K> keys = redisTemplate.keys(this.cacheKeyPrefix + "*");
            return keys.size();
        }
        @Override
        public Set<K> keys () {
            return redisTemplate.keys(this.cacheKeyPrefix + "*");
        }
        @Override
        public Collection<V> values () {
            Set<K> keys = redisTemplate.keys(this.cacheKeyPrefix + "*");
            Set<V> values = new HashSet<V>(keys.size());
            for (K key : keys) {
                values.add((V) redisTemplate.opsForValue().get(this.getRedisCacheKey(key)));
            }
            return values;
        }
        private String getRedisCacheKey (K key) {
            Object redisKey = this.getStringRedisKey(key);
            if (redisKey instanceof String) {
                return this.cacheKeyPrefix + redisKey;
            } else {
                return String.valueOf(redisKey);
            }
        }
        private Object getStringRedisKey(K key){
            Object redisKey;
            if (key instanceof PrincipalCollection) {
                redisKey = this.getRedisKeyFromPrincipalCollection((PrincipalCollection) key);
            } else {
                redisKey = key.toString();
            }
            return redisKey;
        }
        private Object getRedisKeyFromPrincipalCollection (PrincipalCollection key){
            List realmNames = this.getRealmNames(key);
            Collections.sort(realmNames);
            Object redisKey = this.joinRealmNames(realmNames);
            return redisKey;
        }
        private List<String> getRealmNames (PrincipalCollection key){
            ArrayList realmArr = new ArrayList();
            Set realmNames = key.getRealmNames();
            Iterator i$ = realmNames.iterator();
            while (i$.hasNext()) {
                String realmName = (String) i$.next();
                realmArr.add(realmName);
            }
            return realmArr;
        }
        private Object joinRealmNames (List < String > realmArr) {
            StringBuilder redisKeyBuilder = new StringBuilder();
            for (int i = 0; i < realmArr.size(); ++i) {
                String s = realmArr.get(i);
                redisKeyBuilder.append(s);
            }
            String redisKey = redisKeyBuilder.toString();
            return redisKey;
        }

    }

}
