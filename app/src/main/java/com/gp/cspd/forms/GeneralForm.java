package com.gp.cspd.forms;

import android.graphics.Bitmap;

public class GeneralForm {
    private String formName;
    private String formType;
    private String status;
    private Bitmap bitmap1;
    private Bitmap bitmap2;
    private Bitmap bitmap3;
    private Bitmap bitmap4;
    private Bitmap bitmap5;


    protected GeneralForm() {
        formName=null;
        formType=null;
        status=null;
        bitmap1=null;
        bitmap2=null;
        bitmap3=null;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Bitmap getBitmap1() {
        return bitmap1;
    }

    public void setBitmap1(Bitmap bitmap1) {
        this.bitmap1 = bitmap1;
    }

    public Bitmap getBitmap2() {
        return bitmap2;
    }

    public void setBitmap2(Bitmap bitmap2) {
        this.bitmap2 = bitmap2;
    }

    public Bitmap getBitmap3() {
        return bitmap3;
    }

    public void setBitmap3(Bitmap bitmap3) {
        this.bitmap3 = bitmap3;
    }

    public Bitmap getBitmap4() {
        return bitmap4;
    }

    public void setBitmap4(Bitmap bitmap4) {
        this.bitmap4 = bitmap4;
    }

    public Bitmap getBitmap5() {
        return bitmap5;
    }

    public void setBitmap5(Bitmap bitmap5) {
        this.bitmap5 = bitmap5;
    }
}
