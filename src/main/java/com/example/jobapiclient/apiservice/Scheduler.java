package com.example.jobapiclient.apiservice;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class Scheduler {
    private final ApiClient apiClient;

    // scheduler to demonstrate work of apiclient
    @Scheduled(cron = "0 * * * * *")
    public void generateHistoricalPeHistoryChartTask() {
        apiClient.quickApply();
    }
}
