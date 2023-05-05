package com.xz.nacos.controller;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class JSONToCsvTest {
    public static void main(String[] args) throws Exception {
        String json = "[{\n" +
                "\"name\": \"xz\",\n" +
                "\"age\": 111,\n" +
                "\"address\":\"重庆江津\"\n" +
                "},{\n" +
                "\"name\": \"xz123\",\n" +
                "\"age\": 123,\n" +
                "\"address\":\"重庆江津123\"\n" +
                "}]";
        JSONArray obj = new JSONArray(json);
        String s = CDL.toString(obj);
        System.out.println(s);
        String fileName = "D:/test.csv";
        // 写出去
        FileOutputStream outputStream = new FileOutputStream(fileName);
        outputStream.write(s.getBytes(StandardCharsets.UTF_8));
        File file = new File(fileName);
        byte[] buf = new byte[1024];
        FileInputStream inputStream = new FileInputStream(file);
        inputStream.read(buf);
        System.out.println(new String(buf, StandardCharsets.UTF_8));
    }
}
