package com.taotao.manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.common.bean.EasyUIResult;
import com.taotao.manage.pojo.Item;
import com.taotao.manage.pojo.ItemCat;
import com.taotao.manage.pojo.ItemDesc;
import com.taotao.manage.service.ItemCatService;
import com.taotao.manage.service.ItemDescService;
import com.taotao.manage.service.ItemService;

@RequestMapping("item")
@Controller
public class ItemController {

	@Autowired
	private ItemCatService itemCatService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ItemDescService itemDescService;
	
	//前台要求传回json数据，定义json处理对象,将java对象转成json返回
	private static final ObjectMapper mapper = new ObjectMapper();

	/**
	 * ResponseEntity<List<ItemCat>>
	 * @param id
	 * @return
	 * description:查询类目
	 */
	@RequestMapping(value="/cat/list")
	public ResponseEntity<List<ItemCat>> queryItemCats(@RequestParam(value="id",defaultValue="0") Long id){
		
		try {
			List<ItemCat> itemCatList = this.itemCatService.getItemCatById(id);
			if(itemCatList == null || itemCatList.isEmpty()){
				//没找到就 404
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.ok(itemCatList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	/**
	 * ResponseEntity<Void>
	 * @param item
	 * @param desc
	 * @return
	 * description:保存商品
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> savaItem(Item item,@RequestParam(value="desc")String desc,@RequestParam("itemParams")String itemParams){
	    try{
	    	Boolean flag = this.itemService.sava(item,desc,itemParams);
	    	if(flag){
	    		return ResponseEntity.status(HttpStatus.CREATED).build();
	    	}
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		
	}

	/**
	 * ResponseEntity<EasyUIResult>
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * description:分页查询商品
	 */
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<EasyUIResult> queryPage(@RequestParam(value="page",defaultValue="1")Integer pageNum,
			@RequestParam(value="rows",defaultValue="30")Integer pageSize){
		try{
			EasyUIResult easyUIResult = this.itemService.queryPage(pageNum,pageSize);
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
	
//	@RequestMapping(value="/desc/{itemId}")
//	public ResponseEntity<ItemDesc> queryPageDesc(@PathVariable("itemId")String itemId){
//		try{
//			ItemDesc itemDesc = this.itemDescService.queryDesc(itemId);
//			if(itemDesc!=null){
//				return ResponseEntity.status(HttpStatus.OK).body(itemDesc);
//			}
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//	}
	
	/**
	 * String
	 * @param itemId
	 * @return
	 * description:查询商品的描述
	 */
	@RequestMapping(value="/desc/{itemId}")
	@ResponseBody
	public String queryDesc(@PathVariable("itemId")String itemId){
		try{
			ItemDesc itemDesc = this.itemDescService.queryDesc(itemId);
			return mapper.writeValueAsString(itemDesc);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * String
	 * @param id
	 * @return
	 * description:根据商品的cid查询商品的类目名称
	 */
	@RequestMapping(value="/querycat",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String queryCatByCid(@RequestParam("id")Integer id){
		try{
			Map<String,String> m = new HashMap<String,String>();
			String name=this.itemCatService.queryCatById(id);
			m.put("name", name);
			return mapper.writeValueAsString(m);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * ResponseEntity<Void>
	 * @return
	 * description:更新商品
	 */
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Void> update(Item item,@RequestParam("desc")String desc,@RequestParam("itemParams")String itemParams){
		try{
			Boolean flag = this.itemService.updateItem(item,desc,itemParams);
			if(flag){
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
