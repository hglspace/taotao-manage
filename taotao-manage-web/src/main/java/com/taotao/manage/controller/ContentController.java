package com.taotao.manage.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.common.bean.EasyUIResult;
import com.taotao.manage.pojo.Content;
import com.taotao.manage.service.ContentService;


@RequestMapping("content")
@Controller
public class ContentController {

	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> saveContent(Content content){
		try{
			Integer count = this.contentService.saveContent(content);
			if(count.intValue() == 1){
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<EasyUIResult> queryByCatId(@RequestParam("categoryId")Long catetoryId,
			@RequestParam("page") Integer page,
			@RequestParam("rows") Integer rows){
		
		try{
			EasyUIResult easyUIResult = this.contentService.queryByCatId(catetoryId,page,rows);
			if(easyUIResult !=null){
				return ResponseEntity.status(HttpStatus.OK).body(easyUIResult);
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}catch(Exception e){
			
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
}
