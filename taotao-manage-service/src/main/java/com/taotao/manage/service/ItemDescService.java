package com.taotao.manage.service;

import com.taotao.manage.pojo.ItemDesc;

public interface ItemDescService {

	Integer sava(ItemDesc itemDesc);

	ItemDesc queryDesc(String itemId);

	Integer update(ItemDesc itemDesc);

}
