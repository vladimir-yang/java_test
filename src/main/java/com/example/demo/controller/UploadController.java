package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.config.FastDFSClientUtil;
import com.github.tobato.fastdfs.domain.FileInfo;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;


@RestController
public class UploadController {
	
	@Autowired
    private FastDFSClientUtil dfsClient;
	
	@Autowired
    FastFileStorageClient fastFileStorageClient;

	
	// 上传图片
    @PostMapping("/upload")
    public String upload(MultipartFile file) throws Exception{
    	String imgUrl = dfsClient.uploadFile(file);
        return imgUrl;
    }
    
    // 文件路径上传
    @PostMapping("/fileUpload")
    public String fileUpload(String path) throws Exception{
    	File file = new File(path);
    	StorePath imgUrl = fastFileStorageClient.uploadFile(null, new FileInputStream(file), file.length(), "apk");
    	String imgpath = imgUrl.getFullPath();
    	//        String imgUrl = dfsClient.uploadFile(file);
        System.out.println(imgpath);
    	return imgpath;
    }

    @PostMapping("/filedelete")
    public boolean filedelete(String allFilePath) throws Exception{
//        dfsClient.deleteFile("group1/M00/00/01/rBCFo1unNuWAW9RJAAUFLq3fs_g827.jpg");
        boolean result = dfsClient.deleteFile(allFilePath);
        return result;
    }

    @PostMapping("/fileDetail")
    public FileInfo fileDetail(String group,String filepath){
//        FileInfo fileInfo = dfsClient.getFileDetail("group1", "M00/00/01/rBCFo1unNuWAW9RJAAUFLq3fs_g827.jpg");
        FileInfo fileInfo = dfsClient.getFileDetail(group, filepath);
        long fileSize = fileInfo.getFileSize();
        int crc32 = fileInfo.getCrc32();
        System.out.println("fileSize="+fileSize);
        //可以用crc32校验文件是否相同,做秒传功能
        System.out.println("crc32="+crc32);
        return fileInfo;
    }
}
