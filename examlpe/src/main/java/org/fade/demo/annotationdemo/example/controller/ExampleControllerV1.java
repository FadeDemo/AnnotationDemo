package org.fade.demo.annotationdemo.example.controller;

import org.fade.demo.annotationdemo.example.service.ExampleServiceV1;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 例子Controller
 *
 * @author fade
 * @date 2021/12/09
 */
@RestController
@RequestMapping("/exampleV1")
@Deprecated
public class ExampleControllerV1 {

    @Resource(name = "exampleServiceV1Impl")
    private ExampleServiceV1 exampleServiceV1;

    @PostMapping("/save")
    public ResponseEntity<String> save() {
        // 校验字符串
        // 为空校验
//        exampleService.testString("", null, null, null);
        // 非空校验
//        exampleService.testString(null, null, null, null);
        // 空字符串校验
//        exampleService.testString(null, "", "test", null);
        // 非空字符串校验
//        exampleService.testString(null, "", "", "");
        return ResponseEntity.ok("保存成功");
    }

}
