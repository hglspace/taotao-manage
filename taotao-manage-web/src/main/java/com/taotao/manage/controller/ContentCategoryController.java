package com.taotao.manage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.manage.pojo.ContentCategory;
import com.taotao.manage.service.ContentCategoryService;

@RequestMapping("/content/category")
@Controller
public class ContentCategoryController {

	private final static ObjectMapper MAPPER = new ObjectMapper();
	@Autowired
	private ContentCategoryService contentCategoryService;
	
	/**
	 * ResponseEntity<List<ContentCategory>>
	 * @param pid
	 * @return
	 * description:
	 */
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ContentCategory>> queryByParentId(@RequestParam(value="id",defaultValue="0") Long pid){
		try{
			List<ContentCategory> list = this.contentCategoryService.queryByPid(pid);
			if(list == null || list.isEmpty()){
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.ok(list);
		}catch(Exception e){
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ContentCategory> saveContentCategory(ContentCategory contentCategory)throws Exception{
		try{
			Boolean count = this.contentCategoryService.saveContentCategory(contentCategory);
			if(count){
				return ResponseEntity.status(HttpStatus.CREATED).body(contentCategory);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> updateContentCategory(ContentCategory contentCategory){
		try{
			Integer count = this.contentCategoryService.updateContentCategory(contentCategory);
			if(count.intValue() == 1){
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteContentCategory(ContentCategory contentCategory){
		try{
			Boolean flag = this.contentCategoryService.deleteContentCategory(contentCategory);
			if(flag){
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}