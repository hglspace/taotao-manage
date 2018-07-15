package com.taotao.manage.mapper;

import java.util.List;

import com.taotao.manage.pojo.ItemParam;

public interface ItemParamMapper {

	Integer queryById(Long ItemCatId);

	Integer saveItemParam(ItemParam itemParam);

	List<ItemParam> queryList();

	ItemParam queryByCatId(Long itemCatId);

}
