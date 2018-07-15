package com.taotao.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.taotao.common.bean.EasyUIResult;
import com.taotao.manage.mapper.ContentMapper;
import com.taotao.manage.pojo.Content;
import com.taotao.manage.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private ContentMapper contentMapper;
	@Override
	public Integer saveContent(Content content) {
		
		return this.contentMapper.saveContent(content);
	}
	@Override
	public EasyUIResult queryByCatId(Long catetoryId, Integer page, Integer rows) {
		PageHelper.startPage(page, rows);
		List<Content> list = this.contentMapper.queryByCatId(catetoryId);
		EasyUIResult easyUIResult = new EasyUIResult();
		easyUIResult.setRows(list);
		easyUIResult.setTotal(Long.valueOf(list.size()+""));
		return easyUIResult;
	}

}
