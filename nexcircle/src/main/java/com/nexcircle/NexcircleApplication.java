package com.nexcircle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.InetAddress;

@SpringBootApplication
@EnableScheduling
public class NexcircleApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context =
                SpringApplication.run(NexcircleApplication.class, args);

        String port = context.getEnvironment().getProperty("server.port");
        String host = InetAddress.getLocalHost().getHostAddress();


        System.out.println("\nNexcircle started at:");
        System.out.println("Local:   http://localhost:" + port);
        System.out.println("Network: http://" + host + ":" + port + "\n");
    }
}
