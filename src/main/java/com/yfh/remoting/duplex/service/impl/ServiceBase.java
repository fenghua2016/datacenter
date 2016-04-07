package com.yfh.remoting.duplex.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.yfh.store.common.CommonOperation;
import com.yfh.store.mysql.im.entities.InstantGroup;
import com.yfh.store.mysql.im.entities.InstantGroupCriteria;
import com.yfh.store.mysql.im.entities.InstantUser;
import com.yfh.store.mysql.im.entities.InstantUserCriteria;
import com.yfh.store.mysql.im.entities.InstantUserDetail;
import com.yfh.store.mysql.im.entities.InstantUserDetailCriteria;
import com.yfh.store.mysql.im.entities.UserRelation;
import com.yfh.store.mysql.im.entities.UserRelationCriteria;
import com.yfh.store.mysql.im.service.impl.InstantGroupService;
import com.yfh.store.mysql.im.service.impl.InstantUserDetailService;
import com.yfh.store.mysql.im.service.impl.InstantUserService;
import com.yfh.store.mysql.im.service.impl.UserRelationService;
import com.yfh.store.mysql.platform.entities.DataVersion;
import com.yfh.store.mysql.platform.entities.DataVersionCriteria;
import com.yfh.store.mysql.platform.entities.UserBaseInfo;
import com.yfh.store.mysql.platform.entities.UserBaseInfoCriteria;
import com.yfh.store.mysql.platform.service.impl.DataVersionService;
import com.yfh.store.mysql.platform.service.impl.UserBaseInfoService;

public abstract class ServiceBase {
	
	public static final String PlatformCommonCacheValue = "platform-Common";
	public static final String PlatformUserBaseInfoCacheValue = "platform-UserBaseInfo";
	//Spring Cache keys for platform
	public static final String Platform_AppCategories_KEY = "Platform_AppCategories";
	public static final String Platform_AppEntries_KEY = "Platform_AppEntries";
	public static final String Platform_AppPrices_KEY = "Platform_AppPrices";
	public static final String Platform_Countries_KEY = "Platform_Countries";
	public static final String Platform_ChinaProvinces_KEY = "Platform_ChinaProvinces";
	public static final String Platform_ChinaCities_KEY = "Platform_ChinaCities";
	public static final String Platform_ChinaDistricts_KEY = "Platform_ChinaDistricts";
	public static final String Platform_IndustryGeneralCategories_KEY = "Platform_IndustryGeneralCategories";
	public static final String Platform_IndustryCategories_KEY = "Platform_IndustryCategories";
	public static final String Platform_Jobs_KEY = "Platform_Jobs";
	public static final String Platform_UserRoles_KEY = "Platform_UserRoles";
	public static final String Platform_DataVersions_KEY = "Platform_DataVersions";
	
	//Spring Cache keys for imservice
	public static final String IMServiceUserDetailCacheValue = "imservice-user-detail-data";
	public static final String IMServiceUserDataCacheValue = "imservice-user-data";
	public static final String IMServiceRelationDataCacheValue = "imservice-relation-data";
	public static final String IMServiceGroupDataCacheValue = "imservice-group-data";
	
	// Keys  for Manual Redis Operation with platform users;
	protected static final String  Platform_UserJWTTokens_KEY_Prefix = "Platform_User_JWTTokens:";
	protected static final String  Platform_OnlineUsers_KEY_Prefix = "Platform_Users_Online:";
	
	//platform data services;
	@Resource(name="dataVersionService")
	protected CommonOperation<DataVersion, DataVersionCriteria> dataVersionService;
	
	@Resource(name="userBaseInfoService")
	protected CommonOperation<UserBaseInfo, UserBaseInfoCriteria> userBaseInfoService;
	
	//imservice data services;
	@Resource(name="instantUserDetailService")
	protected CommonOperation<InstantUserDetail, InstantUserDetailCriteria> instantUserDetailService;
	
	@Resource(name="instantUserService")
	protected CommonOperation<InstantUser, InstantUserCriteria> instantUserService;
	
	@Resource(name="userRelationService")
	protected CommonOperation<UserRelation, UserRelationCriteria> userRelationService;
	
	@Resource(name="instantGroupService")
	protected CommonOperation<InstantGroup, InstantGroupCriteria> instantGroupService;
	
	@Autowired
	protected RedisTemplate<Serializable, Serializable> redisTemplate;

}
