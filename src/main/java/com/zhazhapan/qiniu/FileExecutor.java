/**
 * 
 */
package com.zhazhapan.qiniu;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.zhazhapan.qiniu.modules.constant.Values;
import com.zhazhapan.qiniu.util.Checker;
import com.zhazhapan.qiniu.view.Dialogs;

/**
 * @author pantao
 *
 */
public class FileExecutor {

	private Logger logger = Logger.getLogger(FileExecutor.class);

	public void saveFile(String path, String content) {
		saveFile(path, content, false);
	}

	public void saveFile(String path, String content, boolean append) {
		saveFile(new File(path), content, append);
	}

	public void saveFile(File file, String content) {
		saveFile(file, content, false);
	}

	public void saveFile(File file, String content, boolean append) {
		if (Checker.isNotNull(file)) {
			try {
				if (!file.exists()) {
					file.createNewFile();
				}
				BufferedWriter out = new BufferedWriter(new FileWriter(file, append));
				out.write(content);
				out.close();
				logger.info("save file '" + file.getAbsolutePath() + "' success");
			} catch (IOException e) {
				logger.error("save file failed, messages: " + e.getMessage());
				Dialogs.showException(Values.SAVE_FILE_ERROR, e);
			}
		}
	}
}
