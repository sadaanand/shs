package com.knucker.engine.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.knucker.engine.dao.JobPositionDao;
import com.knucker.engine.model.JobPosition;

@RestController("/job")
public class JobPositionController {

	JobPositionDao obj = new JobPositionDao();

	@RequestMapping(method = RequestMethod.POST)
	public JobPosition addJobPosition(@RequestParam(name = "position") String positionName,
			@RequestParam(name = "exp") String requiredExp, @RequestParam(name = "core") String coreSkills,
			@RequestParam(name = "sub") String subSkills, @RequestParam(name = "description") String description,
			@RequestParam(name = "contact") String contactPerson) throws IOException {

		JobPosition position = new JobPosition();

		position.setPositionName(positionName);

		position.setRequiredExperience(requiredExp);

		position.setCoreSkills(coreSkills);

		position.setSubSkills(subSkills);

		position.setDescription(description);

		position.setContactPerson(contactPerson);

		obj.setObject(position);
		obj.addToDB();

		return position;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getAllJobs() throws IOException {

		String response = obj.fetchData();

		return response;
	}
}
