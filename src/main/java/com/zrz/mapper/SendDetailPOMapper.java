package com.zrz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zrz.entity.SendDetailPO;

public interface SendDetailPOMapper {
    /**
     *
     * @mbggenerated 2018-05-17
     */
    int deleteByPrimaryKey(String id);

    /**
     *
     * @mbggenerated 2018-05-17
     */
    int insert(SendDetailPO record);

    /**
     *
     * @mbggenerated 2018-05-17
     */
    int insertSelective(SendDetailPO record);

    /**
     *
     * @mbggenerated 2018-05-17
     */
    SendDetailPO selectByPrimaryKey(String id);

    /**
     *
     * @mbggenerated 2018-05-17
     */
    int updateByPrimaryKeySelective(SendDetailPO record);

    /**
     *
     * @mbggenerated 2018-05-17
     */
    int updateByPrimaryKey(SendDetailPO record);
    
    List<String> getAllDate();
    List<SendDetailPO> getListByUserIdAndTime
    	(@Param("userId") String userId, @Param("send_time") String send_time);
}