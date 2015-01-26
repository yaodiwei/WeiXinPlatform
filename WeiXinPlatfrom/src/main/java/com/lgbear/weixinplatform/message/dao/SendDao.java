package com.lgbear.weixinplatform.message.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lgbear.weixinplatform.message.domain.Passive;



@Repository
public interface SendDao {

	public void insert(Passive passive);

	public List<Passive> findAll(@Param("operatorId") String operatorId);
	
	public List<Passive> findEnable(@Param("operatorId") String operatorId);

	public void update(Passive passive);
	
	public void deleteByName(@Param("operatorId") String operatorId, @Param("name") String name);

}
