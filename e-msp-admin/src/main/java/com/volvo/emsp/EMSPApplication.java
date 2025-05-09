package com.volvo.emsp;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Application
 *
 * @author gaoyang
 * @since 2025-04-12 13:27
 */
@Slf4j
@EnableTransactionManagement
@MapperScan("com.volvo.emsp.**.mapper")
@SpringBootApplication(scanBasePackages = {"com.volvo.emsp"})
public class EMSPApplication {

    public static void main(String[] args) throws UnknownHostException {
        final ConfigurableApplicationContext context = new SpringApplicationBuilder()
                .sources(EMSPApplication.class)
                .run(args);

        final Environment env = context.getEnvironment();
        EMSPApplication.log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! ActiveProfiles is '{}', Access URLs:\n\t" +
                        "Local: \t\thttp://127.0.0.1:{}\n\t" +
                        "External: \thttp://{}:{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                env.getProperty("spring.profiles.active"),
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"));
    }
}
