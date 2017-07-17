package edu.auburn.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadUtils {
	/**
	 * download
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void down(String path, String name, HttpServletRequest request, HttpServletResponse response) throws IOException {
		InputStream in = new FileInputStream(new File(path));
//		path = URLEncoder.encode(path,"UTF-8");
		response.setHeader("content-disposition", "attachment;fileName="+name);
		OutputStream out = response.getOutputStream();
		byte[] b = new byte[1024];
		int len = -1;
		while((len = in.read(b)) != -1){
			out.write(b, 0, len);
		}
		out.close();
		in.close();
	}
}
