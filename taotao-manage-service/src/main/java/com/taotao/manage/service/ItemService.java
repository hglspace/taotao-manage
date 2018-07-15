package com.taotao.manage.service;

import com.taotao.common.bean.EasyUIResult;
import com.taotao.manage.pojo.Item;

public interface ItemService {

	Boolean sava(Item item,String desc, String itemParams);

	EasyUIResult queryPage(Integer pageNum, Integer pageSize);

	Boolean updateItem(Item item, String desc, String itemParams);

	Item queryItemById(Long itemId);

}
