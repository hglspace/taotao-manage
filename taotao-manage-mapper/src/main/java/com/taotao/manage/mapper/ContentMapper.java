package com.taotao.manage.mapper;

import java.util.List;

import com.taotao.manage.pojo.Content;

public interface ContentMapper {

	Integer saveContent(Content content);

	List<Content> queryByCatId(Long catetoryId);

}
