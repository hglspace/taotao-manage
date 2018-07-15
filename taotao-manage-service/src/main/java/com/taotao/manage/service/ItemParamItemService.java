package com.taotao.manage.service;

import com.taotao.manage.pojo.ItemParamItem;

public interface ItemParamItemService {

	Integer save(ItemParamItem itemParamItem);

	ItemParamItem queryByItemId(Long itemId);

	Integer update(ItemParamItem itemParamItem);

}
