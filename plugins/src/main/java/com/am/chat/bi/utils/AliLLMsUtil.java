package com.am.chat.bi.utils;


import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.aigc.generation.models.QwenParam;
import com.alibaba.dashscope.common.ResultCallback;
import com.alibaba.dashscope.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;


public class AliLLMsUtil {
    private static final Logger LOG = LoggerFactory.getLogger(AliLLMsUtil.class);

    static {
        Constants.apiKey = "sk-547ca0b49140460a8d33f0e7c47c4de0";
    }

    public static String getResult(String prompt) throws Exception {
        Generation gen = new Generation();
        QwenParam param = QwenParam.builder().model(Generation.Models.QWEN_TURBO).prompt(prompt)
                .topP(0.8).build();
        Semaphore semaphore = new Semaphore(0);

        final String[] result = {null};
        gen.call(param, new ResultCallback<GenerationResult>() {

            @Override
            public void onEvent(GenerationResult message) {
                System.out.println(message);

                result[0] = message.getOutput().getText();
                LOG.info("阿里大模型返回结果：{}",message);
            }
            @Override
            public void onError(Exception ex){
                System.out.println(ex.getMessage());
                semaphore.release();
            }
            @Override
            public void onComplete(){
                System.out.println("onComplete");
                semaphore.release();
            }

        });
        semaphore.acquire();

        return result[0];
    }
}
