package com.yfh.remoting.datasource.aop;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultipleDataSource extends AbstractRoutingDataSource {

	@Override
	public Object determineCurrentLookupKey() {
		
		return DataSourceContextHolder.getDataSource();
	}

}
