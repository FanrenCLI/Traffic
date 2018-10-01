package com.fanren.Traffic.DAO;

import com.fanren.Traffic.pojo.Change_Point;
import com.fanren.Traffic.pojo.Change_PointExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Change_PointMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table change_point
     *
     * @mbg.generated Sat Sep 29 20:53:43 CST 2018
     */
    long countByExample(Change_PointExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table change_point
     *
     * @mbg.generated Sat Sep 29 20:53:43 CST 2018
     */
    int deleteByExample(Change_PointExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table change_point
     *
     * @mbg.generated Sat Sep 29 20:53:43 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table change_point
     *
     * @mbg.generated Sat Sep 29 20:53:43 CST 2018
     */
    int insert(Change_Point record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table change_point
     *
     * @mbg.generated Sat Sep 29 20:53:43 CST 2018
     */
    int insertSelective(Change_Point record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table change_point
     *
     * @mbg.generated Sat Sep 29 20:53:43 CST 2018
     */
    List<Change_Point> selectByExample(Change_PointExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table change_point
     *
     * @mbg.generated Sat Sep 29 20:53:43 CST 2018
     */
    Change_Point selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table change_point
     *
     * @mbg.generated Sat Sep 29 20:53:43 CST 2018
     */
    int updateByExampleSelective(@Param("record") Change_Point record, @Param("example") Change_PointExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table change_point
     *
     * @mbg.generated Sat Sep 29 20:53:43 CST 2018
     */
    int updateByExample(@Param("record") Change_Point record, @Param("example") Change_PointExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table change_point
     *
     * @mbg.generated Sat Sep 29 20:53:43 CST 2018
     */
    int updateByPrimaryKeySelective(Change_Point record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table change_point
     *
     * @mbg.generated Sat Sep 29 20:53:43 CST 2018
     */
    int updateByPrimaryKey(Change_Point record);
}