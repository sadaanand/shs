package com.knucker.engine.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.knucker.engine.dao.CandidatesDao;
import com.knucker.engine.model.Candidate;

@RestController("/candidate")
public class CandidatesController {
	CandidatesDao candidatesDao = new CandidatesDao();

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody String createCandidate(@RequestBody Candidate model, @RequestParam(required = true) String routing,
			@RequestParam(required = true) String parent) throws IOException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("pretty", "true");
		params.put("routing", routing);
		params.put("parent", parent);
		return candidatesDao.index(model, params);
	}
}
