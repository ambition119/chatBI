package com.am.chat.bi.utils;

public class PromptUtil {

    /**
     * 查询意图
     * @param queryContent
     * @return
     */
    public static String getIntentionPrompt(String queryContent) {
        String intentionPrompt = "“" + queryContent + "” 请帮忙判断这句话的意图是需要" +
                "知识库还是数据查询。如果意图是知识库的话result返回值是1，是数据查询的话result返回值是2。" +
                "最后返回值采用json数据格式，返回值格式为{result:result_$返回值}"
                ;

        return intentionPrompt;

    }

    public static String getDimensionMetricPrompt(String queryContent) {
        String dimMetricPrompt = "“" + queryContent + "” 请帮忙解析这句话中含有的维度与指标。解析要求：" +
                "1，语句中除了指标名词外，默认其他名词为维度。" +
                "2，最后的返回结果采用json数据格式，指定返回格式为{dimensions:array[$维度名称]，metrics:array[$指标名称]}。";

        return dimMetricPrompt;
    }

    public static String getSqlPrompt(String queryContent, String dbType, String schemaInfo, String dbDialect) {
        String sqlPrompt = "假设你是"+dbType+"的专家，需要通过问题描述和指令语句两部分内容帮忙生成对应查询SQL语句。第一部分问题说明：\n" +
                "“" + queryContent + "” \n" +
                "第二部分指令内容：\n" +
                "1，不能幻觉出现新的字段，schema字段、表名称、表字段名称必须使用提供的元数据内容信息，元数据信息json格式数据 " + schemaInfo + " \n" +
                "2，可能需要关联表的查询SQL才满足问题内容的要求，关联的表和表字段一定存在已经提供的元数据内容。\n" +
                "3，生产的sql语句使用json格式返回，返回数据格式要求{querySQL: $生成的结果SQL}。";

        return sqlPrompt;
    }

}
