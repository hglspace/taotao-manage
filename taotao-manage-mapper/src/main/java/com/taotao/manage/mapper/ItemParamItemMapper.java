package com.taotao.manage.mapper;

import org.apache.ibatis.annotations.Param;

import com.taotao.manage.pojo.ItemParamItem;

public interface ItemParamItemMapper {

	Integer save(ItemParamItem itemParamItem);

	ItemParamItem queryByItemId(@Param("itemId")Long itemId);

	Integer update(ItemParamItem itemParamItem);

}
