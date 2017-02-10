package com.lw.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Session;

import com.lw.entry.DemoEntry;
import com.lw.entry.HibernateSessionFactory;
import com.lw.util.Util;

public class UploadServlet extends HttpServlet{

    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
    private static final String UPLOAD_DIRECTORY = "upload";
    
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("UploadServlet");
		// 检测是否为多媒体上传
		if (!ServletFileUpload.isMultipartContent(request)) {
		    // 如果不是则停止
		    PrintWriter writer = response.getWriter();
		    response.setCharacterEncoding("UTF-8");
		    writer.println("Error: 表单必须包含 enctype=multipart/form-data");
		    writer.flush();
		    return;
		}
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(MAX_FILE_SIZE);
		upload.setSizeMax(MAX_REQUEST_SIZE);
        String uploadPath = new File(getServletContext().getRealPath("")).getParent() + "/" + UPLOAD_DIRECTORY;
        System.out.println("uploadPath = " +uploadPath);
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        HibernateSessionFactory.rebuildSessionFactory();
        DemoEntry entry = new DemoEntry();
        try {
            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
//            String apkName = "";
            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
                    	if(item.getSize() <= 0)
                    		continue;
                    	String field = item.getFieldName();
                        String fileName = new File(item.getName()).getName();
//						if ("apk".equals(field)) {
//							int index = fileName.indexOf(".");
//							if (index == -1) {
//								index = fileName.length();
//							}
//							apkName = fileName.substring(0, index);
//						}
						String filePath = uploadPath + "/" + entry.getTitle() + "/" + fileName;
						System.out.println(filePath);
                        File storeFile = new File(filePath);
                        if(!storeFile.getParentFile().exists()) {
                        	storeFile.getParentFile().mkdir();
                        }
                        item.write(storeFile);
                        
                        String path = UPLOAD_DIRECTORY + "/" + entry.getTitle() + "/" + fileName;
                        if("apk".equals(field)) {
                        	entry.setApk(path);
                        } else if("icon".equals(field)) {
                        	entry.setIcon(path);
                        }
                        entry.setSize(item.getSize());
                        request.setAttribute("message",
                            "文件上传成功!");
                    } else {
                    	String fieldName = item.getFieldName();
                    	String fieldValue = new String(item.getString().getBytes("ISO-8859-1"),"UTF-8");
                    	System.out.println(fieldName+":"+fieldValue);
                    	if("title".equals(fieldName)) {
                    		entry.setTitle(fieldValue);
                    	} else if("detail".equals(fieldName)) {
                    		entry.setDetail(fieldValue);
                    	} else if("openlink".equals(fieldName)) {
                    		entry.setOpenlink(fieldValue);
                    	} else if("minVersion".equals(fieldName)) {
                    		entry.setMinversion(fieldValue);
                    	}
                    }
                }
                Session session = HibernateSessionFactory.getSession();
                session.beginTransaction();
                session.save(entry);
                session.getTransaction().commit();
                HibernateSessionFactory.closeSession();
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
            request.setAttribute("message",
                    "错误信息: " + ex.getMessage());
            Util.LogW(this.getClass().getName(), ex.getMessage(), ex);
        }
     // 跳转到 message.jsp
        getServletContext().getRequestDispatcher("/message.jsp").forward(
                request, response);
	}
}
