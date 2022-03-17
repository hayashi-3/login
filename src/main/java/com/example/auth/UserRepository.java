package com.example.auth;

import org.apache.ibatis.annotations.Mapper;

//DIコンテナが探す
@Mapper
public interface UserRepository {
	public User identifyUser(String email);
}
