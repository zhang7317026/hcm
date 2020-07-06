package com.zrz.mapper;

import java.util.List;

import com.zrz.entity.PositionListPO;

public interface PositionListPOMapper {
    /**
     *
     * @mbggenerated 2019-09-29
     */
    int deleteByPrimaryKey(String id);

    /**
     *
     * @mbggenerated 2019-09-29
     */
    int insert(PositionListPO record);

    /**
     *
     * @mbggenerated 2019-09-29
     */
    int insertSelective(PositionListPO record);

    /**
     *
     * @mbggenerated 2019-09-29
     */
    PositionListPO selectByPrimaryKey(String id);

    /**
     *
     * @mbggenerated 2019-09-29
     */
    int updateByPrimaryKeySelective(PositionListPO record);

    /**
     *
     * @mbggenerated 2019-09-29
     */
    int updateByPrimaryKey(PositionListPO record);
    
    
    /**
	 * 获取所有list
	 */
	List<PositionListPO> getAllList();
}