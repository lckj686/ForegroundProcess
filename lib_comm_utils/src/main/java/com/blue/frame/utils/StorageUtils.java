package com.blue.frame.utils;

import android.content.Context;
import android.os.Environment;

import com.blue.frame.utils.log4j.LogDebugUtil;

import java.io.File;
import java.io.IOException;

/**
 * Description: 存储位置
 *
 * @author liw
 */
public class StorageUtils {
    private static final String TAG = "StorageUtils";

    /* Checks if external storage is available for read and write */
    static public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);

    }

    /**
     * 获取/data/data/包名/file   的权限
     *
     * @return file
     */
    public static File getFilesAuthority777(Context context, File file) {
        String command = "chmod 777 " + file.getAbsolutePath();
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(command);
        } catch (IOException e) {
            //  Auto-generated catch block
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 获取文件路径
     *
     * @param fileName
     * @return
     */
    public static File getPrivateFile(Context context, String fileName) {
        File file = new File(context.getFilesDir(), fileName);
        return file;
    }

    /* Checks if external storage is available to at least read */
    static public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    // /data/data 下私有目录
    static public File getDataDataDir(Context context, String dirName) {
        return context.getDir(dirName, Context.MODE_PRIVATE);
    }

    /**
     * 公用目录
     *
     * @param
     * @return
     */
    static public File getPublicDir() {

        if (isExternalStorageWritable()) {
            File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            LogDebugUtil.d(TAG, file.getPath());
            return file;
        } else {
            return null;
        }

    }

    /**
     * 私有目录
     *
     * @return
     */
    static public File getPrivateDir(Context context) {

        if (isExternalStorageWritable()) {

            return context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        } else {
            return null;
        }

    }

    static public String getSdcard(String path, String name) {
        if (isExternalStorageWritable()) {
            File destDir = new File(Environment.getExternalStorageDirectory() + File.separator + path);
            if (!destDir.exists()) {
                destDir.mkdirs();
            }

            LogDebugUtil.d(TAG, destDir.getPath() + File.separator + name);
            return destDir.getPath() + File.separator + name;
        } else {
            return null;
        }
    }

    static public File getSdcardFile(String path, String name) {
        if (isExternalStorageWritable()) {
            File destDir = new File(Environment.getExternalStorageDirectory() + File.separator + path);
            if (!destDir.exists()) {
                destDir.mkdirs();
            }

            LogDebugUtil.d(TAG, destDir.getPath() + File.separator + name);
            File file = new File(destDir.getPath() + File.separator + name);
            return file;
        } else {
            return null;
        }
    }

    static public File getDir(String path) {
        if (isExternalStorageWritable()) {
            File destDir = new File(Environment.getExternalStorageDirectory() + File.separator + path);
            if (!destDir.exists()) {
                destDir.mkdirs();
            }

            return destDir;
        } else {
            return null;
        }
    }


    static public File getSdcardSrcFile(String path, String name) {
        if (isExternalStorageWritable()) {
            File destDir = new File(Environment.getExternalStorageDirectory() + File.separator + path);
            if (!destDir.exists()) {
                destDir.mkdirs();
            }

            LogDebugUtil.d(TAG, destDir.getPath() + File.separator + name);
            String dstPath = destDir.getPath() + File.separator + name;
            File file = new File(dstPath);

            return file;
        } else {
            return null;
        }

    }

    static public File getSdcardFile(String path, String name, boolean isDelete) {
        if (isExternalStorageWritable()) {
            File destDir = new File(Environment.getExternalStorageDirectory() + File.separator + path);
            if (!destDir.exists()) {
                destDir.mkdirs();
            }

            LogDebugUtil.d(TAG, destDir.getPath() + File.separator + name);
            String dstPath = destDir.getPath() + File.separator + name;
            File file = new File(dstPath);

            try {
                if (file.exists() && isDelete) {
                    //删除原有下载的文件
                    file.delete();

                    file.createNewFile();
                } else if (!file.exists()) {
                    file.createNewFile();
                }
            } catch (IOException e) {
                e.printStackTrace();
                LogDebugUtil.d(TAG, e.toString());
            }

            return file;
        } else {
            return null;
        }

    }


    static public File getSdcardNewFile(String path, String name) {
        if (isExternalStorageWritable()) {
            File destDir = new File(Environment.getExternalStorageDirectory() + File.separator + path);
            if (!destDir.exists()) {
                destDir.mkdirs();
            }

            LogDebugUtil.d(TAG, destDir.getPath() + File.separator + name);
            String dstPath = destDir.getPath() + File.separator + name;
            File file = new File(dstPath);


            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                    LogDebugUtil.w(TAG, e.toString());
                }
            } else {
                file.delete();
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                    LogDebugUtil.w(TAG, e.toString());
                }
            }
            return file;
        } else {
            return null;
        }
    }
}
