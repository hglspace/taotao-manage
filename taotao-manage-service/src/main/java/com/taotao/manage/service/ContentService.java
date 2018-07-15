package com.taotao.manage.service;

import com.taotao.common.bean.EasyUIResult;
import com.taotao.manage.pojo.Content;

public interface ContentService {

	Integer saveContent(Content content);

	EasyUIResult queryByCatId(Long catetoryId, Integer page, Integer rows);

}
