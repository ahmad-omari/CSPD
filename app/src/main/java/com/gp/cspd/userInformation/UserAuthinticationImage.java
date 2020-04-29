package com.gp.cspd.userInformation;

import android.graphics.Bitmap;

public class UserAuthinticationImage {
    private Bitmap personalImage;
    private Bitmap signiture;
    private Bitmap personalVerification;
    public UserAuthinticationImage(){
        this.personalImage=null;
        this.signiture=null;
        this.personalVerification=null;
    }

    public void setPersonalImage(Bitmap personalImage) {
        this.personalImage = personalImage;
    }

    public void setSigniture(Bitmap signiture) {
        this.signiture = signiture;
    }

    public void setPersonalVerification(Bitmap personalVerification) {
        this.personalVerification = personalVerification;
    }

    public Bitmap getPersonalImage() {
        return personalImage;
    }

    public Bitmap getSigniture() {
        return signiture;
    }

    public Bitmap getPersonalVerification() {
        return personalVerification;
    }
}
