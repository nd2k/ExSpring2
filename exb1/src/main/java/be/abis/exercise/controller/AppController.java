package be.abis.exercise.controller;

import be.abis.exercise.model.Person;
import be.abis.exercise.model.SignInForm;
import be.abis.exercise.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AppController {

    @Autowired
    TrainingService trainingService;

    @GetMapping("/")
    public String getIndexPage(Model model) {

        String viewName = "index";
        model.addAttribute("signInForm", new SignInForm());
        return viewName;
    }

    @GetMapping("/menu")
    public String getMenuPage() {
        String viewName = "menu";
        return viewName;
    }

    @PostMapping("/")
    public RedirectView signIn(@ModelAttribute SignInForm signInForm, Model model, RedirectAttributes redirectAttributes) {

        Person loggedUser = trainingService.findPerson(signInForm.getEmail(), signInForm.getPassword());

        if (loggedUser != null) {
            model.addAttribute("firstName", loggedUser.getFirstName());
            RedirectView redirectView = new RedirectView("/menu",true);
            redirectAttributes.addFlashAttribute("success", "You've been successfully logged in!");
            return redirectView;
        } else {
            RedirectView redirectView = new RedirectView("/",true);
            redirectAttributes.addFlashAttribute("error", "The given credentials are wrong");
            return redirectView;
        }
    }

    @GetMapping("/personadmin")
    public String personAdminPage() {
        String viewName = "personadmin";
        return viewName;
    }

    @GetMapping("/findcourses")
    public String findCoursesPage() {
        String viewName = "findcourses";
        return viewName;
    }
    
    @PostMapping("/signout")
    public String signOut(RedirectAttributes redirectAttributes) {
        String viewName = "redirect:/";
        redirectAttributes.addFlashAttribute("success", "You've been successfully sign out!");
        return viewName;
    }
//    @GetMapping("/exercise")
//    public ModelAndView getCourse() {
//
//        String viewName = "exercise";
//        Map<String, Object> model = new HashMap<>();
//
//        String courseTitle = trainingService.getCourseService().findCourse(7900).getLongTitle();
//        System.out.println(courseTitle);
//
//        Person personWithId3 = trainingService.findPerson(3);
//        String personId3FirstName = personWithId3.getFirstName();
//        String personId3LastName = personWithId3.getLastName();
//
//        model.put("courseTitle", courseTitle);
//        model.put("personFirstName", personId3FirstName);
//        model.put("personLastName", personId3LastName);
//
//        return new ModelAndView(viewName, model);
//    }
}
