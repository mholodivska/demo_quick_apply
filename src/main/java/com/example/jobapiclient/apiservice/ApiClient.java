package com.example.jobapiclient.apiservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ApiClient {
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String applyUrl = "https://partners.apploi.com/v1/application/easy-apply/";

    public ResponseDTO quickApply() {
        RequestObject requestObject = createJsonRequestObject();
        Map<String, Object> fields = objectMapper.convertValue(requestObject, Map.class);

        System.out.println(new JSONObject(fields).toString());
        var request = new HttpEntity<>(new JSONObject(fields).toString(), getHeaders());
        System.out.println(request);
        ResponseEntity<ResponseDTO> responseEntity = restTemplate.postForEntity(applyUrl, request, ResponseDTO.class);
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new HttpClientErrorException(responseEntity.getStatusCode());
        }
        return responseEntity.getBody();
    }

    private RequestObject createJsonRequestObject() {
        RequestObject.Applicant applicant = new RequestObject.Applicant( "{{email}}", "{{fullName}}", "{{phoneNumber}}", new RequestObject.Resume(""));
        RequestObject.Job job = new RequestObject.Job("{{job_id}}");
        RequestObject.PartnerAttributes partner_attributes = new RequestObject.PartnerAttributes(true,
                "https://jobs-univision.apploi.com/view/468036?utm_campaign=integration&utm_medium=job-board-search&utm_source=<utm_source>-boosted&ajs_event=LOAD_JOB_PAGE&ajs_aid=3f617530-4530-11e9-a2ca-7e775511d9e3&ajs_prop_search_fetch_id=efb330ceef1b44e3b07328adae1b1ccd&ajs_prop_keyword=&ajs_prop_page=1&ajs_prop_city_center=Unselected&ajs_prop_search_order=5&ajs_prop_job_id=468036&ajs_prop_doc_type=preferredjob&ajs_prop_job_location_lat=39.3798123&ajs_prop_job_location_lon=-74.5302804&ajs_prop_boosted=1&ajs_prop_utm_source=<utm_source>-boosted&ajs_prop_utm_medium=job-board-search&ajs_prop_utm_campaign=integration",
                "job-board-search",
                "integration",
                "",
                "efb330ceef1b44e3b07328adae1b1ccd",
                1,
                5,
                "Unselected",
                "<exploratory>-quick-apply-boosted",
                "<source>-apply");

        return new RequestObject(applicant, 1, true, job, partner_attributes);
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("accept", "*/*");
        headers.set("x-api-key", "some-api-key");
        return headers;
    }
}
