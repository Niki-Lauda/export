package com.jd.common.export;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: bianfeng
 * Date: 12-2-26
 * Time: 下午12:25
 * To change this template use File | Settings | File Templates.
 */
public abstract class ExportStrategy {
    /**
     * 存储导出标题
     */
    private String[] titles;
    /**
     * 存储导出内容
     */
    private List<List<Object>> dataList = new ArrayList<List<Object>>();

    /**
     * 设置标题内容
     */
    public void setTitle(String... title) {
        this.titles = title;
    }

    /**
     * 获取标题内容
     */
    public String[] getTitles() {
        return titles;
    }

    /**
     * 设置一行中的数据
     */
    public void setData(Object... data) {
        if (data.length > getCols()) {
            throw new IllegalArgumentException("数据数量多于标题列数");
        }
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < data.length; i++) {
            list.add(data[i]);
        }
        dataList.add(list);
    }

    /**
     * 获取指定位置的数据
     */
    public Object getData(int row, int col) {
        if (row > getRows() || col > getCols()) {
            throw new IllegalArgumentException("无效的位置");
        }
        return dataList.get(row).get(col);
    }

    /**
     * 获取导出数据的列数
     *
     * @return
     */
    public int getCols() {
        return titles.length;
    }

    /**
     * 获取导出数据的行数
     *
     * @return
     */
    public int getRows() {
        return dataList.size();
    }

    /**
     * 在响应流中设置下载文件名
     */
    public void setDownloadFileName(HttpServletResponse response, String fileName) {
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("无法设置文件头!", e);
        }

    }

    /**
     * 将数据按照具体策略中的格式导出到输出流中
     *
     * @param outputStream
     */
    public abstract void export(OutputStream outputStream);
}
