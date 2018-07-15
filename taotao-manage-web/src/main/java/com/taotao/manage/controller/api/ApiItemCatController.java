package com.taotao.manage.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.bean.ItemCatResult;
import com.taotao.manage.service.ItemCatService;

@Controller
@RequestMapping("/api/item/cat")
public class ApiItemCatController {

	
	@Autowired
	private ItemCatService itemCatService;
	
	@RequestMapping(value="/all",method=RequestMethod.GET)
	@ResponseBody
	public ItemCatResult queryAllCat(){
		ItemCatResult itemCatResult = this.itemCatService.queryAll();
		System.out.println(itemCatResult.getItemCats());
		return itemCatResult;
	}
}
