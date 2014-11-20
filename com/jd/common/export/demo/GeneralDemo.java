package com.jd.common.export.demo;

import com.jd.common.export.ExportStrategy;
import com.jd.common.export.csv.CSVExportStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: bianfeng
 * Date: 12-2-26
 * Time: 下午4:37
 * To change this template use File | Settings | File Templates.
 */
public class GeneralDemo extends HttpServlet {
    public GeneralDemo() {
    }


    /**
     * 通用方法，使用其他策略时只需要修改
     * ExportStrategy exportStrategy = new CSVExportStrategy();
     * 为相应的策略类即可
     * 注：TemplateExcelExportStrategy除外 ，xml导出策略不用设置标题
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] titles = new String[]{"姓名", "学号", "年龄"};
        ExportStrategy exportStrategy = new CSVExportStrategy();
        /**
         * 设置标题 ,如果使用xml策略则不用设置
         */
        exportStrategy.setTitle(titles);
        /**
         * 设置下载文件名
         */
        exportStrategy.setDownloadFileName(response, "testCSV.txt");
        /**
         * 设置要导出的内容
         */
        for (int i = 0; i < 4; i++) {
            exportStrategy.setData("张三", 12312, 23);
        }
        /**
         * 导出
         */
        exportStrategy.export(response.getOutputStream());
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
