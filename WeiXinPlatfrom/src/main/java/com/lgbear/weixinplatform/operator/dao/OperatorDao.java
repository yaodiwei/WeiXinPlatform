package com.lgbear.weixinplatform.operator.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lgbear.weixinplatform.operator.domain.Operator;



@Repository
public interface OperatorDao {

	public Operator find(String loginName);

	public void update(Operator operator);

}
