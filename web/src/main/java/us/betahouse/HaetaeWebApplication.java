package us.betahouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import us.betahouse.haetae.serviceimpl.common.init.InitService;

import javax.annotation.PostConstruct;

@EnableJpaAuditing
@EnableTransactionManagement
@EnableAspectJAutoProxy
@ImportResource(locations = {"classpath:spring/user.xml", "classpath:spring/biz-service.xml"})
@SpringBootApplication
@EnableScheduling
public class HaetaeWebApplication {

    @Autowired
    private InitService initService;

    private static HaetaeWebApplication app;

    public static void main(String[] args) {
        SpringApplication.run(HaetaeWebApplication.class, args);
        // 应用初始化
        app.initService.init();
    }

    @PostConstruct
    private void init() {
        app = this;
        app.initService = this.initService;
    }
}
