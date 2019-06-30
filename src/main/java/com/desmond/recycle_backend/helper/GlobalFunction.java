package com.desmond.recycle_backend.helper;

import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class GlobalFunction {

    public static String uglify(String text) {
        StringBuilder uglyText = new StringBuilder();
        for (int pointer = 0; pointer < text.length(); pointer++) {
            uglyText.append(text.charAt(pointer) + Config.salt.charAt(pointer % Config.salt.length()));
        }
        return uglyText.toString();
    }

    private static String byte2Hex(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte aByte : bytes) {
            String nextStr = Integer.toHexString(0xF00 | (aByte & 0xFF));
            stringBuilder.append(nextStr.substring(nextStr.length() - 2));
        }
        return stringBuilder.toString();
    }

    public static String encrypt(String type, String plain) {
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance(type);
            messageDigest.update(uglify(plain).getBytes(StandardCharsets.UTF_8));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    public static Map<String, String[]> request2Map(HttpServletRequest request){
        String token = request.getHeader("token");
//        System.out.println(token);
        Map<String, String[]> result = new HashMap<>(request.getParameterMap());
        result.put("token", new String[]{token});
        return result;
    }

    public static String getBase64Img(String filename) {
        File file = new File("upload/"+filename);
        if (!file.exists())
            return "";
        else {
            try {
                FileInputStream fis = new FileInputStream(file);
                int len = fis.available();
                byte[] buffer = new byte[len];
                fis.read(buffer);
                fis.close();
                return "data:image/jpeg;base64,"+Base64.encodeBase64String(buffer);
            } catch (Exception e) {
                return "";
            }
        }
    }
}
