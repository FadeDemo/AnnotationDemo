package org.fade.demo.annotationdemo.fieldvalidateannotation.version1.config;

import org.fade.demo.annotationdemo.fieldvalidateannotation.version1.aspect.FieldValidateAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fade
 * @date 2021/12/09
 */
@Configuration
public class FieldValidateConfiguration {

    @Bean
    public FieldValidateAspect fieldValidateAspect() {
        return new FieldValidateAspect();
    }

}
