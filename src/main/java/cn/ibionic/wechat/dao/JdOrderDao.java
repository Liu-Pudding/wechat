package cn.ibionic.wechat.dao;

import cn.ibionic.wechat.entity.JdOrder;
import cn.ibionic.wechat.entity.JdOrderExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface JdOrderDao {
    long countByExample(JdOrderExample example);

    int deleteByExample(JdOrderExample example);

    int deleteByPrimaryKey(String id);

    int insert(JdOrder record);

    int insertSelective(JdOrder record);

    List<JdOrder> selectByExample(JdOrderExample example);

    JdOrder selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") JdOrder record, @Param("example") JdOrderExample example);

    int updateByExample(@Param("record") JdOrder record, @Param("example") JdOrderExample example);

    int updateByPrimaryKeySelective(JdOrder record);

    int updateByPrimaryKey(JdOrder record);
}