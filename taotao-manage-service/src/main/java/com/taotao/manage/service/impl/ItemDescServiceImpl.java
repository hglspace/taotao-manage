package com.taotao.manage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.manage.mapper.ItemDescMapper;
import com.taotao.manage.pojo.ItemDesc;
import com.taotao.manage.service.ItemDescService;

@Service
public class ItemDescServiceImpl implements ItemDescService {

	@Autowired
	private ItemDescMapper itemDescMapper;
	@Override
	public Integer sava(ItemDesc itemDesc) {
		// TODO Auto-generated method stub
		return this.itemDescMapper.sava(itemDesc);
	}
	@Override
	public ItemDesc queryDesc(String itemId) {
		return this.itemDescMapper.queryDescById(itemId);
	}
	@Override
	public Integer update(ItemDesc itemDesc) {
		// TODO Auto-generated method stub
		return this.itemDescMapper.update(itemDesc);
	}

}
