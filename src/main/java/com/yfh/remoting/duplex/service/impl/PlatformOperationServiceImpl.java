package com.yfh.remoting.duplex.service.impl;

import java.util.List;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.yfh.store.mysql.platform.entities.ApplicationCategory;
import com.yfh.store.mysql.platform.entities.ApplicationEntry;
import com.yfh.store.mysql.platform.entities.ApplicationPrice;
import com.yfh.store.mysql.platform.entities.ChinaCity;
import com.yfh.store.mysql.platform.entities.ChinaDistrict;
import com.yfh.store.mysql.platform.entities.ChinaProvince;
import com.yfh.store.mysql.platform.entities.Country;
import com.yfh.store.mysql.platform.entities.DataVersion;
import com.yfh.store.mysql.platform.entities.IndustryCategory;
import com.yfh.store.mysql.platform.entities.IndustryGeneralCategory;
import com.yfh.store.mysql.platform.entities.Job;
import com.yfh.store.mysql.platform.entities.UserBaseInfo;
import com.yfh.store.mysql.platform.entities.UserBaseInfoCriteria;
import com.yfh.store.mysql.platform.entities.UserRole;
import com.yfh.store.mysql.platform.service.PlatformOperationService;
import com.yfh.store.mysql.platform.service.impl.UserBaseInfoService;

public class PlatformOperationServiceImpl extends ServiceBase implements PlatformOperationService{

	
	@Override
	@Cacheable(value="#root.target.PlatformCommonCacheValue", key="#root.target.Platform_AppCategories_KEY")
	public List<ApplicationCategory> getAppCategories() {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	@Cacheable(value="#root.target.PlatformCommonCacheValue", key="#root.target.Platform_AppEntries_KEY")
	public List<ApplicationEntry> getAppEntries() {
		// TODO Auto-generated method stub
		return null;
	}
	


	@Override
	@Cacheable(value="#root.target.PlatformCommonCacheValue", key="#root.target.Platform_AppPrices_KEY")
	public List<ApplicationPrice> getAppPrices() {
		// TODO Auto-generated method stub
		return null;
	}
	


	@Override
	@Cacheable(value="#root.target.PlatformCommonCacheValue", key="#root.target.Platform_Countries_KEY")
	public List<Country> getCountries() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	@Cacheable(value="#root.target.PlatformCommonCacheValue", key="#root.target.Platform_ChinaProvinces_KEY")
	public List<ChinaProvince> getChinaProvinces() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	@Cacheable(value="#root.target.PlatformCommonCacheValue", key="#root.target.Platform_ChinaCities_KEY")
	public List<ChinaCity> getChinaCities() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	@Cacheable(value="#root.target.PlatformCommonCacheValue", key="#root.target.Platform_ChinaDistricts_KEY")
	public List<ChinaDistrict> getChinaDistricts() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	@Cacheable(value="#root.target.PlatformCommonCacheValue", key="#root.target.Platform_IndustryGeneralCategories_KEY")
	public List<IndustryGeneralCategory> getIndustryGeneralCategories() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	@Cacheable(value="#root.target.PlatformCommonCacheValue", key="#root.target.Platform_IndustryCategories_KEY")
	public List<IndustryCategory> getIndustryCategories() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	@Cacheable(value="#root.target.PlatformCommonCacheValue", key="#root.target.Platform_Jobs_KEY")
	public List<Job> getJobs() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	@Cacheable(value="#root.target.PlatformCommonCacheValue", key="#root.target.Platform_UserRoles_KEY")
	public List<UserRole> getUserRoles() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	@Cacheable(value="#root.target.PlatformCommonCacheValue", key="#root.target.Platform_DataVersions_KEY")
	public List<DataVersion> getDataVersions() {
		
		return dataVersionService.selectAll();
	}
	

	@Override
	@Cacheable(value="#root.target.PlatformUserBaseInfoCacheValue")
	public UserBaseInfo getUserBaseInfo(int userId) {
		
		return userBaseInfoService.selectOne(userId);
	}

	@Override
	public int createUserBaseInfo(UserBaseInfo userInfo) {
		return userBaseInfoService.insert(userInfo);
	}

	@Override
	@CachePut(value="#root.target.PlatformUserBaseInfoCacheValue", key="#userInfo.getUserid()")
	public int updateUserBaseInfo(UserBaseInfo userInfo) {
		return userBaseInfoService.update(userInfo);
	}

	@Override
	public UserBaseInfo getLogin(String mobileNum, String pwd) {
		
		UserBaseInfo info = null;
		if(!mobileNum.isEmpty() && !pwd.isEmpty()){
			UserBaseInfoCriteria infoCriteria = new UserBaseInfoCriteria();
			infoCriteria.createCriteria().andMobilenumberEqualTo(mobileNum).andPasswordEqualTo(pwd);
			List<UserBaseInfo> infos = userBaseInfoService.selectByExample(infoCriteria);
			//for safe; mobileNum should be unique;
			if(infos != null && infos.size() == 1){
				info = infos.get(0);
			}
		}
		
		return info;
	}

}
