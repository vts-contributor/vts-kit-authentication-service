package auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"vn.com.viettel","auth"})
@EnableFeignClients(basePackages = {"auth"})
public class BackEndTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackEndTemplateApplication.class, args);
    }

}
