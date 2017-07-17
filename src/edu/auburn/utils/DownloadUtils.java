package edu.auburn.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadUtils {
	/**
	 * 下载
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void down(String path, String name, HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 获取用户下载的文件名称(url地址后追加数据,get)
//		String fileName = request.getParameter("fileName");
//		// 上传目录路径
//		String basePath = context.getRealPath("/upload");
		// 获取一个文件流
		InputStream in = new FileInputStream(new File(path));
		// 如果文件名是中文，需要进行url编码
//		path = URLEncoder.encode(path,"UTF-8");
		// 设置下载的响应头
		response.setHeader("content-disposition", "attachment;fileName="+name);
		// 获取response字节流
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
