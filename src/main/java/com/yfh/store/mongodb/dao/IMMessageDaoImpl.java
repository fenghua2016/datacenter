package com.yfh.store.mongodb.dao;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.WriteResult;
import com.yfh.remoting.protobuf.PlatformCrossMessage.ProtoMessage;

public class IMMessageDaoImpl implements CommonDao<ProtoMessage> {
	private MongoOperations mongoOps;
	private static final String COLLECTION = "IMMessage";
	
	public IMMessageDaoImpl(MongoOperations mongoOps){
		this.mongoOps=mongoOps;
	}
	
	@Override
	public void create(ProtoMessage t) {
		this.mongoOps.insert(t, COLLECTION);	
	}

	@Override
	public ProtoMessage readById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		return this.mongoOps.findOne(query, ProtoMessage.class, COLLECTION);
	}

	@Override
	public void update(ProtoMessage t) {
		this.mongoOps.save(t, COLLECTION);	
	}

	@Override
	public int deleteById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		WriteResult result = this.mongoOps.remove(query, ProtoMessage.class, COLLECTION);
		return result.getN();
	}


	
}
