package com.example.mobile;

public class AllergyItem {

    String name;
    boolean check;

    public AllergyItem(String name, boolean check){
        this.name = name;
        this.check = check;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean getCheck() {
        return check;
    }
    public void setCheck(boolean check) {
        this.check = check;
    }
}
