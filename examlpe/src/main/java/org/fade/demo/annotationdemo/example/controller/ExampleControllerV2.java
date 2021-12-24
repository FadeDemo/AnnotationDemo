package org.fade.demo.annotationdemo.example.controller;

import org.fade.demo.annotationdemo.example.entity.Example;
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

    @PostMapping("/testValidateStringParameterIsNotBlank")
    public ResponseEntity<String> testValidateStringParameterIsNotBlank() {
        // 校验不通过
//        exampleService.testValidateStringParameterIsNotBlank(null);
//        exampleService.testValidateStringParameterIsNotBlank("");
//        exampleService.testValidateStringParameterIsNotBlank(" \t");
        // 校验通过
        exampleService.testValidateStringParameterIsNotBlank("test");
        return ResponseEntity.ok("操作成功");
    }

    @PostMapping("/testValidateNotFirstParameter")
    public ResponseEntity<String> testValidateNotFirstParameter() {
        // 校验不通过
//        exampleService.testValidateNotFirstParameter(null, "\t");
        // 校验通过
        exampleService.testValidateNotFirstParameter(null, "test");
        return ResponseEntity.ok("操作成功");
    }

    @PostMapping("/testValidateAllParameters")
    public ResponseEntity<String> testValidateAllParameters() {
        // 校验不通过
//        exampleService.testValidateAllParameters(null, null, null);
//        exampleService.testValidateAllParameters(1, null, null);
//        exampleService.testValidateAllParameters(1, Map.of(1, "test"), null);
        // 校验通过
        exampleService.testValidateAllParameters(1, Map.of(1, "test"), "test");
        return ResponseEntity.ok("操作成功");
    }

    @PostMapping("/testValidateParameterFields")
    public ResponseEntity<String> testValidateParameterFields() {
        Example example = new Example();
        // 校验不通过
//        exampleService.testValidateParameterFields(example);
        example.setId(1L);
//        exampleService.testValidateParameterFields(example);
        example.setName("test");
//        exampleService.testValidateParameterFields(example);
        example.setList(List.of("test"));
//        exampleService.testValidateParameterFields(example);
        // 校验通过
        example.setMap(Map.of("1", "test"));
        exampleService.testValidateParameterFields(example);
        return ResponseEntity.ok("操作成功");
    }

    @PostMapping("/testValidateParameterAndField")
    public ResponseEntity<String> testValidateParameterAndField() {
        // 校验不通过
        Example example = new Example();
//        exampleService.testValidateParameterAndField(example, null);
        example.setId(1L);
//        exampleService.testValidateParameterAndField(example, null);
        // 校验通过
        exampleService.testValidateParameterAndField(example, 1);
        return ResponseEntity.ok("操作成功");
    }

    @PostMapping("/testFieldsValidateAndFieldValidate")
    public ResponseEntity<String> testFieldsValidateAndFieldValidate() {
        // 校验不通过
//        exampleService.testFieldsValidateAndFieldValidate(null, null);
//        exampleService.testFieldsValidateAndFieldValidate(1, null);
        // 校验通过
        exampleService.testFieldsValidateAndFieldValidate(1, 1);
        return ResponseEntity.ok("操作成功");
    }

}
