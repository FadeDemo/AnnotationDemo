package org.fade.demo.annotationdemo.example.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 例子
 *
 * @author fade
 * @date 2021/12/09
 */
@Data
public class Example {

    private Long id;

    private String name;

    private List<String> list;

    private Map<String, String> map;

}
