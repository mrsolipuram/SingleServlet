/**
 * 
 */
package com.single.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * @author madhava
 * 
 */
public class UploadController implements BaseController {

	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		if (!ServletFileUpload.isMultipartContent(request)) {
			throw new IllegalArgumentException(
					"Request is not multipart, please 'multipart/form-data' enctype for your form.");
		}
		ServletFileUpload uploadHandler = new ServletFileUpload(
				new DiskFileItemFactory());
		try {
			List<FileItem> items = uploadHandler.parseRequest(request);
			InputStream inputStream = null;
			String name = null;
			for (FileItem item : items) {
				if (!item.isFormField()) {
					inputStream = item.getInputStream();
					name = item.getName();
					System.out.println("name:" + name);
					System.out.println("contentType:" + item.getContentType());
					System.out.println("size:" + item.getSize());
				}
			}
			if (inputStream != null) {
				FileOutputStream ios = new FileOutputStream(new File("/home/madhava/key_backup/"+name));
				byte[] buffer = new byte[1024];
				int len;
				while ((len = inputStream.read(buffer)) != -1) {
				    ios.write(buffer, 0, len);
				}
				ios.close();
			}
			PrintWriter out = response.getWriter();
			out.write("{\"name\":\"" + name + "\",\"filePath\":\"/home/madhava/key_backup/"+name+"\"}");
			//out.write("success,name:"+name);
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

	}

}
