package org.fade.demo.annotationdemo.fieldvalidateannotation.version2.config;

import org.fade.demo.annotationdemo.fieldvalidateannotation.version2.aspect.FieldValidateAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fade
 * @date 2021/12/14
 */
@Configuration
public class FieldValidateAutoConfiguration {

    @Bean
    public FieldValidateAspect fieldValidateAspectV2() {
        return new FieldValidateAspect();
    }

}
