package com.example.test;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Yi-27
 * @create 2020-10-30 17:29
 */
public class UploadServlet extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("jiehsoudaole");
//        response.setContentType("text/html; charset=UTF-8");
//        response.getWriter().write("收到！");
//
//        // 防止中文乱码
//        request.setCharacterEncoding("UTF-8");
//        ServletInputStream inputStream = request.getInputStream(); // 得到输入流对象
//
//        byte[] buffer = new byte[1024];
//        StringBuilder stringBuilder = new StringBuilder();
//        int len;
//        while((len = inputStream.read(buffer)) != -1){
//            stringBuilder.append(new String(buffer, 0, len));
//        }
//        System.out.println(stringBuilder);
//    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1. 先判断上传的数据是否为多段数据（只有是多段的数据才是文件上传的
        if (ServletFileUpload.isMultipartContent(request)) {
            // 创建FileItemFactory工厂实现类
            FileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            // 创建用于解析上传数据的工具类ServletFileUpload类
            ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);

            try {
                // 解析上传的数据，得到每一个表单项，FileItem
                List<FileItem> list = servletFileUpload.parseRequest(request);
                // 循环判断，每一个表单项，是普通类型，还是上传的文件
                for (FileItem fileItem : list) {
                    if(fileItem.isFormField()){
                        // 普通表单项
                        System.out.println("表单项的name属性值" + fileItem.getFieldName());
                        // 参数设为UTF-8，解决乱码问题
                        System.out.println("表单项的value属性值" + fileItem.getString("UTF-8"));

                    }else{
                        // 上传的文件
                        System.out.println("表单项的name属性值" + fileItem.getFieldName());
                        // 参数设为UTF-8，解决乱码问题
                        System.out.println("上传文件名：" + fileItem.getName());
                        // 持久化到本地
                        fileItem.write(new File("F:\\Java基础学习和算法练习\\JavaWeb\\LearnJavaWeb\\web_servlet\\" + fileItem.getName()));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
