package com.example.jobapiclient.apiservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestObject {

    private Applicant applicant;
    private int get_person_token;
    private boolean enqueue;
    private Job job;
    private PartnerAttributes partner_attributes;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Applicant {
        private String email;
        private String fullName;
        private String phoneNumber;
        private Resume resume;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Job {
        private String jobId;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Resume {
        private String data;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PartnerAttributes {
        private boolean sponsored;
        private String redirect_apply;
        private String utm_medium;
        private String utm_campaign;
        private String keyword;
        private String search_fetch_id;
        private long page;
        private long order;
        private String city_center;
        private String utm_source;
        private String source;
    }
}