package com.example.mobile;

public class AllergyItem {

    String name;
    boolean check;

    //알러지 데이터가 저장될 구조
    public AllergyItem(String name, boolean check){
        this.name = name;
        this.check = check;
    }
    //이름을 출력하고 설정
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    //선택 정보가 저장되고 출력
    public boolean getCheck() {
        return check;
    }
    public void setCheck(boolean check) {
        this.check = check;
    }
}
