package com.yfh.store.mysql.mybatis.cache;

import java.util.concurrent.locks.ReadWriteLock;  
import java.util.concurrent.locks.ReentrantReadWriteLock;  
  
import org.apache.ibatis.cache.Cache;  
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yfh.common.Serialization.FSTSerializer;
import redis.clients.jedis.BinaryJedisCluster;
import redis.clients.jedis.exceptions.JedisConnectionException;   
   
  
public class RedisCache implements Cache {  
	
	    protected static Logger log = LoggerFactory.getLogger(RedisCache.class);
        
        /** The ReadWriteLock. */  
        private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();  
        
        @Autowired
        private BinaryJedisCluster binaryJedisCluster;
        
        @Autowired
    	private FSTSerializer fstSerializer;
           
        private String id;  
        public RedisCache(final String id) {  
                if (id == null) {  
                        throw new IllegalArgumentException("ID is Necessary");  
                }  
                log.debug("MybatisRedisCache:id=" + id);  
                this.id=id;  
        }  
           
        @Override  
        public String getId() {  
                return this.id;  
        }  
   
        @SuppressWarnings("deprecation")
		@Override  
        public int getSize() {  
        	
                int result = 0;  
                try {   
                        result = Integer.valueOf(binaryJedisCluster.dbSize().toString());
                        
                } catch (JedisConnectionException e) {   
                } 
                
                return result;  
                    
        }  
   
        @Override  
        public void putObject(Object key, Object value) {  
        	
                if(log.isDebugEnabled())  
                log.debug("putObject:" + key.hashCode() + "=" + value);  
                if(log.isInfoEnabled())  
                log.info("put to redis sql :" +key.toString());  
                try {
                	
                	binaryJedisCluster.set(fstSerializer.serialize(key.hashCode()), fstSerializer.serialize(value));
                	
                } catch (JedisConnectionException e) {  
                       
                }  
                   
        }  
   
        @Override  
        public Object getObject(Object key) {  
                Object value = null;  
                try {  
                        value  = fstSerializer.deserialize(binaryJedisCluster.get(fstSerializer.serialize(key.hashCode())));  
                } catch (JedisConnectionException e) {  
                        
                } 
                
                if(log.isDebugEnabled())  
                log.debug("getObject:" + key.hashCode() + "=" + value);  
                return value;  
        }  
   
        @Override  
        public Object removeObject(Object key) {  
        	
                Object value = null;  
                try {    
                        value  = binaryJedisCluster.expire(fstSerializer.serialize(key.hashCode()), 0);  
                } catch (JedisConnectionException e) {  
                       
                } 
                
                if(log.isDebugEnabled())  
                log.debug("getObject:" + key.hashCode() + "=" + value);  
                return value;  
        }  
   
        @Override  
        public void clear() {  
    
        }  
   
        @Override  
        public ReadWriteLock getReadWriteLock() {  
                return readWriteLock;  
        }  
       
}

