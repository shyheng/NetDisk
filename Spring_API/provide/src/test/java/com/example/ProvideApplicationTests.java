package com.example;

import com.example.net_file.service.INetFileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProvideApplicationTests {

    @Autowired
    INetFileService netFileService;

    @Test
    void contextLoads() {
        System.out.println(netFileService.list());
    }

}
