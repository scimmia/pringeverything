package com.scimmia.dati;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.scimmia.dati.kebiao.BaseWriteModel;
import org.apache.commons.lang3.tuple.MutablePair;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class FileUtil {

    public static InputStream getResourcesFileInputStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("" + fileName);
    }

    public static void writeV2003(String filePath,LinkedList<MutablePair<String,LinkedList<List<Object>>>> all) throws IOException {
        OutputStream out = new FileOutputStream(filePath);
        ExcelWriter writer = EasyExcelFactory.getWriter(out, ExcelTypeEnum.XLS,true);
        for (int i =0;i<all.size();i++){
            MutablePair<String,LinkedList<List<Object>>> sheet = all.get(i);
            Sheet sheet1 = new Sheet(i+1, 3);
            sheet1.setSheetName(sheet.getLeft());
            sheet1.setAutoWidth(Boolean.TRUE);
            writer.write1(sheet.getRight(), sheet1);
        }
        writer.finish();
        out.close();
    }
    public static void writeV2003(String filePath, LinkedHashMap<String,LinkedList<BaseWriteModel>> all) throws IOException {
        OutputStream out = new FileOutputStream(filePath);
        ExcelWriter writer = EasyExcelFactory.getWriter(out, ExcelTypeEnum.XLS,true);
        int i = 1;
        for (String k:all.keySet()){
            Sheet sheet1 = new Sheet(i, 3);
            sheet1.setSheetName(k);
            sheet1.setAutoWidth(Boolean.TRUE);
            writer.write(all.get(k), sheet1);
            writer.merge(1,1,1,4);
            i++;
        }
        writer.finish();
        out.close();
    }
    public static void writeV2003a(String filePath, LinkedHashMap<String,LinkedList<List<Object>>> all) throws IOException {
        OutputStream out = new FileOutputStream(filePath);
        ExcelWriter writer = EasyExcelFactory.getWriter(out, ExcelTypeEnum.XLS,true);
        int i = 1;
        for (String k:all.keySet()){
            Sheet sheet1 = new Sheet(i, 3);
            sheet1.setSheetName(k);
            sheet1.setAutoWidth(Boolean.TRUE);
            writer.write1(all.get(k), sheet1);
            writer.merge(1,1,0,3);
            i++;
        }
        writer.finish();
        out.close();
    }
}