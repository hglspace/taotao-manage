package com.taotao.manage.service;

import java.util.List;

import com.taotao.manage.pojo.ContentCategory;

public interface ContentCategoryService {

	List<ContentCategory> queryByPid(Long pid);

	Boolean saveContentCategory(ContentCategory contentCategory);

	Integer updateContentCategory(ContentCategory contentCategory);

	Boolean deleteContentCategory(ContentCategory contentCategory);

}
