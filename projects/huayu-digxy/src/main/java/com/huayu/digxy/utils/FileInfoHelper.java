package com.huayu.digxy.utils;

import java.io.IOException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.huayu.platform.exception.ServiceRuntimeException;
import com.huayu.platform.utils.DateBaseUtils;
import com.huayu.platform.utils.file.FileTools;

public class FileInfoHelper {
	private final static String LOAD_FILE_HOME = "uploadfile" ;
	
	private final static String LOAD_FILE_TMP_HOME = "tmp" ;

	public static String getUploadFilePath(String fileName){
		return getLocalPath(LOAD_FILE_TMP_HOME) + "/" + fileName;
	}
	
	private static String getLocalPath(String folderName){
		return ServletActionContext.getServletContext().getRealPath(folderName);
	}
	
	public static String getProjectImgPath(long projectId){
		String dateFolder = "/" + projectId + "/images/";
		return getLocalPath(LOAD_FILE_HOME) + dateFolder  ;
	}
	
	public static String getProjectPath(long projectId){
		String dateFolder = "/" + projectId + "/" + DateBaseUtils.format(new Date()) + "/";
		return getLocalPath(LOAD_FILE_HOME) + dateFolder  ;
	}
	
	public static String getProjectPath(long projectId ,long subProjectId){
		String dateFolder = "/" + projectId + "/" +subProjectId+"/"+ DateBaseUtils.format(new Date()) + "/";
		return getLocalPath(LOAD_FILE_HOME) + dateFolder  ;
	}

	public static void moveFile(String srcFile , String targetFile){
		try {
			FileTools.moveFile(srcFile, targetFile);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ServiceRuntimeException(String.format("Failure: the file(%s) move to %s ", srcFile , targetFile ));
		}
	}
	
	public static void moveImgFile(String srcFileName , String targetFileName , Long projectId){
		String srcFile = getUploadFilePath(srcFileName);
		String targetFile = getProjectImgPath(projectId) + targetFileName;
		moveFile(srcFile, targetFile);
	}
	
	public static void moveSubFile(String srcFileName , String targetFileName , Long projectId , Long subProjectId){
		String srcFile = getUploadFilePath(srcFileName);
		String targetFile = getProjectPath(projectId , subProjectId) + targetFileName;
		moveFile(srcFile, targetFile);
	}

}
