package com.taotao.manage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.manage.mapper.ItemParamItemMapper;
import com.taotao.manage.pojo.ItemParamItem;
import com.taotao.manage.service.ItemParamItemService;

@Service
public class ItemParamItemServiceImpl implements ItemParamItemService{

	@Autowired
	private ItemParamItemMapper itemParamItemMapper;
	@Override
	public Integer save(ItemParamItem itemParamItem) {
		return this.itemParamItemMapper.save(itemParamItem);
	}
	@Override
	public ItemParamItem queryByItemId(Long itemId) {

		return this.itemParamItemMapper.queryByItemId(itemId);
	}
	@Override
	public Integer update(ItemParamItem itemParamItem) {
		
		return this.itemParamItemMapper.update(itemParamItem);
	}

}
