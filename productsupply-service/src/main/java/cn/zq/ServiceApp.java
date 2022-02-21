package cn.zq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages ={"cn.zq.config","cn.zq.*"})
@MapperScan("cn.zq.dao")
public class ServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(ServiceApp.class);
    }
}
