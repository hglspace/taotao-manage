package com.taotao.manage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.manage.mapper.ContentCategoryMapper;
import com.taotao.manage.pojo.ContentCategory;
import com.taotao.manage.service.ContentCategoryService;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private ContentCategoryMapper  contentCategoryMapper;
	
	@Override
	public List<ContentCategory> queryByPid(Long pid) {
		return this.contentCategoryMapper.queryByPid(pid);
	}

	@Override
	public Boolean saveContentCategory(ContentCategory contentCategory) {
		// TODO Auto-generated method stub
		contentCategory.setIsParent(false);
		contentCategory.setSortOrder(1);
		contentCategory.setStatus(1);
		Integer count = this.contentCategoryMapper.saveContentCategory(contentCategory);
		if(count.intValue() == 1){
			ContentCategory parent = this.contentCategoryMapper.queryById(contentCategory.getParentId());
			if(!parent.getIsParent()){
				parent.setIsParent(true);
				this.contentCategoryMapper.updateContentCategory(parent);
			}
			return true;
		}else{
			throw new RuntimeException("新增内容类目失败，但是没有异常："+contentCategory);
		}
	}

	@Override
	public Integer updateContentCategory(ContentCategory contentCategory) {
		return this.contentCategoryMapper.updateContentCategory(contentCategory);
	}

	@Override
	public Boolean deleteContentCategory(ContentCategory contentCategory) {
        Long pid = contentCategory.getParentId();
        //查询父结点，利用父结点查询其兄弟结点
        Integer count = 0;
        List<ContentCategory> brothers = this.contentCategoryMapper.queryByPid(pid);
        if(brothers !=null && brothers.size() == 1){//说明只有他一个子结点
        	ContentCategory parent = new ContentCategory();
        	parent.setId(pid);
        	parent.setIsParent(false);
        	count = this.contentCategoryMapper.updateContentCategory(parent);
        	if(count.intValue()!=1){
        		throw new RuntimeException("修改父节点出错："+parent);
        	}
        	contentCategory = brothers.get(0);
        }else{
            contentCategory = this.contentCategoryMapper.queryById(contentCategory.getId());
        }
        
        if(contentCategory.getIsParent()){
        	List<Object> ids = new ArrayList<Object>();
        	queryChildrenIds(contentCategory.getId(), ids);
        	ids.add(contentCategory.getId());
        	Integer deleteCount = this.contentCategoryMapper.deleteByIds(ids);
        	if(deleteCount < 1){
        		throw new RuntimeException("删除商品类目失败:catentcategory="+contentCategory);
        	}
        }else{
        	List<Object> ids = new ArrayList<Object>();
        	ids.add(contentCategory.getId());
        	Integer deleteCount = this.contentCategoryMapper.deleteByIds(ids);
        	if(deleteCount < 1){
        		throw new RuntimeException("删除商品类目失败:catentcategory="+contentCategory);
        	}
        }
		return true;
	}
	
	
	public void queryChildrenIds(Long parentId,List<Object> ids){
		List<ContentCategory> ccList = this.contentCategoryMapper.queryByPid(parentId);
		for(ContentCategory contentCategory : ccList){
			ids.add(contentCategory.getId());
			if(contentCategory.getIsParent()){
				queryChildrenIds(contentCategory.getId(), ids);
			}
		}
		
	}

}
