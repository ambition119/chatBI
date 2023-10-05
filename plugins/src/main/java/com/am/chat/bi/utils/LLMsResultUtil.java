package com.am.chat.bi.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LLMsResultUtil {

    public static String getIntentionResult(String result){
        String intentionResult = null;

        Pattern pattern = Pattern.compile("(\\d+)");
        Matcher matcher = pattern.matcher(result);
        if (matcher.find()) {
            intentionResult = matcher.group();
        }

        return intentionResult;
    }

    public static JsonElement getJsonValue(String result, String name) {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(result).getAsJsonObject();
        return jsonObject.get(name);
    }

    public static String getDimMetricJson(String result){
        String sqlResult = null;
//        Pattern pattern = Pattern.compile("```json(.*?)```");
        Pattern pattern = Pattern.compile("\\{.*\\}");
        Matcher matcher = pattern.matcher(result);
        if (matcher.find()) {
            sqlResult = matcher.group();
        }

        return sqlResult;
    }

    public static String getSqlResult(String result){
        String sqlResult = null;

        Pattern pattern = Pattern.compile("querySql: \"(.*?)\"}");
        Matcher matcher = pattern.matcher(result);
        if (matcher.find()) {
            sqlResult = matcher.group();
        }

        return sqlResult;
    }


}
