package com.lxl.trade.mapper;

import com.lxl.trade.entity.GoodsNumberLog;
import com.lxl.trade.entity.GoodsNumberLogExample;
import com.lxl.trade.entity.GoodsNumberLogKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsNumberLogMapper {
    int countByExample(GoodsNumberLogExample example);

    int deleteByExample(GoodsNumberLogExample example);

    int deleteByPrimaryKey(GoodsNumberLogKey key);

    int insert(GoodsNumberLog record);

    int insertSelective(GoodsNumberLog record);

    List<GoodsNumberLog> selectByExample(GoodsNumberLogExample example);

    GoodsNumberLog selectByPrimaryKey(GoodsNumberLogKey key);

    int updateByExampleSelective(@Param("record") GoodsNumberLog record, @Param("example") GoodsNumberLogExample example);

    int updateByExample(@Param("record") GoodsNumberLog record, @Param("example") GoodsNumberLogExample example);

    int updateByPrimaryKeySelective(GoodsNumberLog record);

    int updateByPrimaryKey(GoodsNumberLog record);
}