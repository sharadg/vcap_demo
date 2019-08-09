package io.pivotal.vcap_services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class VcapServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(VcapServicesApplication.class, args);
    }

    @Value("${vcap.services.credhub-demo.credentials.jdbcUrl:Hello Warming World!}")
    private String credentials;

    @GetMapping("/")
    public String dumpVars() {
        return credentials;
    }

}
