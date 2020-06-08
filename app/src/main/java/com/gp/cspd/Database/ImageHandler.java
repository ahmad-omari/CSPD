package com.gp.cspd.Database;

import android.graphics.Bitmap;

public class ImageHandler {
    private static ImageHandler instance=null;
    private Bitmap bitmap;

    private ImageHandler(){
    }

    public static ImageHandler getInstance() {
        if (instance == null)
            instance = new ImageHandler();
        return instance;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
