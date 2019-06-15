package com.scimmia.dati.kebiao;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.scimmia.dati.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.MutablePair;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class KeBiao {
    String[] courses = new String[]{
            "《经济新常态下农商银行高质量发展应该怎么干》	闫友田",
            "《厅堂营销活动策划实战经验交流》	李金玉",
            "《支行业务发展策略》	韩风华",
            "《支行过程管控与客户维护》	李春萌",
            "《支行精细化管理》	孙晓琳",
            "《服务辖区网格化营销实践及操作要点》	王纪昌",
            "《厅堂网格化营销与实战指导》	齐丽敏",
            "《以省联社123456为引领 推进代理型网点建设》	王保君",
            "网点转型思路再探讨	班主任",
            "《支行信贷业务拓展》	李晓雷",
            "《潍坊农商银行网点转型回顾与前景展望》	郑传伟",
            "《莘县农商银行网点转型发展经验交流》	魏会超",
            "《网点转型导入流程与工作要点》	李明哲"
    };
    public static void main(String[] args) {
        KeBiao k = new KeBiao();
//        k.initCourses();
        k.doExcel("kebiao0617.xlsx");
    }
    HashMap<String,Course> initCourses(){
        HashMap<String,Course> al = new HashMap<>();
        for (String course :
                courses) {
            String[] temp = StringUtils.split(course,"\t");
            if (temp.length == 2){
                Course c = new Course();
                c.setName(temp[0]);
                c.setTeacher(temp[1]);
                al.put(temp[1],c);
            }
        }
        System.out.println("");
        return al;
    }
    void doExcel(String filename){
        try {
            HashMap<String,Course> al = initCourses();

            LinkedList<MutablePair<String,LinkedList<List<Object>>>> allSheet = new LinkedList<>();
            InputStream inputStream = FileUtil.getResourcesFileInputStream(filename);
            List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 0));
            inputStream.close();
//            System.out.println(((List<Object>)data.get(0)).get(2));
//            allSheet.add(new MutablePair<String,LinkedList<List<Object>>>((String) ((List<Object>)data.get(0)).get(2),new LinkedList<>()));
//            allSheet.add(new MutablePair<String,LinkedList<List<Object>>>((String) ((List<Object>)data.get(0)).get(3),new LinkedList<>()));
//            allSheet.add(new MutablePair<String,LinkedList<List<Object>>>((String) ((List<Object>)data.get(0)).get(4),new LinkedList<>()));
//            allSheet.add(new MutablePair<String,LinkedList<List<Object>>>((String) ((List<Object>)data.get(0)).get(5),new LinkedList<>()));
//            allSheet.add(new MutablePair<String,LinkedList<List<Object>>>((String) ((List<Object>)data.get(0)).get(6),new LinkedList<>()));
//            for (int i = 1; i < data.size(); i++) {
//                List<Object> m = (List<Object>) data.get(i);
//                String theDate = (String) m.get(1);
//                for (int j=0;j<allSheet.size();j++){
//                    LinkedList<List<Object>> datas = allSheet.get(j).getRight();
//                    List<Object> dataLine = new LinkedList<>();
//                    if (StringUtils.contains(theDate,"上")){
//                        dataLine.add(theDate.replace("上","号"));
//                        dataLine.add("8:00-12:00");
//                    }else if (StringUtils.contains(theDate,"下")){
//                        dataLine.add(theDate.replace("下","号"));
//                        dataLine.add("14:30-18:00");
//                    }else if (StringUtils.contains(theDate,"晚")){
//                        dataLine.add(theDate.replace("晚","号"));
//                        dataLine.add("19:00-23:00");
//                    }
//                    String teacher = (String) m.get(2+j);
//                    Course course =al.get(teacher);
//                    dataLine.add(course.getName());
//                    dataLine.add(course.getTeacher());
//                    datas.add(dataLine);
//                }
//            }
//            FileUtil.writeV2003("h:\\0617.xls",allSheet);

//            LinkedHashMap<String,LinkedList<BaseWriteModel>> teachers = new LinkedHashMap<>();
//            for (String t :
//                    al.keySet()) {
//                LinkedList<BaseWriteModel> dataTemp = new LinkedList<>();
//                BaseWriteModel model1 = new BaseWriteModel();
//                BaseWriteModel model2 = new BaseWriteModel();
//                model1.setP1("专家授课表");
//                model1.setP2("");
//                model1.setP3("");
//                model1.setP4("");
//                model2.setP1("");
//                model2.setP2("8:00-12:00");
//                model2.setP3("14:30-18:00");
//                model2.setP4("19:00-23:00");
//                dataTemp.add(model1);
//                dataTemp.add(model2);
//
//                for (int i = 1; i < data.size(); ) {
//                    List<Object> m = (List<Object>) data.get(i);
//                    BaseWriteModel model = new BaseWriteModel();
//                    model.setP1(((String) m.get(1)).replace("上","号"));
//                    model.setP2("");
//                    model.setP3("");
//                    model.setP4("");
//                    dataTemp.add(model);
//                    i = i+3;
//                }
//                teachers.put(t,dataTemp);
//            }
//            FileUtil.writeV2003("h:\\0617teachers.xls",teachers);
            LinkedHashMap<String,LinkedList<List<Object>>> teachers = new LinkedHashMap<>();
            for (String t :
                    al.keySet()) {
                LinkedList<List<Object>> dataTemp = new LinkedList<>();
                List<Object> model1 = new LinkedList<>();
                List<Object> model2 = new LinkedList<>();
                model1.add("专家授课表");
                model2.add("");
                model2.add("8:00-12:00");
                model2.add("14:30-18:00");
                model2.add("19:00-23:00");
                dataTemp.add(model1);
                dataTemp.add(model2);

                for (int i = 1; i < data.size(); ) {
                    List<Object> m = (List<Object>) data.get(i);
                    List<Object> model = new LinkedList<>();
                    model.add(((String) m.get(1)).replace("上","号"));
                    model.add("");
                    model.add("");
                    model.add("");
                    dataTemp.add(model);
                    i = i+3;
                }
                teachers.put(t,dataTemp);
            }
            FileUtil.writeV2003a("h:\\0617teachers.xls",teachers);

            System.out.println("ss");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
