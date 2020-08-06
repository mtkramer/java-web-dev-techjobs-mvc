package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

    @RequestMapping(value = "results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        ArrayList<Job> jobs;
        if (searchTerm.length() <= 0) {
            jobs = JobData.findAll();
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        model.addAttribute("columns", columnChoices);
        model.addAttribute("title", "Jobs with " + columnChoices.get(searchType) + ": " + searchTerm);
        model.addAttribute("jobs", jobs);
        return "search.html";
    }

}

/*
Use the correct annotation for the method. To configure the correct mapping type and mapping route,
refer to the form tag in the search.html template.
The displaySearchResults method should take in a Model parameter.
The method should also take in two other parameters, specifying the type of search and the search term.
In order for these last two parameters to be properly passed in by Spring Boot, you need to use the
correct annotation. Also, you need to name them appropriately, based on the corresponding form field
names defined in search.html.
If the user enters “all” in the search box, or if they leave the box empty, call the findAll() method
from JobData. Otherwise, send the search information to findByColumnAndValue. In either case, store the
results in a jobs ArrayList.
Pass jobs into the search.html view via the model parameter.
Pass ListController.columnChoices into the view, as the existing search handler does.
 */