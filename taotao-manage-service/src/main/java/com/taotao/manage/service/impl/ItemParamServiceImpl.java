package com.taotao.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.taotao.common.bean.EasyUIResult;
import com.taotao.manage.mapper.ItemParamMapper;
import com.taotao.manage.pojo.Item;
import com.taotao.manage.pojo.ItemParam;
import com.taotao.manage.service.ItemParamService;

@Service
public class ItemParamServiceImpl implements ItemParamService {
    
	@Autowired
	private ItemParamMapper itemParamMapper;
	@Override
	public Integer queryById(Long ItemCatId) {
         
		return this.itemParamMapper.queryById(ItemCatId);
	}
	@Override
	public Integer saveItemParam(ItemParam itemParam) {
		// TODO Auto-generated method stub
		return this.itemParamMapper.saveItemParam(itemParam);
	}
	@Override
	public EasyUIResult queryList(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<ItemParam> list =this.itemParamMapper.queryList();
		EasyUIResult easyUIResult = new EasyUIResult();
		easyUIResult.setRows(list);
		easyUIResult.setTotal(Long.valueOf(list.size()+""));
		return easyUIResult;
	}
	@Override
	public ItemParam queryByCatId(Long itemCatId) {

		return this.itemParamMapper.queryByCatId(itemCatId);
	}

}
