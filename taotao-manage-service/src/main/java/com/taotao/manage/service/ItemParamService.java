package com.taotao.manage.service;

import com.taotao.common.bean.EasyUIResult;
import com.taotao.manage.pojo.ItemParam;

public interface ItemParamService {

	Integer queryById(Long ItemCatId);

	Integer saveItemParam(ItemParam itemParam);

	EasyUIResult queryList(Integer pageNum, Integer pageSize);

	ItemParam queryByCatId(Long itemCatId);

}
