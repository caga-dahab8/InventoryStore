package com.group1Project.InventoryStore.CustomConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private StringToProductConverter stringToProductConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToProductConverter);
    }
}
