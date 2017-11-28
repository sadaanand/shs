package com.knucker.engine.dao;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.ParseException;
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
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.PropertySource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knucker.engine.model.JobPosition;

@PropertySource("classpath:appication.properties")
public class ReferralsDao {

//	@Value("${engine.host}")
	public String host = "localhost";

//	@Value("${engine.host.port}")
	public String hostPort = "9200";

//	@Value("${engine.host.user}")
	public String user = "elastic";
	
//	@Value("${engine.host.pass}")
	public String pass = "changeme";
	
	
	JobPosition position;

	public static Integer id = 1;

	RestClient restClient;

	public ReferralsDao() {
		
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

	public void setObject(JobPosition position) {
		this.position = position;
	}

	
	public String fetchEmployeeData(String jobId, String employeeId) throws IOException, ParseException, JSONException {
		
		Map<String, String> params = Collections.singletonMap("pretty", "true");
		Response job = restClient.performRequest("GET", "/referral/position/"+jobId, params);
		
		JSONObject json = new JSONObject(EntityUtils.toString(job.getEntity()));
		String skill = json.getString("coreSkills");
		
		Response candidates = restClient.performRequest("GET", "/referral/candidate/_search?q=core_skill:"+skill, params);
		JSONObject result = new JSONObject(EntityUtils.toString(candidates.getEntity()));
		
		String response = "Total Sugesstions : "+result.getString("hits.total")+" and candidates are >>>>> "+EntityUtils.toString(candidates.getEntity());
		
		return response;
		
	}

}
