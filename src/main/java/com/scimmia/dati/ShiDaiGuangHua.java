package com.scimmia.dati;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;


import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
/*
* 从时代光华题库中抽题及答案，并输出为js数组
* */
public class ShiDaiGuangHua {
    public static void main(String[] args) {
//        readFileByLines("h:/ShiDaiGuangHuaquestions");
        readExcel("1.xls");
    }

    public static void readFileByLines(String fileName) {
        LinkedList<String> questions = new LinkedList<>();
        LinkedList<String> answers = new LinkedList<>();
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            String a = "";
            while ((tempString = reader.readLine()) != null) {
                if (isStartWithNumber(tempString)) {
                    if (StringUtils.contains(tempString, "——答案")) {
                        String[] ss = StringUtils.split(tempString, "——答案");
                        if (ss.length == 2) {
                            questions.add(StringUtils.split(ss[0], ".")[1]);
                            answers.add(ss[1]);
                        }
//                        System.out.println(StringUtils.trim(tempString));

                    }
                }
                line++;
            }
            reader.close();
            System.out.println("var qs = [");
            for (String qa :
                    questions) {
                System.out.println("\"" + qa + "\",");
            }
            System.out.println("];");
            System.out.println("var as = [");
            for (String qa :
                    answers) {
                System.out.println("\"" + qa + "\",");
            }
            System.out.println("];");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    static boolean isStartWithNumber(String s) {
        boolean result = false;
        if (StringUtils.isNoneEmpty(s)) {
            String[] ss = StringUtils.split(s, ".");
            if (ss.length > 1) {
                result = NumberUtils.isCreatable(ss[0]);
            }
        }
        return result;
    }

    //光华题库
    public static void readExcel(String filename) {
        try {
            InputStream inputStream = FileUtil.getResourcesFileInputStream(filename);
            List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 0));
            inputStream.close();
            LinkedList<String> questions = new LinkedList<>();
            LinkedList<String> answers = new LinkedList<>();
            for (int i = 0; i < data.size(); i++) {
                List<String> dataTemp = (List<String>) data.get(i);
                if (dataTemp.size() > 2) {
                    String questionType = dataTemp.get(1);
                    String question = dataTemp.get(3);
                    String answer = dataTemp.get(12);
                    String theAnswer = "";
                    if (StringUtils.equals(questionType, "单选题")) {
                        StringBuffer ans = new StringBuffer();
                        if (StringUtils.containsIgnoreCase(answer, "A")) {
                            ans.append(dataTemp.get(14));
                        } else if (StringUtils.containsIgnoreCase(answer, "b")) {
                            ans.append(dataTemp.get(15));
                        } else if (StringUtils.containsIgnoreCase(answer, "c")) {
                            ans.append(dataTemp.get(16));
                        } else if (StringUtils.containsIgnoreCase(answer, "d")) {
                            ans.append(dataTemp.get(17));
                        } else if (StringUtils.containsIgnoreCase(answer, "e")) {
                            ans.append(dataTemp.get(18));
                        } else if (StringUtils.containsIgnoreCase(answer, "f")) {
                            ans.append(dataTemp.get(19));
                        } else if (StringUtils.containsIgnoreCase(answer, "g")) {
                            ans.append(dataTemp.get(20));
                        }
                        theAnswer = ans.toString();
                    } else if (StringUtils.equals(questionType, "多选题")) {
                        StringBuffer ans = new StringBuffer();
                        if (StringUtils.containsIgnoreCase(answer, "A")) {
                            ans.append(dataTemp.get(14)).append('|');
                        }
                        if (StringUtils.containsIgnoreCase(answer, "b")) {
                            ans.append(dataTemp.get(15)).append('|');
                        }
                        if (StringUtils.containsIgnoreCase(answer, "c")) {
                            ans.append(dataTemp.get(16)).append('|');
                        }
                        if (StringUtils.containsIgnoreCase(answer, "d")) {
                            ans.append(dataTemp.get(17)).append('|');
                        }
                        if (StringUtils.containsIgnoreCase(answer, "e")) {
                            ans.append(dataTemp.get(18)).append('|');
                        }
                        if (StringUtils.containsIgnoreCase(answer, "f")) {
                            ans.append(dataTemp.get(19)).append('|');
                        }
                        if (StringUtils.containsIgnoreCase(answer, "g")) {
                            ans.append(dataTemp.get(20));
                        }
                        theAnswer = ans.toString();
                    } else if (StringUtils.equals(questionType, "判断题")) {
                        if (StringUtils.containsAny(answer, "A", "a", "正确", "1", "√")) {
                            theAnswer = "√";
                        } else if (StringUtils.containsAny(answer, "B", "b", "错误", "2", "×", "X", "x")) {
                            theAnswer = "×";
                        }
                    }else {
                        continue;
                    }
//                    System.out.println(question);
//                    System.out.println(theAnswer);
                    if (StringUtils.isNoneEmpty(question)) {
                        questions.add(question);
                        answers.add(theAnswer);
                    }
                }
            }
            printQandAtoJS(questions,answers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printQandAtoJS(LinkedList<String> questions,LinkedList<String> answers) {
        System.out.println("var qs = [");
        for (String qa :
                questions) {
            System.out.println("\""+qa+"\",");
        }
        System.out.println("];");
        System.out.println("var as = [");
        for (String qa :
                answers) {
            System.out.println("\""+qa+"\",");
        }
        System.out.println("];");
    }
}