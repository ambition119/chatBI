package com.am.chat.bi.service.impl;

import com.am.chat.bi.comm.ErrorCode;
import com.am.chat.bi.comm.Result;
import com.am.chat.bi.domain.vo.ResponseVo;
import com.am.chat.bi.service.LLMsService;
import com.am.chat.bi.utils.AliLLMsUtil;
import com.am.chat.bi.utils.LLMsResultUtil;
import com.am.chat.bi.utils.PromptUtil;
import com.google.gson.JsonElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LLMsServiceImpl implements LLMsService {

    private static final Logger LOG = LoggerFactory.getLogger(LLMsServiceImpl.class);

    @Override
    public Result getIntention(String queryContent) {
        LOG.info("chat query content: {}",  queryContent);
        try {
            String intentionPrompt = PromptUtil.getIntentionPrompt(queryContent);
            String intentionResult = AliLLMsUtil.getResult(intentionPrompt);
            String result = LLMsResultUtil.getIntentionResult(intentionResult);

            ResponseVo responseVo = new ResponseVo();
            responseVo.setName("result");
            responseVo.setValue(result);

            return Result.success().withResponse(responseVo);
        } catch (Exception e) {
            LOG.error("用户查询意图大模型结果判断异常：", e);
            return Result.fail(ErrorCode.FAIL_LLMs).withErrorMsg(e.getMessage());
        }
    }

    @Override
    public Result getDimMetric(String queryContent) {
        LOG.info("chat query content: {}",  queryContent);
        try {
            String dimensionMetricPrompt = PromptUtil.getDimensionMetricPrompt(queryContent);
            String dimensionMetricResult = AliLLMsUtil.getResult(dimensionMetricPrompt);

            String dimMetricJson = LLMsResultUtil.getDimMetricJson(dimensionMetricResult);

            JsonElement dimensions = LLMsResultUtil.getJsonValue(dimMetricJson, "dimensions");
            JsonElement metrics = LLMsResultUtil.getJsonValue(dimMetricJson, "metrics");

            ResponseVo responseVo = new ResponseVo();
            responseVo.setName("dimensions");
            responseVo.setValue(dimensions);

            responseVo.setName("metrics");
            responseVo.setValue(metrics);
            return Result.success().withResponse(responseVo);
        } catch (Exception e) {
            LOG.error("用户查询大模型维度指标解析异常：", e);
            return Result.fail(ErrorCode.FAIL_LLMs).withErrorMsg(e.getMessage());
        }
    }

    @Override
    public Result getSql(String queryContent) {
        LOG.info("chat query content: {}",  queryContent);
        try {
            //TODO： 待补充数据库类型，基于解析的元数据信息
            String schemaInfo = "{\n" +
                    "    \"schema\":\"report_db\",\n" +
                    "    \"tables\":[\n" +
                    "        \"dim_table\",\n" +
                    "        \"metric_table\"\n" +
                    "    ],\n" +
                    "    \"dim_table\":[\n" +
                    "        {\n" +
                    "            \"dim_date\":\"date\",\n" +
                    "            \"dim_app_id\":\"int\",\n" +
                    "            \"dim_app_name\":\"varchar\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"count_date\":\"date\",\n" +
                    "            \"app_id\":\"int\",\n" +
                    "            \"app_pv\":\"bigint\",\n" +
                    "            \"app_uv\":\"bigint\"\n" +
                    "        }\n" +
                    "    ]\n" +
                    "}";

            String sqlPrompt = PromptUtil.getSqlPrompt(queryContent,"postgresql",schemaInfo, null);
            LOG.info("LLMs sql prompt content: {}",  sqlPrompt);
            String sqlResult = AliLLMsUtil.getResult(sqlPrompt);
            String result = LLMsResultUtil.getSqlResult(sqlResult);

            ResponseVo responseVo = new ResponseVo();
            responseVo.setName("result");
            responseVo.setValue(result);

            return Result.success().withResponse(responseVo);
        } catch (Exception e) {
            LOG.error("用户查询大模型生成sql异常：", e);
            return Result.fail(ErrorCode.FAIL_LLMs).withErrorMsg(e.getMessage());
        }
    }
}
