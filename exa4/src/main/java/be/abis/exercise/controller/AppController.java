package be.abis.exercise.controller;

import be.abis.exercise.model.Person;
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

    @GetMapping("/exercise")
    public ModelAndView getCourse() {

        String viewName = "exercise";
        Map<String, Object> model = new HashMap<>();

        String courseTitle = trainingService.getCourseService().findCourse(7900).getLongTitle();
        System.out.println(courseTitle);

        Person personWithId3 = trainingService.findPerson(3);
        String personId3FirstName = personWithId3.getFirstName();
        String personId3LastName = personWithId3.getLastName();

        model.put("courseTitle", courseTitle);
        model.put("personFirstName", personId3FirstName);
        model.put("personLastName", personId3LastName);

        return new ModelAndView(viewName, model);
    }
}
