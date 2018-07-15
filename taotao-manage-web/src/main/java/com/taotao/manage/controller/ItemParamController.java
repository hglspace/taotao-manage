package com.taotao.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.common.bean.EasyUIResult;
import com.taotao.manage.pojo.ItemParam;
import com.taotao.manage.service.ItemParamService;

@RequestMapping("item/param")
@Controller
public class ItemParamController {
	
	@Autowired
	private ItemParamService itemParamService;

	/**
	 * ResponseEntity<Void>
	 * @param ItemCatId
	 * @return
	 * description:根据商品类目id查询规格模版
	 */
	@RequestMapping(value="/{ItemCatId}",method=RequestMethod.GET)
	public ResponseEntity<Void> queryParamById(@PathVariable("ItemCatId") Long ItemCatId){
		try{
			Integer count = this.itemParamService.queryById(ItemCatId);
			if(count.intValue() == 1){
				return ResponseEntity.status(HttpStatus.OK).build();
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}catch(Exception e){
		   e.printStackTrace();	
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(value="/{ItemCatId}",method=RequestMethod.POST)
	public ResponseEntity<Void> saveItemParam(@PathVariable("ItemCatId") Long ItemCatId,@RequestParam("paramData")String paramData){
		try{
			ItemParam itemParam = new ItemParam();
			itemParam.setItemCatId(ItemCatId);
			itemParam.setParamData(paramData);
			Integer count = this.itemParamService.saveItemParam(itemParam);
			if(count.intValue() == 1){
				return ResponseEntity.status(HttpStatus.CREATED).build();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ResponseEntity<EasyUIResult> queryList(@RequestParam(value="page",defaultValue="1")Integer pageNum,
			@RequestParam(value="rows",defaultValue="30")Integer pageSize){
		try{
			EasyUIResult easyUIResult = this.itemParamService.queryList(pageNum,pageSize);
			if(easyUIResult!=null){
				return ResponseEntity.ok(easyUIResult);
			}else{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	@RequestMapping(value="/query/itemcatid/{itemCatId}",method=RequestMethod.GET)
	public ResponseEntity<ItemParam> queryByCatId(@PathVariable("itemCatId")Long itemCatId){
		try{
			ItemParam itemParam = this.itemParamService.queryByCatId(itemCatId);
			if(itemParam!=null){
				return ResponseEntity.status(HttpStatus.OK).body(itemParam);
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}catch(Exception e){
			
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
}
