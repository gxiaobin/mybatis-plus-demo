package com.zm.util;

import java.io.*;
import java.util.Arrays;

/**
 * @description 有关文件的操作
 * @author: guzuyi
 * @create: 2019-05-07 09:33
 **/
public class FileUtil {
    /**
     * 字符串写入文件
     *
     * @param fileName 文件
     * @param content  内容
     */
    public static void createFile(String fileName, String content) throws IOException {
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
        out.write(content);
        out.close();
    }

    public static void createFile(String fileName, StringBuilder content) throws IOException {
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
        out.write(content.toString());
        out.close();
    }

    /**
     * 读文件 字符流
     */
    public static StringBuilder readFileByLines(String fileName) throws IOException {
        StringBuilder builder = new StringBuilder();
        File file = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        // 一次读入一行，直到读入null为文件结束
        String tempString;
        while ((tempString = reader.readLine()) != null) {
            builder.append(tempString);
        }
        reader.close();
        return builder;
    }

    /**
     * 读文件 字节流
     */
    public static byte[] readBytes(File file) throws IOException {
        InputStream in = new FileInputStream(file);
        byte[] bs = null;
        try {
            bs = toByteArray(in);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
        return bs;
    }

    public static byte[] readBytes(String file) throws IOException {
        return readBytes(new File(file));
    }

    public static byte[] toByteArray(InputStream in) throws IOException {
        return toByteArray(in, Integer.MAX_VALUE);
    }

    public static byte[] toByteArray(InputStream in, int maxSize) throws IOException {
        int bytesSize = 1024;
        byte[] bs = new byte[bytesSize];
        int len = 0;
        while (true) {
            if (len >= bytesSize) {// 达到数组上限，翻倍
                bytesSize <<= 1;
                if (bytesSize > maxSize) {
                    bytesSize = maxSize + 8;
                }
                if (bytesSize < 0) {
                    bytesSize = Integer.MAX_VALUE; // greater than max int
                }
                bs = Arrays.copyOf(bs, bytesSize);
            }
            int i = in.read(bs, len, bytesSize - len);
            if (-1 == i) {
                break;
            }

            len += i;
            if (len > maxSize || Integer.MAX_VALUE == len) {
                throw new IOException("IOUtil.is2bs() greater than max size: " + len);
            }
        }
        return Arrays.copyOf(bs, len);
    }

    /**
     * 获取文件的文件类型
     */
    public static String getExtName(String fileName) {
        int dotIdx = fileName.lastIndexOf('.');
        return (dotIdx > 0 && dotIdx < fileName.length()) ? fileName
                .substring(dotIdx + 1) : "";
    }

    /**
     * 获取 base64的图片文件格式
     */
    public static String getBase64ExtName(StringBuilder builder) {
        String extName = "";
        if (builder.indexOf("data:image/png;base64,") != -1) {
            extName = "png";
        } else if (builder.indexOf("data:image/jpg;base64,") != -1) {
            extName = "jpg";
        } else if (builder.indexOf("data:image/jpeg;base64,") != -1) {
            extName = "jpeg";
        } else if (builder.indexOf("data:image/gif;base64,") != -1) {
            extName = "gif";
        } else if (builder.indexOf("data:image/bmp;base64,") != -1) {
            extName = "bmp";
        } else if (builder.indexOf("data:image/tiff;base64,") != -1) {
            extName = "tiff";
        }
        return extName;
    }

    /**
     * 创建路径
     */
    public static void mkdirPath(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static void main(String[] args) throws Exception {
      /* String content = "asdadadasdasd那是那是asasfaffddssdf";
       String path = "E:\\imgs\\tmpDir\\my.txt";
       createFile(path,content);
       StringBuilder builder = new StringBuilder();
       builder = readFileByLines(path);
       System.out.println(builder.toString());*/
        String path = "E:\\imgs\\tmpDir\\my.txt";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
