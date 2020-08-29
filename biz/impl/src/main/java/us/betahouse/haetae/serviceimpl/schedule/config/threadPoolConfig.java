package us.betahouse.haetae.serviceimpl.schedule.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class threadPoolConfig {
    private final Logger LOGGER = LoggerFactory.getLogger(threadPoolConfig.class);

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(20);
        threadPoolTaskScheduler.setThreadNamePrefix("threadPoolTaskScheduler-");
        threadPoolTaskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        threadPoolTaskScheduler.setAwaitTerminationSeconds(60);
        LOGGER.info("已经载入ThreadPoolTaskScheduler的Bean对象,对象为{}",threadPoolTaskScheduler);
        return threadPoolTaskScheduler;
    }
}
