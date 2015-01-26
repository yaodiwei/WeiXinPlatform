package com.lgbear.weixinplatform.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lgbear.weixinplatform.user.domain.User;
import com.lgbear.weixinplatform.user.domain.UserCriteria;



@Repository
public interface UserDao {

	public void insert(User user);

	public User findByOpenId(String openId);
	
	public String findOpenId(String openId);
	
	public void update(User user);

	public List<User> findByCriteria(@Param("criteria") UserCriteria criteria, @Param("offset") int offset, @Param("limit") int limit);

	public int countByCriteria(@Param("criteria") UserCriteria criteria);

	public void delete(String userCode);
	
	public void deleteByUserCode(String userCode);

	public List<User> find48HourUser(@Param("currentTime") int currentTime, @Param("operatorId") String operatorId);
}
