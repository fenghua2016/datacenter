package com.yfh.remoting.datacenter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DataCenter {
	
	public static ApplicationContext appContext;
	
	public static void main(String[] args) throws Exception{
		appContext = new ClassPathXmlApplicationContext("application-context.xml");
		//Start RMI Serivce;
		//Start MQ Receiver;
	}
}
