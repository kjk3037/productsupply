package cn.zq;



import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class})
@ComponentScan(basePackages ={"cn.zq.config","cn.zq.*"})
@MapperScan("cn.zq.dao")
public class WebApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(WebApp.class,args);
    }
}
