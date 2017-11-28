package com.knucker.engine.dao;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
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
import org.springframework.context.annotation.PropertySource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knucker.engine.model.JobPosition;

@PropertySource("classpath:appication.properties")
public class JobPositionDao {

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

	public JobPositionDao() {
		// TODO Auto-generated constructor stub
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
				

				restClient = builder.build();//RestClient.builder(new HttpHost("localhost", 9200, "http")).build();
		//restClient = RestClient.builder(new HttpHost("localhost", 9200, "http")).build();
	}

	public void setObject(JobPosition position) {
		this.position = position;
	}

	public void addToDB() throws IOException {

		HashMap<String, String> jsonMap = new HashMap<>();
		jsonMap.put("position", position.getPositionName());
		jsonMap.put("description", position.getDescription());
		jsonMap.put("exp", position.getRequiredExperience());
		jsonMap.put("coreSkills", position.getcoreSkills());
		jsonMap.put("subSkills", position.getsubSkills());
		jsonMap.put("contact", position.getContactPerson());

		addRequest(jsonMap);

	}

	private void addRequest(HashMap<String, String> jsonMap) throws IOException {
		Map<String, String> params = Collections.singletonMap("pretty", "true");
		HttpEntity entity = new StringEntity(new ObjectMapper().writeValueAsString(jsonMap));
		Response response = restClient.performRequest("POST", "/referral/position", params, entity);
		System.out.println(EntityUtils.toString(response.getEntity()));

	}

	public String fetchData() throws IOException {
		Map<String, String> params = Collections.singletonMap("pretty", "true");
		Response response = restClient.performRequest("GET", "/referral/_search", params);
		return EntityUtils.toString(response.getEntity());
	}

}
