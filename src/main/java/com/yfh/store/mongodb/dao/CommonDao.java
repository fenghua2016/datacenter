package com.yfh.store.mongodb.dao;

public interface CommonDao<T> {
    void create(T t);
    
	T readById(String id);
	
	void update(T t);
	
	int deleteById(String id);
}
