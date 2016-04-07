package com.yfh.remoting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfh.common.Serialization.FSTSerializer;
import com.yfh.common.Serialization.JsonSerializer;
import com.yfh.remoting.protobuf.PlatformCrossMessage.ProtoMessage;
import com.yfh.remoting.service.IMMsgStoreService;
import com.yfh.store.mongodb.dao.IMMessageDaoImpl;

@Service
public class IMMsgStoreServiceImpl implements IMMsgStoreService {
	
	@Autowired
	private IMMessageDaoImpl imMsgMongoDAO;
	
	@Autowired
	private FSTSerializer fstSerializer;
	
	@Autowired
	private JsonSerializer jsonSerializer;
	
	@Override
	public void storeMsg(byte[] msg) {
		storeMsg(fstSerializer.deserialize(msg, ProtoMessage.class));
	}


	@Override
	public void storeMsg(String msg) {
		storeMsg(jsonSerializer.deserialize(msg, ProtoMessage.class));
	}
	
	
	public void storeMsg(ProtoMessage msg) {
		imMsgMongoDAO.create(msg);
	}
	
	
	

}
