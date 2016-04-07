package com.yfh.remoting.mq;

import org.springframework.beans.factory.annotation.Autowired;

import com.yfh.remoting.protobuf.PlatformCrossMessage.ProtoMessage;
import com.yfh.remoting.queue.client.GenericMQMessageListener;
import com.yfh.remoting.service.impl.IMMsgStoreServiceImpl;

public class IMMQMsgListener implements GenericMQMessageListener {

	@Autowired
	private IMMsgStoreServiceImpl imMsgStoreServiceImpl;
	
	@Override
	public void handleMsg(Object msg) {
		if(msg instanceof ProtoMessage){
			imMsgStoreServiceImpl.storeMsg((ProtoMessage)msg);
		}
		
	}

}
