package com.knucker.engine.controller;

import java.io.IOException;

import org.apache.http.ParseException;
import org.json.JSONException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.knucker.engine.dao.ReferralsDao;

@RestController("/referral")
public class ReferralsController {

	ReferralsDao obj = new ReferralsDao();

	@RequestMapping(method = RequestMethod.GET)
	public String getAllJobs(@RequestParam(name = "JobId") String jobId, @RequestParam(name = "EmployeeId") String employeeId) throws IOException, ParseException, JSONException {

		return obj.fetchEmployeeData(jobId, employeeId);
		
	}
}
