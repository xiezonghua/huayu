package com.huayu.platform.utils.file;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FileTools {
	public static void moveFile(String srcFilePath, String targetFilePath)
			throws IOException {
		FileUtils.moveFile(new File(srcFilePath), new File(targetFilePath));
	}
}
