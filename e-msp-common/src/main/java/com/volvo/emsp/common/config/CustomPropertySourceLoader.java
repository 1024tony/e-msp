package com.volvo.emsp.common.config;

import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * spring 中默认使用 PropertiesPropertySourceLoader 进行 properties 文件解析，
 * 在解析过程会利用 OriginTrackedPropertiesLoader 创建 CharacterReader,
 * CharacterReader 内部指定了 ISO_8859_1 编码，导致解析中文乱码
 *
 * @author gaoyang
 * @since 2025-04-12 13:27
 */
public class CustomPropertySourceLoader extends PropertiesPropertySourceLoader {

    @Override
    public List<PropertySource<?>> load(String name, Resource resource) throws IOException {
        Properties properties = loadUseUtf8(resource);
        if (!properties.isEmpty()) {
            PropertiesPropertySource source = new PropertiesPropertySource(name, properties);
            List<PropertySource<?>> list = new ArrayList<>();
            list.add(source);
            return list;
        }
        return super.load(name, resource);
    }

    private Properties loadUseUtf8(Resource resource) throws IOException {
        Properties props = new Properties();
        InputStream is = resource.getInputStream();
        try {
            String filename = resource.getFilename();
            if (filename != null && filename.endsWith(".xml")) {
                props.loadFromXML(is);
            } else {
                props.load(new InputStreamReader(is, StandardCharsets.UTF_8));
            }
        } finally {
            is.close();
        }
        return props;
    }

}
