package com.jhsns.Sns.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileManager {
	public static final String FILE_UPLOAD_PATH = "C:\\Spring\\springproject\\upload\\Sns";
	
	public static String saveFile(int userId, MultipartFile file)
	{
		if(file == null)
		{
			return null;
		}
		
		String directoryName = "/" + userId + "_" + System.currentTimeMillis();
		
		String directoryPath = FILE_UPLOAD_PATH + directoryName;
		
		File directory = new File(directoryPath);
		
		if(!directory.mkdir())
		{
			return null;
		}
		
		String filePath = directoryPath + "/" + file.getOriginalFilename();
		
		byte[] bytes;
		
		try {
			bytes = file.getBytes();
			Path path = Paths.get(filePath);
			Files.write(path, bytes);
		} catch (IOException e) {
			return null;
		}
		
		return "/images" + directoryName + "/" + file.getOriginalFilename();
	}
	public static boolean removeFile(String filePath)
	{
		if(filePath == null)
		{
			return false;
		}
		
		String fullFilePath = FILE_UPLOAD_PATH + filePath.replace("/images", "");
		
		Path path = Paths.get(fullFilePath);		// 파일 위치
		Path directoryPath = path.getParent();		// 디렉토리 위치
		
		if(Files.exists(directoryPath) && Files.exists(path))
		{
			try {
				Files.delete(path);
				Files.delete(directoryPath);
			} catch (IOException e) {
				return false;
			}
			return true;
		}
		else
		{
			return false;
		}
	}
}
