package com.justin.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testCollection() throws UnsupportedEncodingException {
        List<String> testStr = new ArrayList<>();
        testStr.add(null);

        System.out.println(testStr.isEmpty());

        testStr.add("a");
        testStr.add("b");

        String collect = testStr.stream().collect(Collectors.joining(","));
        System.out.println(collect);

        String encode = URLEncoder.encode(collect,"UTF-8");
        System.out.println(encode);

    }

}
