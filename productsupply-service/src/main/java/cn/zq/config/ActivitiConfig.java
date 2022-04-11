package cn.zq.config;

import org.activiti.engine.*;
import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ActivitiConfig extends AbstractProcessEngineAutoConfiguration {

    @Autowired
    DataSource dataSource;
    @Autowired
    ProcessEngine processEngine;
    @Bean
    public ProcessEngine getEngine() {
        return ProcessEngines.getDefaultProcessEngine();
    }

    @Bean
    public TaskService getTaskService() {
        return processEngine.getTaskService();
    }

    @Bean
    public RuntimeService getRuntimeService() {
        return processEngine.getRuntimeService();
    }

    @Bean
    public RepositoryService getRepositoryService() {
        return processEngine.getRepositoryService();
    }

    @Bean
    public ManagementService getManagementService() {
        return processEngine.getManagementService();
    }

    @Bean
    public HistoryService getHistoryService() {
        return processEngine.getHistoryService();
    }
}