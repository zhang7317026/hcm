package com.zrz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zrz.entity.SysUserInfoPO;

public interface SysUserInfoPOMapper {
    /**
     *
     * @mbggenerated 2018-05-18
     */
    int deleteByPrimaryKey(String id);

    /**
     *
     * @mbggenerated 2018-05-18
     */
    int insert(SysUserInfoPO record);

    /**
     *
     * @mbggenerated 2018-05-18
     */
    int insertSelective(SysUserInfoPO record);

    /**
     *
     * @mbggenerated 2018-05-18
     */
    SysUserInfoPO selectByPrimaryKey(String id);

    /**
     *
     * @mbggenerated 2018-05-18
     */
    int updateByPrimaryKeySelective(SysUserInfoPO record);

    /**
     *
     * @mbggenerated 2018-05-18
     */
    int updateByPrimaryKey(SysUserInfoPO record);
    
    /**
     * 根据账号获取PO
     */
    SysUserInfoPO getByAccount(@Param("account") String account);
    
    List<SysUserInfoPO> getAllEnabled();
}