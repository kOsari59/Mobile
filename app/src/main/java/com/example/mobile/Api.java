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

//공공데이터 api 이용해서 관련 식품 데이터 모두 받고
//그 데이터 중에서 알러지 키가 있는 식품 데이터를 받음.
//code에 알러지 정보가 저장됨
public class Api {
    String key = "mFIj1lR4NzqA7XXA2GytgmxQpgvDlWDF9v%2B1ibMLXcLA3162vS7Vs1Syx41SL%2F8iEngGhId7%2FKarPWBTneZv5w%3D%3D";
    String prdlstNm = "신라면"; //식품명 ;; 실제 코드에서는 신라면 대신 사진 검색으로 받은 제품명을 사용

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

        //공공api에서 json형식 받아옴
        url = new URL("http://apis.data.go.kr/B553748/CertImgListService/getCertImgListService?serviceKey=" + key + "&prdlstNm=" + prdlstNm + "&returnType=json");

        conn = url.openConnection(); // URLConnection 에 ULR을 추가
        br = new BufferedReader(new InputStreamReader(conn.getInputStream())); //버퍼리더를 통해 URL을 br에 넣어준뒤
        result = br.readLine().toString(); // result에 br을 string 형으로 바꾸어 대입
        br.close(); //버퍼리더 꺼주기

        //파싱
        JSONObject jsonObject = new JSONObject(result);
        jArr = jsonObject.getJSONArray("list");
        for (int i = 0; i < jArr.length(); i++) {
            jobj = (JSONObject) jArr.get(i);
            if (jobj.get("prdlstNm").equals(prdlstNm)) {
                code = (String) jobj.get("allergy");
                break;
            }
        }
    }
}
