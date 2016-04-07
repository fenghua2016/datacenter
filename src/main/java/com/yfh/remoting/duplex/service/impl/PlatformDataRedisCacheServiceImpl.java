package com.yfh.remoting.duplex.service.impl;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.ValueOperations;

import com.yfh.remoting.service.PlatformDataCacheService;
import com.yfh.store.mysql.platform.entities.UserBaseInfo;

public class PlatformDataRedisCacheServiceImpl extends ServiceBase implements PlatformDataCacheService{

	@Resource(name="redisTemplate")
	private ValueOperations<String, String> jwtTokenOps;
	
	@Resource(name="redisTemplate")
	private ValueOperations<String, UserBaseInfo> onlineOps;

	
	@Override
	public void putJwtToken(int userId, String token, int hours) {
		
		jwtTokenOps.set(Platform_UserJWTTokens_KEY_Prefix + userId, token, hours, TimeUnit.HOURS);	
		
	}

	@Override
	public boolean verifyJwtToken(final int userId, String token) {
       String existedToken = jwtTokenOps.get(Platform_UserJWTTokens_KEY_Prefix + userId);
       if(existedToken != null && existedToken.equals(token)){
    	   return true;
       }
       
       return false;
	}
	
	@Override
	public int getJwtTokensCount() {
		
		return redisTemplate.keys(Platform_UserJWTTokens_KEY_Prefix + "*").size();
	}

	@Override
	public void putOnlineUser(int userId, int hours, String loginService) {
		UserBaseInfo userInfo = userBaseInfoService.selectOne(userId);
		userInfo.setDefaultloginservice(loginService);
		onlineOps.set(Platform_OnlineUsers_KEY_Prefix + userId, userInfo, hours, TimeUnit.HOURS);
	}

	@Override
	public void removeOfflineUser(int userId) {
		redisTemplate.delete(Platform_OnlineUsers_KEY_Prefix + userId);
	}

	@Override
	public boolean isOnlineUser(final int userId) {
		
		return redisTemplate.execute(new RedisCallback<Boolean>(){	           
				@Override
				public Boolean doInRedis(RedisConnection connection)
						throws DataAccessException {
					byte[] key = redisTemplate.getStringSerializer().serialize(Platform_OnlineUsers_KEY_Prefix + userId);
					if(connection.exists(key)){
						
                         return true; 
					}
					return false;
				}
				
			});
	}

	@Override
	public String getOnlineUserLoginService(int userId) {
		UserBaseInfo userInfo = onlineOps.get(Platform_OnlineUsers_KEY_Prefix + userId);
		return (userInfo != null)? userInfo.getDefaultloginservice() : null;
	}

	@Override
	public int getOnlineUserCount() {
		
		return redisTemplate.keys(Platform_OnlineUsers_KEY_Prefix + "*").size();
	}

	@Override
	public void putOnlineUserAndToken(int userId, String token, int hours, String loginService) {
		
		putJwtToken(userId,token,hours);
		putOnlineUser(userId, hours, loginService);
	}

}
