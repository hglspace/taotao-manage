package com.taotao.manage.service;

import java.util.List;

import com.taotao.common.bean.ItemCatResult;
import com.taotao.manage.pojo.ItemCat;

public interface ItemCatService {

	List<ItemCat> getItemCatById(Long id);

	String queryCatById(Integer id);

	ItemCatResult queryAll();

}
