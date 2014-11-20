package com.jd.common.export.demo;

import com.jd.common.export.excel.TemplateExcelExportStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: bianfeng
 * Date: 12-2-26
 * Time: 下午4:48
 * To change this template use File | Settings | File Templates.
 */
public class TemplateExcelExportDemo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 注意区别，new 本类的对象，而不是父类 ExportStrategy
         */
        TemplateExcelExportStrategy exportStrategy = new TemplateExcelExportStrategy();
        /**
         * 设置下载文件名
         */
        exportStrategy.setDownloadFileName(response, "testTemplateExcelExport.xls");

        List<User> list = new ArrayList<User>();
        list.add(new User("张三", 23));
        list.add(new User("李四", 21));
        list.add(new User("王五", 15));
        list.add(new User("赵六", 26));
        Map beans = new HashMap<String, List<User>>();
        beans.put("user", list);

        /**
         * 设置要导出的内容
         */
        exportStrategy.setDataMap(beans);

        /**
         * 设置模版
         *
         *  String path= request.getSession().getServletContext().getRealPath("/template_user.xls");
         *  web应用的根目录下，IntelliJ IDEA-web;eclipse-WebRoot
         */
        String template = "D:/template_user.xls";   //本地路径
        exportStrategy.setTemplate(template);
        /**
         * 导出
         */
        exportStrategy.export(response.getOutputStream());
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
