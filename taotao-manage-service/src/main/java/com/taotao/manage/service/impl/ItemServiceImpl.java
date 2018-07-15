package com.taotao.manage.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.taotao.common.bean.EasyUIResult;
import com.taotao.manage.mapper.ItemMapper;
import com.taotao.manage.pojo.Item;
import com.taotao.manage.pojo.ItemDesc;
import com.taotao.manage.pojo.ItemParamItem;
import com.taotao.manage.service.ItemDescService;
import com.taotao.manage.service.ItemParamItemService;
import com.taotao.manage.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private ItemDescService itemDescService;
	
	@Autowired
	private ItemParamItemService itemParamItemService;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	private final static ObjectMapper MAPPER = new ObjectMapper();
	
	/* (non-Javadoc)
	 * @see com.taotao.manage.service.ItemService#sava(com.taotao.manage.pojo.Item, java.lang.String, java.lang.String)
	 * 保存商品
	 */
	@Override
	public Boolean sava(Item item,String desc,String itemParams) {
		item.setId(null);
		item.setStatus(1);
		Integer count1 = this.itemMapper.sava(item);
		if(count1.intValue()!=1){
			throw new RuntimeException("商品添加失败:"+item);
		}
		ItemDesc itemDesc = new ItemDesc();
    	itemDesc.setItemId(item.getId());
    	itemDesc.setItemDesc(desc);
    	Integer count2 = this.itemDescService.sava(itemDesc);
    	if(count2.intValue() != 1){
    		throw new RuntimeException("商品描述添加失败:"+itemDesc);
    	}
    	
    	ItemParamItem itemParamItem = new ItemParamItem();
    	itemParamItem.setItemId(item.getId());
    	itemParamItem.setParamData(itemParams);
    	Integer count3 = this.itemParamItemService.save(itemParamItem);
    	if(count3.intValue()!=1){
    		throw new RuntimeException("商品规格添加失败:"+itemParamItem);
    	}
    	if(count1 == 1 && count2 == 1 && count3 == 1){
    		this.sendMsg(item.getId(), "save");
    		return true;
    	}
    	return false;
	}
	
	
	/* (non-Javadoc)
	 * @see com.taotao.manage.service.ItemService#queryPage(java.lang.Integer, java.lang.Integer)
	 *  分页查询商品
	 */
	@Override
	public EasyUIResult queryPage(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Item> list =this.itemMapper.queryPage();
		EasyUIResult easyUIResult = new EasyUIResult();
		easyUIResult.setRows(list);
		easyUIResult.setTotal(Long.valueOf(list.size()+""));
		return easyUIResult;
	}
	
	
	/* (non-Javadoc)
	 * @see com.taotao.manage.service.ItemService#updateItem(com.taotao.manage.pojo.Item, java.lang.String, java.lang.String)
	 * 
	 * 更新商品
	 */
	@Override
	public Boolean updateItem(Item item, String desc,String itemParams) {
		item.setUpdated(new Date());
        Integer count1 = this.itemMapper.update(item);
        if(count1.intValue()!=1){
        	throw new RuntimeException("商品更新失败:"+item);
        }
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(item.getId());
        itemDesc.setItemDesc(desc);
        itemDesc.setUpdated(new Date());
        Integer count2 = this.itemDescService.update(itemDesc);
        if(count2.intValue()!=1){
        	throw new RuntimeException("商品描述更新失败:"+itemDesc);
        }
        
        ItemParamItem itemParamItem = new ItemParamItem();
        itemParamItem.setItemId(item.getId());
        itemParamItem.setParamData(itemParams);
        Integer count3 = this.itemParamItemService.update(itemParamItem);
        if(count3.intValue()!=1){
        	throw new RuntimeException("商品规格更新失败:"+itemParamItem);
        }
        if(count1 == 1 && count2 == 1 && count3.intValue() == 1){
			this.sendMsg(item.getId(), "update");
			return true;
        }
		return false;
	}
	
	
	/* (non-Javadoc)
	 * @see com.taotao.manage.service.ItemService#queryItemById(java.lang.Long)
	 * 根据商品Id查询商品
	 */
	@Override
	public Item queryItemById(Long itemId) {
		return this.itemMapper.queryItemById(itemId);
	}
	
	/**
	 * void
	 * @param itemId
	 * @param type
	 * description:rabbit发送消息
	 */
	private void sendMsg(Long itemId,String type){
		try{
			Map<String,Object> msg = new HashMap<String,Object>();
			msg.put("type", type);
			msg.put("itemId", itemId);
			msg.put("data", System.currentTimeMillis());
			this.rabbitTemplate.convertAndSend("item."+type, MAPPER.writeValueAsString(msg));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

}
