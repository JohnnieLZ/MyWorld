package com.PL.FileIOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class FileUtils {

    public static void main(String[] args) {
        FileUtils utils = new FileUtils();
        System.out.println("--------------对比开始----------------");
//        utils.comparisonFile("/Users/johnnie/Documents/TempFiles/BUSI_FUND_DEFRAY.txt", "/Users/johnnie/Documents/TempFiles/BUSI_FUND_DEFRAY.txt", "/Users/johnnie/Documents/Temp/BUSI_FUND_DEFRAY.txt");
//        utils.comparisonFile("/Users/johnnie/Documents/TempFiles/BUSI_HOSP_CHARGE.txt", "/Users/johnnie/Documents/TempFiles/BUSI_HOSP_CHARGE.txt", "/Users/johnnie/Documents/Temp/BUSI_HOSP_CHARGE.txt");
//        utils.comparisonFile("/Users/johnnie/Documents/TempFiles/BUSI_RECIPE_DETAIL.txt", "/Users/johnnie/Documents/TempFiles/BUSI_RECIPE_DETAIL.txt", "/Users/johnnie/Documents/Temp/BUSI_RECIPE_DETAIL.txt");

//        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//        Date date = new Date();
//        String hcclrq = format.format(date).toString();
//        System.out.println(hcclrq);
//        utils.outputDifferenceDate("/Users/johnnie/Documents/TempFile/BUSI_HOSP_CHARGE.txt", "/Users/johnnie/Documents/Temp/BUSI_HOSP_CHARGE.txt", "2017-11-29");
//        utils.outputDifferenceDate("/Users/johnnie/Documents/TempFile/BUSI_RECIPE_DETAIL.txt", "/Users/johnnie/Documents/Temp/BUSI_RECIPE_DETAIL.txt", "2017-11-29");
        utils.deleteFileRepeatData("/Users/johnnie/Documents/Temp/BUSI_HOSP_CHARGE.txt", "/Users/johnnie/Documents/Temp/deleteData.txt", "/Users/johnnie/Documents/BUSI_HOSP_CHARGE.txt");
        System.out.println("--------------对比结束----------------");
    }

    private FileInputStream in = null;
    private InputStreamReader is = null;
    private BufferedReader reader = null;
    private FileOutputStream fos = null;
    private OutputStreamWriter osw = null;
    private BufferedWriter writer = null;

    /**
     * 读文件中数据返回一个List list大小对应文件行数
     *
     * @param filePath
     * @return
     */
    public List<String> readFileData(String filePath) {

        List<String> data = new ArrayList<String>();
        try {
            File file = new File(filePath);
            if (file.isDirectory()) {
                return null;
            } else if (file.isFile()) {
                in = new FileInputStream(file);
                is = new InputStreamReader(in);
                reader = new BufferedReader(is);
                String lineDate = null;
                while (true) {
                    lineDate = reader.readLine();
                    if (lineDate != null) {
                        data.add(lineDate);
                    } else {
                        break;
                    }

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return data;
    }


    /**
     * 对比2个单个文件内容（通过文件名确认对比文件），输出对比出的不同内容到指定的文件夹下的文件中
     *
     * @param inputFilePath1 对比文件1
     * @param inputFilePath2 对比文件2
     * @param outputFilePath 创建新文件命名为文件1输出不同记录到该路径下
     */
    public void comparisonFile(String inputFilePath1, String inputFilePath2, String outputFilePath) {

        List<String> file1 = readFileData(inputFilePath1);
        List<String> file2 = readFileData(inputFilePath2);
        try {
            fos = new FileOutputStream(outputFilePath);
            osw = new OutputStreamWriter(fos);
            writer = new BufferedWriter(osw);
            //如果文件1比文件2大，移除文件1中文件2的内容，否则移除文件2中文件1的内容
            if (file1.size() > file2.size()) {
                for (String str : file2) {
                    file1.remove(str);
                }
                for (String str : file1) {
                    writer.write(str + "\n");
                    fos.flush();
                }
            } else {
                for (String str : file1) {
                    file2.remove(str);
                }
                for (String str : file1) {
                    writer.write(str + "\n");
                    fos.flush();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     *
     */
    public void outputDifferenceDate(String filePath, String outputFilePath, String equals) {
        List fileDate = readFileData(filePath);
        try {
            fos = new FileOutputStream(outputFilePath);
            osw = new OutputStreamWriter(fos);
            writer = new BufferedWriter(osw);

            for (int i = 0; i < fileDate.size(); i++) {
                String date = (String) fileDate.get(i);
                String[] str = date.split("%[|][*]");
                if (str[str.length - 1].substring(0, 10).equals(equals)) {
                    writer.write(date + "\n");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     *
     */
    public void deleteFileRepeatData(String filePath1, String filePath2, String outputFilePath) {
        List<String> file1 = readFileData(filePath1);
        HashSet set = new HashSet(file1);
        file1.clear();
        file1.addAll(set);
        List<String> file2 = readFileData(filePath2);
        try {
            fos = new FileOutputStream(outputFilePath);
            osw = new OutputStreamWriter(fos);
            writer = new BufferedWriter(osw);

            for (int i = 0; i < file1.size(); i++) {
                for (int j = 0; j < file2.size(); j++) {
                    if(file1.get(i).substring(0,24).equals(file2.get(j))){
                        file1.remove(file1.get(i));
                    }
                }
                writer.write(file1.get(i) + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
