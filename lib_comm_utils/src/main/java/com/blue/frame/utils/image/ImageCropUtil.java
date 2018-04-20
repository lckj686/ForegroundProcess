package com.blue.frame.utils.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.ViewGroup;

import com.blue.frame.utils.StorageUtils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Description:截图
 * Created by liwei on 16/8/10.
 */
public class ImageCropUtil {

    public static Bitmap turnViewToBitmap(ViewGroup view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bitmap);
        view.draw(c);
        return bitmap;
    }


    public static String saveFile(Context context, Bitmap bm) {
        File file = StorageUtils.getPrivateFile(context, "jump_completed.png");


        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(file));
            bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();

            return file.getPath();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String saveShareFile(Context context, Bitmap bm) {
        File file = StorageUtils.getPrivateFile(context, "share.png");


        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(file));
            bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();

            return file.getPath();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 压缩图片质量
     * @param image
     * @return
     */
    public static byte[] compressImage(Bitmap image, int maxSize) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > maxSize) {  //循环判断如果压缩后图片是否大于目标值,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        return baos.toByteArray();
    }
}
