package com.taotao.manage.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PropertiesService {

	@Value("${IMAGE_UPLOADPATH}")
	public String IMAGE_UPLOADPATH;
	
	@Value("${IMAGE_HTTPPATH}")
	public String IMAGE_HTTPPATH;
}
