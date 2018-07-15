package com.taotao.manage.mapper;

import org.apache.ibatis.annotations.Param;

import com.taotao.manage.pojo.ItemDesc;

public interface ItemDescMapper {

	Integer sava(ItemDesc itemDesc);

	ItemDesc queryDescById(@Param("itemId")String itemId);

	Integer update(ItemDesc itemDesc);

}
