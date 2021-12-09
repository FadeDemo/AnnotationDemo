package org.fade.demo.annotationdemo.example.service;

import org.fade.demo.annotationdemo.example.entity.Example;
import org.fade.demo.annotationdemo.fieldvalidateannotation.version1.annotation.FieldsValidate;

import java.util.Collection;
import java.util.Map;

/**
 * 例子Service
 *
 * @author fade
 * @date 2021/12/09
 */
public interface ExampleService {

    /**
     * 保存
     * @param example {@link Example}实体类
     * @param str {@link String}
     * @param collection {@link Collection}
     * @param map {@link Map}
     * */
    void save(Example example, String str, Collection<?> collection, Map<?, ?> map);

}
