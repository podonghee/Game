package com.podong.game.module.schduling;

import com.podong.game.module.schduling.batch.BatchClass;
import com.podong.game.module.schduling.batched.BasicClass;
import com.podong.game.module.schduling.module.impl.BatchSchdulingModuleImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@EnableScheduling
public class BatchScheduling implements SchedulingConfigurer {
    private final int POOL_SIZE = 5;

    @Autowired
    BatchSchdulingModuleImpl batchSchdulingModule;

    @Value("${batch.hs.useYn}")
    String syncYn;
    @Autowired
    private ApplicationContext context;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();

        threadPoolTaskScheduler.setPoolSize(POOL_SIZE);
        threadPoolTaskScheduler.setThreadNamePrefix("my-scheduled-task-pool-");
        threadPoolTaskScheduler.initialize();

        scheduledTaskRegistrar.setTaskScheduler(threadPoolTaskScheduler);
    }
    //@Scheduled(cron = "${batch.hs.rankTime}")
    public void gameRank() {
        scheduleStart("gameRankBatchClass");
    }
    //@Scheduled(cron = "${batch.hs.time}")
    public void game() {
        scheduleStart("gameBatchClass");
    }
    //@Scheduled(cron = "${batch.hs.time}")
    public void gameCompany() {
        scheduleStart("gameCompanyBatchClass");
    }
    //@Scheduled(cron = "${batch.hs.time}")
    public void gameSshot() {
        scheduleStart("gameSshotBatchClass");
    }
    @Scheduled(cron = "${batch.hs.time}")
    public void gameVideo() {
        scheduleStart("gameVideoBatchClass");
    }
    //@Scheduled(cron = "${batch.hs.time}")
    public void gameAttack() {
        scheduleStart("gameAttackBatchClass");
    }

    public void scheduleStart(String className){
        if("Y".equals(syncYn))
        {
            BatchClass adapter = new BatchClass();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Date now = new Date();
            String strDate = sdf.format(now);
            System.out.println("game Batch Start cron job Execute:: " + strDate);
            try {
                BasicClass basicClass = (BasicClass)context.getBean(className);
                adapter.setClassName(basicClass);
                adapter.start();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
