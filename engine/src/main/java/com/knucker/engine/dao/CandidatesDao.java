package com.knucker.engine.dao;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knucker.engine.model.Candidate;

public class CandidatesDao {

//	@Value("${engine.host}")
	public String host = "localhost";

//	@Value("${engine.host.port}")
	public String hostPort = "9200";

//	@Value("${engine.host.user}")
	public String user = "elastic";
	
//	@Value("${engine.host.pass}")
	public String pass = "changeme";
	
	public static Integer id = 1;

	RestClient restClient;

	public CandidatesDao() {
		// TODO Auto-generated constructor stub
		
		final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(user, pass));
		
		RestClientBuilder builder = RestClient.builder(new HttpHost(host, Integer.parseInt(hostPort)))
		        .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
		            @Override
		            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
		                httpClientBuilder.disableAuthCaching(); 
		                return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
		            }
		        });
		

		restClient = builder.build();
	}

	

	

	public String index(Candidate candidate, Map<String, String> params) throws IOException {
		
		HttpEntity entity = new StringEntity(new ObjectMapper().writeValueAsString(candidate));
		Response response = restClient.performRequest("POST", "/referral/candidate", params, entity);
		return EntityUtils.toString(response.getEntity());

	}

	public String fetchData() throws IOException {
		Map<String, String> params = Collections.singletonMap("pretty", "true");
		Response response = restClient.performRequest("GET", "/referral/_search", params);
		return EntityUtils.toString(response.getEntity());
	}

}
