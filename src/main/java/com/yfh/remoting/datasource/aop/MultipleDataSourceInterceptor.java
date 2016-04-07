package com.yfh.remoting.datasource.aop;

import java.lang.reflect.Proxy;
import java.util.Map;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

@Component
@Aspect
@Order(-1000)
public class MultipleDataSourceInterceptor {
  
	protected static Logger log = LoggerFactory.getLogger(MultipleDataSourceInterceptor.class);
	
	@Resource(name="dataSourcePathNameMap")
	private Map<String,String> dataSourcePathNameMap;
	
	@Pointcut("execution(* com.yfh.store.common.CommonOperation.*(..)) || execution(* com.yfh.store.mysql..*Mapper.*(..))")
	private void aspectData(){
		
	}
	
	@Before("aspectData()")
	public void dynamicSetDataSource(JoinPoint joinPoint) throws Exception{
		Class<?> clz = joinPoint.getTarget().getClass();
		String fullClassName = clz.getName();
		log.info("getTarget.getClass().getName()-->:" + fullClassName);
		//proxy from two kinds, Interface/Cglib;
		if(Proxy.isProxyClass(clz) || ClassUtils.isCglibProxyClass(clz)){
			fullClassName = joinPoint.getSignature().getDeclaringTypeName();
			log.info("getSignature().getDeclaringTypeName()-->:" + fullClassName);
		}
		
		String methodName = joinPoint.getSignature().getName();
		
		log.info("final Class + Method:  " + fullClassName + " : " + methodName);
		
		boolean found = false;
		for(String key : dataSourcePathNameMap.keySet()){
			if(fullClassName.contains(key)){
				DataSourceContextHolder.setDataSource(dataSourcePathNameMap.get(key));
				found = true;
				log.info("switch datasource key:" + key + " fullClassName:" + fullClassName);
				break;
			}
		}
		
		if(!found){
			DataSourceContextHolder.clearDataSource();
		}
	}
	
	
	
}
