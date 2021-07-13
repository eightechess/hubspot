package com.hubteam.services;

import com.hubteam.model.countries.Countries;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class PartnerResponse {
    public int send_Message(List<Countries> countries) throws NoSuchAlgorithmException, KeyStoreException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, KeyManagementException, IOException, IOException, UnsupportedEncodingException, KeyManagementException {
        SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        int responseCode = 0;
        HttpPost post = new HttpPost("https://candidate.hubteam.com/candidateTest/v3/problem/result?userKey=55b7f2f87ae0b1bd653a5d912149");
        Map<String, String> mymap = BeanUtils.describe(countries);
        List<NameValuePair> nvpList = new ArrayList<>(mymap.size());
        for (Map.Entry<String, String> entry : mymap.entrySet()) {
            nvpList.add(new NameValuePair() {

                @Override
                public String getValue() {
                    return entry.getValue();
                }

                @Override
                public String getName() {
                    return entry.getKey();
                }
            });
        }
        post.setEntity(new UrlEncodedFormEntity(nvpList, "UTF-8"));
        HttpResponse response = httpclient.execute(post);
        responseCode = response.getStatusLine().getStatusCode();
        return responseCode;
    }
}
