package be.abis.exercise.controller;

import be.abis.exercise.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AppController {

    @Autowired
    TrainingService trainingService;

    @GetMapping("/exercice")
    public ModelAndView getCourse() {

        String viewName = "course";
        Map<String, Object> model = new HashMap<>();

        String courseTitle = trainingService.getCourseService().findCourse(7900).getLongTitle();
        System.out.println(courseTitle);

        String personId3FirstName = trainingService.findPerson(3).getFirstName();
        String personId3LastName = trainingService.findPerson(3).getLastName();

        model.put("courseTitle", courseTitle);
        model.put("personFirstName", personId3FirstName);
        model.put("personLastName", personId3LastName);

        return new ModelAndView(viewName, model);
    }
}
