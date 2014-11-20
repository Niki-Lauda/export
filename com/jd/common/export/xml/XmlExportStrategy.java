package com.jd.common.export.xml;

import com.jd.common.export.ExportStrategy;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: bianfeng
 * Date: 12-2-26
 * Time: 下午3:09
 * To change this template use File | Settings | File Templates.
 */
public class XmlExportStrategy extends ExportStrategy {
    public XmlExportStrategy() {
    }

    public void export(OutputStream outputStream) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
            //输出xml文件头
            bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            bw.newLine();
            String titles[] = getTitles();

            //总开始标签
            bw.write("<contents>");
            for (int i = 0; i < getRows(); i++) {
                bw.newLine();
                //输出<content row="i">
                bw.write("\t<content id=\"" + (i + 1) + "\">");
                bw.newLine();
                //输出content内容
                for (int j = 0; j < titles.length; j++) {
                    bw.write("\t\t<" + titles[j] + ">");
                    bw.write(getData(i, j).toString());
                    bw.write("</" + titles[j] + ">");
                    bw.newLine();
                }
                bw.write("\t</content>");
            }
            bw.newLine();
            bw.write("</contents>");
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException("无法导出内容!", e);
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException("输出流关闭失败!", e);
            }
        }

    }
}
