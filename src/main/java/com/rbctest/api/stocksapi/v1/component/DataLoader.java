package com.rbctest.api.stocksapi.v1.component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile; 

import com.rbctest.api.stocksapi.v1.service.FileUploadService;

@Component
public class DataLoader{

	@Autowired
	FileUploadService fileServ;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Value("${sample_data_file_name}")
	private String fileName;

	@EventListener(ApplicationReadyEvent.class)
	public void loadDatafterStartup() {
		try {
			Resource fileResource = resourceLoader.getResource("classpath:"+fileName);
			MultipartFile multipartFile = new MockMultipartFile(fileName, new FileInputStream(fileResource.getFile()));
			fileServ.batchUpload(multipartFile);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}