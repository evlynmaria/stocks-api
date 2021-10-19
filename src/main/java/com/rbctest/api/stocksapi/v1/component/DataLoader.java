package com.rbctest.api.stocksapi.v1.component;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.rbctest.api.stocksapi.v1.service.FileUploadService;

@Component
public class DataLoader {

	@Autowired
	FileUploadService fileServ;

	@Autowired
	private ResourceLoader resourceLoader;

	@Value("${sample_data_file_name}")
	private String fileName;

	// method is triggered on application start and loads the sample csv
	@EventListener(ApplicationReadyEvent.class)
	public void loadDatafterStartup() {
		try {
			Resource fileResource = resourceLoader.getResource("classpath*:" + fileName);
			MultipartFile multipartFile = new MockMultipartFile(fileName, fileResource.getInputStream());
			fileServ.batchUpload(multipartFile);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}