package com.knucker.connectorMaster.linkedIn;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/linkedIn")
public class LinkedInController {

    private LinkedIn linkedIn;
    private ConnectionRepository connectionRepository;

    public LinkedInController(LinkedIn ln, ConnectionRepository connectionRepository) {
        this.linkedIn = ln;
        this.connectionRepository = connectionRepository;
    }

    @GetMapping
    public String helloLinedIn(Model model) {
        if (connectionRepository.findPrimaryConnection(LinkedIn.class) == null) {
            return "redirect:/connect/linkedin";
        }

        model.addAttribute("linkedInProfile", linkedIn.profileOperations().getUserProfile());
        return "helloLinkedIN";
    }

}