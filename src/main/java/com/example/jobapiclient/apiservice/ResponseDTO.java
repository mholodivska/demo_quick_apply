package com.example.jobapiclient.apiservice;

import lombok.Data;

@Data
public class ResponseDTO {

    private String developer_message;
    private String[] errors;
    private Info info;

    @Data
    private class Info {
        private long person_id;
        private String bearer_token;
        private long job_id;
    }
}
