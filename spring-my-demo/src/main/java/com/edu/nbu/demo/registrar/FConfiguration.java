package com.edu.nbu.demo.registrar;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(MyAutoConfigurationBeanRegistrar.class)
public class FConfiguration {
}
