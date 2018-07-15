package com.taotao.manage.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.manage.pojo.Item;
import com.taotao.manage.pojo.ItemDesc;
import com.taotao.manage.service.ItemCatService;
import com.taotao.manage.service.ItemDescService;
import com.taotao.manage.service.ItemService;

@RequestMapping("api/item")
@Controller
public class ApiItemController {

	@Autowired
	private ItemCatService itemCatService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ItemDescService itemDescService;
	
	//前台要求传回json数据，定义json处理对象,将java对象转成json返回
	private static final ObjectMapper mapper = new ObjectMapper();


	@RequestMapping(value="/desc/{itemId}",method=RequestMethod.GET)
	public ResponseEntity<ItemDesc> queryPageDesc(@PathVariable("itemId")String itemId){
		try{
			ItemDesc itemDesc = this.itemDescService.queryDesc(itemId);
			if(itemDesc!=null){
				return ResponseEntity.status(HttpStatus.OK).body(itemDesc);
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}catch(Exception e){
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	
	@RequestMapping(value="{itemId}",method=RequestMethod.GET)
	public ResponseEntity<Item> queryById(@PathVariable("itemId")Long itemId){
		try{
			Item item = this.itemService.queryItemById(itemId);
			if(null == item){
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.status(HttpStatus.OK).body(item);
		}catch(Exception e){
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
}
