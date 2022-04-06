package org.fade.demo.operationrecord;

import org.fade.demo.operationrecord.aspect.OperationRecordAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 操作记录自动配置
 *
 * @author fade
 * @date 2022/04/06
 */
@Configuration
public class OperationRecordAutoConfiguration {

    @Bean
    public OperationRecordAspect operationRecordAspect() {
        return new OperationRecordAspect();
    }

}
