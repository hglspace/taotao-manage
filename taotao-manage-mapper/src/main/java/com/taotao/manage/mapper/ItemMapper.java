package com.taotao.manage.mapper;


import java.util.List;

import com.taotao.common.bean.EasyUIResult;
import com.taotao.manage.pojo.Item;

public interface ItemMapper {

	public Integer sava(Item item);

	public List<Item> queryPage();

	public Integer update(Item item);

	public Item queryItemById(Long itemId);
}
