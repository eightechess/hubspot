package com.hubteam.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hubteam.model.partners.MyPartners;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PartnerRequest {
    public MyPartners request_Message() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("https://candidate.hubteam.com/candidateTest/v3/problem/dataset?userKey=55b7f2f87ae0b1bd653a5d912149");
        HttpResponse httpresponse = httpclient.execute(httpget);
        ObjectMapper objectMapper = new ObjectMapper();
        MyPartners partnersList = objectMapper.readValue(httpresponse.getEntity().getContent(),MyPartners.class);
        return partnersList;
    }
}
