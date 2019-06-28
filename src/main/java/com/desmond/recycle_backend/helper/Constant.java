package com.desmond.recycle_backend.helper;
import com.desmond.recycle_backend.models.Response;

import java.util.Map;

public class Constant {
    public static String[] Nothing = new String[]{""};
    public static Map responseWhenError = new Response(null, "请检查您的操作是否合法！", 500).toMap();
}
