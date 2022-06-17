package com.example.mobile;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;

public class Api {
    String key = "mFIj1lR4NzqA7XXA2GytgmxQpgvDlWDF9v%2B1ibMLXcLA3162vS7Vs1Syx41SL%2F8iEngGhId7%2FKarPWBTneZv5w%3D%3D";
    String prdlstNm = "신라면";

    public String code = "알러지가 없습니다.";   //지역 코드

    public void Api_loader() throws IOException, ParseException, Exception {
        URL url;
        BufferedReader br;
        URLConnection conn;
        JSONParser parser;
        JSONArray jArr;
        JSONObject jobj;
        JSONObject jtest;
        String result;
        url = new URL("http://apis.data.go.kr/B553748/CertImgListService/getCertImgListService?serviceKey=" + key + "&prdlstNm=" + prdlstNm + "&returnType=json");

        conn = url.openConnection(); // URLConnection 에 ULR을 추가
        br = new BufferedReader(new InputStreamReader(conn.getInputStream())); //버퍼리더를 통해 URL을 br에 넣어준뒤
        result = br.readLine().toString(); // result에 br을 string 형으로 바꾸어 대입
        br.close(); //버퍼리더 꺼주기
        //System.out.println(result); 결과 출력하고 싶으면 이렇게
        JSONObject jsonObject = new JSONObject(result);
        jArr = jsonObject.getJSONArray("list");
        for (int i = 0; i < jArr.length(); i++) { //jArr의 사이즈 만큼 반복
            jobj = (JSONObject) jArr.get(i);//지역이 일치하는지 비교
            if (jobj.get("prdlstNm").equals("신라면 BLACK")) { //지역이 일치하는지 비교
                code = (String) jobj.get("allergy"); //지역코드 대입
                break;
            }
        }
    }
}
