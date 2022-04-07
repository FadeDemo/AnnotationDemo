package org.fade.demo.operationrecorddemo.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class ExampleServiceTest {

    @Resource(name = "exampleServiceImpl")
    private ExampleService exampleService;

    @Test
    public void testAdd() {
        exampleService.add();
    }

    @Test
    public void testUpdate() {
        exampleService.update(1);
    }

}
