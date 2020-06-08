package com.gp.cspd.forms;

import android.graphics.Bitmap;

public class RenewIdCardForm extends Form {
    private Bitmap bitmap;

    public RenewIdCardForm() {
        super("renew id");
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
