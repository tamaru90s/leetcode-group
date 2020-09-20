package com.tamaru.leetcodegroup.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.PrintStream;
import java.util.Map;

@Service
public class QueryService {
    public void query() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://leetcode-cn.com/api/user_submission_calendar/tamaru-m/";
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        if (entity.getStatusCode() != HttpStatus.OK) {
            return;
        }
        String body = StringEscapeUtils.unescapeJava(entity.getBody()).substring(1, StringEscapeUtils.unescapeJava(entity.getBody()).length() - 1);
        Map<Long, Integer> map = JSON.parseObject(body, new TypeReference<>() {
        });
        map.forEach((k, v) -> process(k, v));
    }

    private PrintStream process(Long k, Integer v) {
        return System.out.printf("key:%d, value:%d\n", k, v);
    }
}
