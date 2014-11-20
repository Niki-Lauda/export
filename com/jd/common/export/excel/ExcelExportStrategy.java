package com.jd.common.export.excel;

import com.jd.common.export.ExportStrategy;
import org.apache.poi.hssf.usermodel.*;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: bianfeng
 * Date: 12-2-26
 * Time: 下午1:18
 * To change this template use File | Settings | File Templates.
 */
public class ExcelExportStrategy extends ExportStrategy {
    public ExcelExportStrategy() {
    }

    public void export(OutputStream outputStream) {
        //创建Excel工作簿
        HSSFWorkbook workBook = new HSSFWorkbook();
        //创建Excel工作页（表）
        HSSFSheet sheet = workBook.createSheet("导出数据");
        try {
            String[] titles = getTitles();
            //创建一行
            HSSFRow row = sheet.createRow(0);
            for (int i = 0; i < titles.length; i++) {
                // 创建一个单元格
                HSSFCell cell = row.createCell(i);
                //设置单元格的值
                cell.setCellValue(new HSSFRichTextString(titles[i]));
            }
            for (int i = 0; i < getRows(); i++) {
                row = sheet.createRow(i + 1);
                for (int j = 0; j < getCols(); j++) {
                    HSSFCell cell = row.createCell(j);
                    cell.setCellValue(new HSSFRichTextString(getData(i, j).toString()));
                }
            }
            //将excel的内容输出到流中
            workBook.write(outputStream);
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
