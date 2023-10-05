package com.am.chat.bi.controller;


import com.am.chat.bi.comm.Result;
import com.am.chat.bi.service.LLMsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
@Api(value = "大模型服务", description = "大模型服务", tags = {"LLMs APIß"})
public class LLMsController {

    @Autowired
    LLMsService llMsService;

    @PostMapping(value = "/intention", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询意图", notes = "查询意图", response = Result.class)
    public ResponseEntity<?> intention(@RequestBody String queryContent) {
        Result result = llMsService.getIntention(queryContent);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/dimMetrics", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询维度指标", notes = "查询维度指标", response = Result.class)
    public ResponseEntity<?> dimMetrics(@RequestBody String queryContent) {
        Result result = llMsService.getDimMetric(queryContent);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/querySql", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "生成查询sql", notes = "生成查询sql", response = Result.class)
    public ResponseEntity<?> querySql(@RequestBody String queryContent) {
        Result result = llMsService.getSql(queryContent);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
