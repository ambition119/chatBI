package com.am.chat.bi.service;

import com.am.chat.bi.comm.Result;

public interface LLMsService {
    Result getIntention(String queryContent);
    Result getDimMetric(String queryContent);
    Result getSql(String queryContent);
}
