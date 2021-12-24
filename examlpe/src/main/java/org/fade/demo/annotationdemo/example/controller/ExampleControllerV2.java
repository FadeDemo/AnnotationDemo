package org.fade.demo.annotationdemo.example.controller;

import org.fade.demo.annotationdemo.example.service.ExampleServiceV2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;

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

    @PostMapping("/testValidateCollectionParameterIsNotEmpty")
    public ResponseEntity<String> testValidateCollectionParameterIsNotEmpty() {
        // 校验不通过
//        exampleService.testValidateCollectionParameterIsNotEmpty(null);
        List<Object> list = new ArrayList<>(16);
//        exampleService.testValidateCollectionParameterIsNotEmpty(list);
//        Set<Object> set = new HashSet<>(16);
//        exampleService.testValidateCollectionParameterIsNotEmpty(set);
        // 校验通过
        list.add(true);
        exampleService.testValidateCollectionParameterIsNotEmpty(list);
        return ResponseEntity.ok("操作成功");
    }

    @PostMapping("/testValidateMapParameterIsNotEmpty")
    public ResponseEntity<String> testValidateMapParameterIsNotEmpty() {
        Map<Object, Object> map = new HashMap<>(16);
        // 校验不通过
//        exampleService.testValidateMapParameterIsNotEmpty(null);
//        exampleService.testValidateMapParameterIsNotEmpty(map);
        // 校验通过
        map.put("test", true);
        exampleService.testValidateMapParameterIsNotEmpty(map);
        return ResponseEntity.ok("操作成功");
    }

    @PostMapping("/testValidateStringParameterIsNotEmpty")
    public ResponseEntity<String> testValidateStringParameterIsNotEmpty() {
        // 校验不通过
//        exampleService.testValidateStringParameterIsNotEmpty(null);
//        exampleService.testValidateStringParameterIsNotEmpty("");
        // 校验通过
        exampleService.testValidateStringParameterIsNotEmpty(" ");
        exampleService.testValidateStringParameterIsNotEmpty("test");
        return ResponseEntity.ok("操作成功");
    }

    @PostMapping("/testValidateCollectionParameterIsEmpty")
    public ResponseEntity<String> testValidateCollectionParameterIsEmpty() {
        // 校验不通过
//        List<? extends Serializable> list = List.of(1, "test");
//        Set<? extends Serializable> set = Set.of(1, "test");
//        exampleService.testValidateCollectionParameterIsEmpty(list);
//        exampleService.testValidateCollectionParameterIsEmpty(set);
        // 校验通过
        exampleService.testValidateCollectionParameterIsEmpty(null);
        exampleService.testValidateCollectionParameterIsEmpty(List.of());
        exampleService.testValidateCollectionParameterIsEmpty(Set.of());
        return ResponseEntity.ok("操作成功");
    }

    @PostMapping("/testValidateMapParameterIsEmpty")
    public ResponseEntity<String> testValidateMapParameterIsEmpty() {
        // 校验不通过
//        exampleService.testValidateMapParameterIsEmpty(Map.of(1, "test"));
        // 校验通过
        exampleService.testValidateMapParameterIsEmpty(null);
        exampleService.testValidateMapParameterIsEmpty(Map.of());
        return ResponseEntity.ok("操作成功");
    }

    @PostMapping("/testValidateStringParameterIsEmpty")
    public ResponseEntity<String> testValidateStringParameterIsEmpty() {
        // 校验不通过
//        exampleService.testValidateStringParameterIsEmpty(" ");
//        exampleService.testValidateStringParameterIsEmpty("test");
        // 校验通过
        exampleService.testValidateStringParameterIsEmpty(null);
        exampleService.testValidateStringParameterIsEmpty("");
        return ResponseEntity.ok("操作成功");
    }

}
