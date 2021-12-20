package org.fade.demo.annotationdemo.example.controller;

import org.fade.demo.annotationdemo.example.service.ExampleServiceV2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author fade
 * @date 2021/12/14
 */
@RestController
@RequestMapping("/exampleV2")
public class ExampleControllerV2 {

    @Resource(name = "exampleServiceV2Impl")
    private ExampleServiceV2 exampleService;

    @PostMapping("/testPointCutNormal")
    public ResponseEntity<String> testPointCutNormal() {
        exampleService.testPointCutNormal();
        return ResponseEntity.ok("操作成功");
    }

    @PostMapping("/testValidateParameterIsNotNull")
    public ResponseEntity<String> testValidateParameterIsNotNull() {
        // 校验不通过
//        exampleService.testValidateParameterIsNotNull(null);
        // 校验通过
        exampleService.testValidateParameterIsNotNull("测试");
        return ResponseEntity.ok("操作成功");
    }

    @PostMapping("/testValidateParameterIsNull")
    public ResponseEntity<String> testValidateParameterIsNull() {
        // 校验通过
        exampleService.testValidateParameterIsNull(null);
        // 校验不通过
//        exampleService.testValidateParameterIsNull("测试");
        return ResponseEntity.ok("操作成功");
    }

}
