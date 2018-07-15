package com.taotao.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.taotao.manage.pojo.ContentCategory;

public interface ContentCategoryMapper {

	List<ContentCategory> queryByPid(Long pid);

	Integer saveContentCategory(ContentCategory contentCategory);

	Integer updateContentCategory(ContentCategory contentCategory);

	ContentCategory queryById(Long id);

	Integer deleteByIds(@Param("ids")List<Object> ids);

}
