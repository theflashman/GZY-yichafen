package com.iwork.G629.util;

import java.io.File;
import java.io.IOException;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileUtil {
	public static File transferCommonsMultipartFileToFile(CommonsMultipartFile cFile) {
		File newFile = new File(cFile.getOriginalFilename());
		try {
			cFile.transferTo(newFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newFile;
	}
}
