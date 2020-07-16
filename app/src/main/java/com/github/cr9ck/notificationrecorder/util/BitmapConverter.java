package com.github.cr9ck.notificationrecorder.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;

import java.nio.ByteBuffer;

public class BitmapConverter {

    public static byte[] bitmapToByteArray(Bitmap bitmap) {
        int size = bitmap.getByteCount();
        ByteBuffer byteBuffer = ByteBuffer.allocate(size);
        bitmap.copyPixelsToBuffer(byteBuffer);
        byteBuffer.rewind();
        return byteBuffer.array();
    }

    public static Bitmap byteArrayToBitmap(byte[] bytes) {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    public static Bitmap drawableToBitmap(Resources resources, @DrawableRes int drawable) {
        return BitmapFactory.decodeResource(resources, drawable);
    }
}
