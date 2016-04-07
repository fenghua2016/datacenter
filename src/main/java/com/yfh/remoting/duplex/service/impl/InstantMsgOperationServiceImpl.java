package com.yfh.remoting.duplex.service.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import com.yfh.store.mysql.im.entities.InstantGroup;
import com.yfh.store.mysql.im.entities.InstantUser;
import com.yfh.store.mysql.im.entities.InstantUserDetail;
import com.yfh.store.mysql.im.entities.UserRelation;
import com.yfh.store.mysql.im.service.InstantOperationService;

public class InstantMsgOperationServiceImpl extends ServiceBase implements InstantMsgOperationService {

	
	@Override
	@Cacheable(value="#root.target.IMServiceRelationDataCacheValue")
	public UserRelation getUserRelationByUserId(int userId) {
		return userRelationService.selectOne(userId);
	}

	@Override
	@CacheEvict(value="#root.target.IMServiceRelationDataCacheValue", key="#userRelation.getUserid()")
	public int insertUserRelation(UserRelation userRelation) {
		return userRelationService.insert(userRelation);
	}

	@Override
	@Caching(evict= {
		@CacheEvict(value="#root.target.IMServiceUserDetailCacheValue", key="#userRelation.getUserid()"),
		@CacheEvict(value="#root.target.IMServiceRelationDataCacheValue", key="#userRelation.getUserid()")
	})
	public int updateUserRelation(UserRelation userRelation) {
		return userRelationService.update(userRelation);
	}

	@Override
	@Cacheable(value="#root.target.IMServiceGroupDataCacheValue")
	public InstantGroup getIMGroupById(int gid) {
		return instantGroupService.selectOne(gid);
	}

	@Override
	public int insertIMGroup(InstantGroup group) {
		return instantGroupService.insert(group);
	}

	@Override
	@CacheEvict(value="#root.target.IMServiceGroupDataCacheValue", key="#group.getGroupid()")
	public int updateIMGroup(InstantGroup group) {	
		return instantGroupService.update(group);
	}

	@Override
	@Cacheable(value="#root.target.IMServiceUserDetailCacheValue")
	public InstantUserDetail getIMUserDetail(int userId) {
		return instantUserDetailService.selectOne(userId);
	}

	@Override
	@CacheEvict(value="#root.target.IMServiceUserDetailCacheValue", key="#userDetail.getUserid()")
	public int insertIMUserDetail(InstantUserDetail userDetail) {
		return instantUserDetailService.insert(userDetail);
	}

	@Override
	@Caching(evict= {
	  @CacheEvict(value="#root.target.IMServiceUserDetailCacheValue", key="#userDetail.getUserid()"),
	  @CacheEvict(value="#root.target.IMServiceUserDataCacheValue", key="#userDetail.getUserid()"),
	  @CacheEvict(value="#root.target.IMServiceRelationDataCacheValue", key="#userDetail.getUserid()")
	})
	public int updateIMUserDetail(InstantUserDetail userDetail) {
		return instantUserDetailService.update(userDetail);
	}

	@Override
	@Cacheable(value="#root.target.IMServiceUserDataCacheValue")
	public InstantUser getIMUserByUserId(int userId) {
		return instantUserService.selectOne(userId);
	}

	@Override
	@CacheEvict(value="#root.target.IMServiceUserDataCacheValue", key="#user.getUserid()")
	public int insertIMUser(InstantUser user) {
		return instantUserService.insert(user);
	}

	@Override
	@Caching(evict= {
		@CacheEvict(value="#root.target.IMServiceUserDetailCacheValue", key="#user.getUserid()"),
	    @CacheEvict(value="#root.target.IMServiceUserDataCacheValue", key="#user.getUserid()")
	})
	public int updateIMUser(InstantUser user) {
		return instantUserService.update(user);
	}

}
