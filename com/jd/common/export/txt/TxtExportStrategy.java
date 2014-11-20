package com.jd.common.export.txt;

import com.jd.common.export.ExportStrategy;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: bianfeng
 * Date: 12-3-19
 * Time: 下午12:56
 * To change this template use File | Settings | File Templates.
 */
public class TxtExportStrategy extends ExportStrategy{
    public TxtExportStrategy() {
    }

    public void export(OutputStream outputStream) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
            String titles[] = getTitles();
            /**
             * 输出标题
             */
            for (int i = 0; i < titles.length; i++) {
                bw.write(titles[i]);
                if (i != titles.length - 1) {
                    bw.write("\t");
                }
            }
            bw.newLine();
            /**
             * 输出内容
             */
            for (int i = 0; i < getRows(); i++) {
                for (int j = 0; j < getCols(); j++) {
                    bw.write(getData(i, j).toString());
                    if (j != getCols() - 1) {
                        bw.write("\t");
                    }
                }
                bw.newLine();
            }
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
