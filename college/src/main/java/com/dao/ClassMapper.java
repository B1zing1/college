package com.dao;

import com.bean.Class;
import com.bean.Major;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ClassMapper {

    int deleteByPrimaryKey(Integer classid);

    int insert(Class record);

    int insertSelective(Class record);

    Class selectByPrimaryKey(Integer classid);

    int updateByPrimaryKeySelective(Class record);

    int updateByPrimaryKey(Class record);

    //查询所有班级信息
    List<Class> findAllClass(Map map);
    //查询指定班级信息
    Class findClassById(int classid);

    //根据专业id查询包含的班级
    List<Class> findClassByMajorid(int majorid);
    //根据学院id查询包含专业和班级
    List findMajorClassByDeptid(int departid);

}
