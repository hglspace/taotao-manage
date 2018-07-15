package com.taotao.manage.mapper;

import java.util.List;

import com.taotao.manage.pojo.ItemCat;

public interface ItemCatMapper {

	public List<ItemCat> queryById(ItemCat record);

	public String queryCatById(Integer id);

	public List<ItemCat> queryAll();
}
