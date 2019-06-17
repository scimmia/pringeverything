package com.scimmia.dati;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.apache.commons.lang3.StringUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Fenban {
    public static void main(String[] args) {
//        readFileByLines("h:/ShiDaiGuangHuaquestions");
        Fenban fenban = new Fenban();
        fenban.readExcel("5.xlsx");
    }
    public void readExcel(String filename) {
        try {
            InputStream inputStream = FileUtil.getResourcesFileInputStream(filename);
            List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 0));
            inputStream.close();
            LinkedList<List<Object>> classA = new LinkedList<>();
            LinkedList<List<Object>> classB = new LinkedList<>();
            LinkedList<List<Object>> classC = new LinkedList<>();
            LinkedList<List<Object>> classD = new LinkedList<>();
            LinkedList<List<Object>> classE = new LinkedList<>();
            LinkedList<LinkedList<List<Object>>> all = new LinkedList<>();
            all.add(classA);
            all.add(classB);
            all.add(classC);
            all.add(classD);
            all.add(classE);
            HashMap<String,Integer> maps = new HashMap<>();
            for (int i = 0; i < data.size(); i++) {
                List<Object> m = (List<Object>) data.get(i);
                String org = (String) m.get(1);
                if (maps.containsKey(org)){
                    Integer ma = maps.get(org);
                    ma = ma+1;
                    maps.put(org,ma);
                }else {
                    maps.put(org, 1);
                }
                all.get(i%5).add(m);
            }
            for (String key:maps.keySet()){
                System.out.println(key+'\t'+maps.get(key));
            }
            writeV2003(all);
//            System.out.println("ss");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeV2003(LinkedList<LinkedList<List<Object>>> all) throws IOException {
        OutputStream out = new FileOutputStream("h:\\2004.xls");
        ExcelWriter writer = EasyExcelFactory.getWriter(out, ExcelTypeEnum.XLS,true);
        //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
        Sheet sheet1 = new Sheet(1, 3);
        sheet1.setSheetName("晨曦");
        sheet1.setAutoWidth(Boolean.TRUE);
        writer.write1(all.get(0), sheet1);

        Sheet sheet2 = new Sheet(2, 3 );
        sheet2.setSheetName("晨光");
        sheet2.setAutoWidth(Boolean.TRUE);
        writer.write1(all.get(1), sheet2);

        Sheet sheet3 = new Sheet(3, 3 );
        sheet3.setSheetName("曙光");
        sheet3.setAutoWidth(Boolean.TRUE);
        writer.write1(all.get(2), sheet3);

        Sheet sheet4 = new Sheet(4, 3 );
        sheet4.setSheetName("朝阳");
        sheet4.setAutoWidth(Boolean.TRUE);
        writer.write1(all.get(3), sheet4);

        Sheet sheet5 = new Sheet(5, 3 );
        sheet5.setSheetName("旭日");
        sheet5.setAutoWidth(Boolean.TRUE);
        writer.write1(all.get(4), sheet5);
        writer.finish();
        out.close();
    }

}

