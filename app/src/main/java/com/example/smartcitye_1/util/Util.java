package com.example.smartcitye_1.util;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/11 at 18:17
 */
public class Util {
    public static final String Rows = "ROWS_DETAIL";
    private static Toast toast;

    public static void showToast(String msg, Context context) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();
    }


    public static void showDialog(String msg, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage(msg);
        builder.setPositiveButton("确定", null);

        builder.show();
    }


    public static String simpleDate(String type, Date date) {
        SimpleDateFormat format = new SimpleDateFormat(type);
        return format.format(date);
    }

    //private void drawImage(String uri) {
    //        Hashtable<EncodeHintType, String> hashtable = new Hashtable<>();
    //        hashtable.put(EncodeHintType.CHARACTER_SET, "utf-8");
    //        try {
    //            BitMatrix bitMatrix = new QRCodeWriter().encode(uri, BarcodeFormat.QR_CODE, 300, 300, hashtable);
    //            int[] pix = new int[300 * 300];
    //            for (int x = 0; x < 300; x++) {
    //                for (int y = 0; y < 300; y++) {
    //                    if (bitMatrix.get(x, y)) {
    //                        pix[y * 300 + x] = 0xff000000;
    //                    } else {
    //                        pix[y * 300 + x] = 0xffffffff;
    //                    }
    //                }
    //            }
    //            Bitmap bitmap = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888);
    //            bitmap.setPixels(pix, 0, 300, 0, 0, 300, 300);
    //            ivImage2.setImageBitmap(bitmap);
    //            ivImage1.setImageBitmap(bitmap);
    //        } catch (WriterException e) {
    //            e.printStackTrace();
    //        }
    //    }
    public static Bitmap getBitMat(String url) {
        Hashtable<EncodeHintType, String> hashtable = new Hashtable<>();
        hashtable.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            BitMatrix bitMatrix = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE
                    , 300, 300);
            int pix[] = new int[300 * 300];
            for (int i = 0; i < 300; i++) {

                for (int j = 0; j < 300; j++) {
                    if (bitMatrix.get(i, j)) {
                        pix[j * 300 + i] = 0xff000000;
                    } else {
                        pix[j * 300 + i] = 0xffffffff;
                    }
                }
            }
            Bitmap bitmap = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pix, 0, 300, 0, 0, 300, 300);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }
}
