package com.gp.cspd.userInformation;

public class User {
    private String [] nameEnglish4Parts;
    private String [] nameArabic4Parts;
    private String mothersNameEnglish;
    private String mothersNameArabic;
    private String gender;
    private String birthPlace;
    private String livingPlace;
    private String bloodType;
    private String email;
    private int phoneNO;
    private Address userAddress;
    private UserAccount userAccount;
    private UserAuthinticationImage authinticationImage;

    public User(String ssn,String password) {
        this.nameEnglish4Parts = new String[4];
        this.nameArabic4Parts = new String[4];
        this.mothersNameEnglish = "";
        this.mothersNameArabic = "";
        this.gender = "";
        this.birthPlace = "";
        this.livingPlace = "";
        this.bloodType = "";
        this.email = "";
        this.phoneNO = 0;
        this.userAddress = new Address();
        this.userAccount = new UserAccount(ssn,password);
        this.authinticationImage = new UserAuthinticationImage();
    }

    public String[] getNameEnglish4Parts() {
        return nameEnglish4Parts;
    }

    public String[] getNameArabic4Parts() {
        return nameArabic4Parts;
    }

    public String getMothersNameEnglish() {
        return mothersNameEnglish;
    }

    public String getMothersNameArabic() {
        return mothersNameArabic;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public String getLivingPlace() {
        return livingPlace;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getEmail() {
        return email;
    }

    public int getPhoneNO() {
        return phoneNO;
    }

    public Address getUserAddress() {
        return userAddress;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public UserAuthinticationImage getAuthinticationImage() {
        return authinticationImage;
    }


    public void setNameEnglish4Parts(String first,String second,String third,String last) {
        this.nameEnglish4Parts[0] = first;
        this.nameEnglish4Parts[1] = second;
        this.nameEnglish4Parts[2] = third;
        this.nameEnglish4Parts[3] = last;
    }

    public void setNameArabic4Parts(String first,String second,String third,String last) {
        this.nameArabic4Parts[0] = first;
        this.nameArabic4Parts[1] = second;
        this.nameArabic4Parts[2] = third;
        this.nameArabic4Parts[3] = last;
    }

    public void setMothersNameEnglish(String mothersNameEnglish) {
        this.mothersNameEnglish = mothersNameEnglish;
    }

    public void setMothersNameArabic(String mothersNameArabic) {
        this.mothersNameArabic = mothersNameArabic;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public void setLivingPlace(String livingPlace) {
        this.livingPlace = livingPlace;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNO(int phoneNO) {
        this.phoneNO = phoneNO;
    }

    public void setUserAddress(Address userAddress) {
        this.userAddress = userAddress;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public void setAuthinticationImage(UserAuthinticationImage authinticationImage) {
        this.authinticationImage = authinticationImage;
    }
}
