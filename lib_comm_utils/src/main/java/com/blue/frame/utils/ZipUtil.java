package com.blue.frame.utils;

import com.blue.frame.utils.log4j.LogDebugUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

/**
 * Description: zip解压
 * Created by liwei on 16/7/11.
 */
public class ZipUtil {
    private static final String TAG = "ZipUtil";

    public static void unZipFile(String archive, String decompressDir) throws IOException, FileNotFoundException, ZipException {
        BufferedInputStream bi;
        ZipFile zf = new ZipFile(archive);
        Enumeration e = zf.entries();

        while (e.hasMoreElements()) {
            ZipEntry ze2 = (ZipEntry) e.nextElement();
            String entryName = ze2.getName();
            String path = decompressDir + "/" + entryName;
            if (ze2.isDirectory()) {

                LogDebugUtil.d(TAG, "正在创建解压目录 - " + entryName);
                File decompressDirFile = new File(path);
                if (!decompressDirFile.exists()) {
                    decompressDirFile.mkdirs();
                }
            } else {
                LogDebugUtil.d(TAG, "正在创建解压文件 - " + entryName);

                String fileDir = path.substring(0, path.lastIndexOf("/"));
                File fileDirFile = new File(fileDir);
                if (!fileDirFile.exists()) {
                    fileDirFile.mkdirs();
                }
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(decompressDir + "/" + entryName));
                bi = new BufferedInputStream(zf.getInputStream(ze2));
                byte[] readContent = new byte[1024];
                int readCount = bi.read(readContent);
                while (readCount != -1) {
                    bos.write(readContent, 0, readCount);
                    readCount = bi.read(readContent);
                }
                bos.close();
            }
        }
        zf.close();
        //bIsUnzipFinsh = true;
    }




}
