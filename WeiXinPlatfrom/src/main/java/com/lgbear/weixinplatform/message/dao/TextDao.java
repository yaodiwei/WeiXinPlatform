package com.lgbear.weixinplatform.message.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lgbear.weixinplatform.message.domain.Text;
import com.lgbear.weixinplatform.message.domain.TextCriteria;
import com.lgbear.weixinplatform.user.domain.User;



@Repository
public interface TextDao {

	public void insert(Text text);

	public List<Text> findByOpeatorId(@Param("operatorId") String operatorId, @Param("offset") int offset, @Param("limit") int endset);
	
	public int countByOpeatorId(@Param("operatorId") String operatorId);
	
	public List<Text> findByOpenIdAndOpeatorId(@Param("openId") String openId, @Param("operatorId") String operatorId);
	
	public int countByOpenIdAndOpeatorId(@Param("openId") String openId, @Param("operatorId") String operatorId);
	
	public List<Text> findByCriteria(@Param("criteria") TextCriteria criteria, @Param("offset") int offset, @Param("limit") int limit);
	
	public int countByCriteria(@Param("criteria") TextCriteria criteria);

	public void deleteByMsgId(@Param("msgId") String msgId);

	public void delete(@Param("userCode") String userCode);
	
	public void deleteByTextCode(@Param("userCode") String userCode);
	
	public int findLastMsgTimeByOpenId(@Param("openId") String openId, @Param("operatorId") String operatorId);
	
}
