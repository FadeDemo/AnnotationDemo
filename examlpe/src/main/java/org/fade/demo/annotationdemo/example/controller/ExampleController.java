package org.fade.demo.annotationdemo.example.controller;

import org.fade.demo.annotationdemo.example.service.ExampleService;
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
@RequestMapping("/example")
public class ExampleController {

    @Resource(name = "exampleServiceImpl")
    private ExampleService exampleService;

    @PostMapping("/save")
    public ResponseEntity<String> save() {
        // 传空值
        // fixme 拦截不生效
        exampleService.save(null, null, null, null);
        return ResponseEntity.ok("保存成功");
    }

}
