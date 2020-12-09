package com.gwy.manager.util.file;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

/**
 * @author Tracy
 * @date 2020/11/6 10:45
 */
public class MultipartFileUtil {

    /**
     * MultipartFile 转 File
     *
     * @param file 文件
     * @throws Exception 异常
     */
    public static File multipartFileToFile(MultipartFile file) throws Exception {

        File toFile = null;
        if (!StringUtils.isEmpty(file.getName()) || file.getSize() > 0) {
            InputStream ins;
            ins = file.getInputStream();
            toFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }

    /**
     * 获取流文件
     *
     * @param ins  输入流
     * @param file 文件
     */
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除本地临时文件
     *
     * @param file 预删除
     */
    public static void deleteTempFile(File file) {
        FileUtils.deleteQuietly(file);
    }
}