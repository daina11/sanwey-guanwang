/*
 * Copyright © 2016 uerp.net. All rights reserved.
 */
package sw.common.file;

import sw.common.util.WebUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;


/**
 * 文件操作工具类
 *
 * @author SW
 * @version 1.0
 */
public final class FileUtils {
    /**
     * 私有构造函数，不可实例化
     */
    private FileUtils() {

    }

    public static void download(String servicePath, HttpServletResponse response) throws IOException {
        download(servicePath, null, response);
    }

    public static void download(String servicePath, String fileName, HttpServletResponse response) throws IOException {
        String originalFilename;
        if (fileName == null) {
            originalFilename = servicePath;
        } else {
            //Linux操作系统下文件路径转译
            if ("/".equals(File.separator)) {
                originalFilename = servicePath + "/" + fileName;
            } else {
                originalFilename = servicePath + "\\" + fileName;
            }
        }
        File file = new File(originalFilename);
        FileInputStream inputStream = new FileInputStream(file);
        BufferedInputStream br = new BufferedInputStream(inputStream);
        byte[] buf = new byte[1024];
        int len = 0;
        response.reset();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition",
                "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
        OutputStream out = response.getOutputStream();
        while ((len = br.read(buf)) > 0)
            out.write(buf, 0, len);
        br.close();
        out.close();
    }

    /**
     * excel数据导出
     * wwh 2018年8月15日
     * @param filename
     * @return
     * @throws IOException
     */
    public static OutputStream downFileHelper(String filename) throws IOException {
        HttpServletResponse response = WebUtils.getHttpServletResponse();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=\""+java.net.URLEncoder.encode(filename, "UTF-8"));
        return response.getOutputStream();
    }
}
