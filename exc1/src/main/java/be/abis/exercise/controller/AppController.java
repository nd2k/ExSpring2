package be.abis.exercise.controller;

import be.abis.exercise.model.*;
import be.abis.exercise.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

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

    @GetMapping("/findcoursebyid")
    public String findCourseByIdPage(Model model) {
        String viewName = "findcoursebyid";
        model.addAttribute("courseByIdForm", new CourseByIdForm());
        return viewName;
    }

    @PostMapping("/findcoursebyid")
    public String findCourseById(@ModelAttribute CourseByIdForm courseByIdForm, Model model, RedirectAttributes redirectAttributes) {
        System.out.println(courseByIdForm.getCourseId());
        Course retrievedCourse = trainingService.getCourseService().findCourse(courseByIdForm.getCourseId());
        System.out.println(retrievedCourse);
        if (retrievedCourse != null) {
            model.addAttribute("retrievedCourse", retrievedCourse);
            String viewName = "findcoursebyid";
            return viewName;
        } else {
            String viewName = "redirect:/findcoursebyid";
            redirectAttributes.addFlashAttribute("error", "The given id does not exists!");
            return viewName;
        }
    }
    
    @GetMapping("/findallcourses")
    public String findAllCourses(Model model) {
        List<Course> listOfCourses = trainingService.getCourseService().findAllCourses();
        String viewName = "findallcourses";
        model.addAttribute("listOfCourses", listOfCourses);
        return viewName;
    }

    @GetMapping("/findcoursebyshorttitle")
    public String findCourseByShortTitlePage(Model model) {
        String viewName = "findcoursebyshorttitle";
        model.addAttribute("courseByShortTitleForm", new CourseByShortTitleForm());
        return viewName;
    }

    @PostMapping("/findcoursebyshorttitle")
    public String findCourseByShortTitle(@ModelAttribute CourseByShortTitleForm courseByShortTitleForm, Model model, RedirectAttributes redirectAttributes) {
        Course retrievedCourse = trainingService.getCourseService().findCourse(courseByShortTitleForm.getShortTitle());
        if (retrievedCourse != null) {
            model.addAttribute("retrievedCourse", retrievedCourse);
            String viewName = "findcoursebyshorttitle";
            return viewName;
        } else {
            String viewName = "redirect:/findcoursebyshorttitle";
            redirectAttributes.addFlashAttribute("error", "The given title does not exists!");
            return viewName;
        }
    }

    @GetMapping("/changepassword")
    public String changePasswordPage(Model model) {
        String viewName = "changepassword";
        model.addAttribute("changePasswordForm", new ChangePasswordForm());
        return viewName;
    }

    @PostMapping("/changepassword")
    public String changePassword(@ModelAttribute ChangePasswordForm changePasswordForm, RedirectAttributes redirectAttributes) throws IOException {
        Person loggedUser = trainingService.findPerson(changePasswordForm.getEmail(), changePasswordForm.getPassword());
        if (loggedUser != null) {
            trainingService.changePassword(loggedUser, changePasswordForm.getNewPassword());
            redirectAttributes.addFlashAttribute("success", "Your password has been successfully changed!");
            String viewName = "redirect:/changepassword";
            return viewName;
        } else {
            redirectAttributes.addFlashAttribute("error", "The given credentials are wrong");
            String viewName = "redirect:/changepassword";
            return viewName;
        }
    }
    
    @GetMapping("/findperson")
    public String findPersonPage(Model model) {
        String viewName = "findperson";
        model.addAttribute("findPersonForm", new FindPersonForm());
        return viewName;
    }
    
    @PostMapping("/findperson")
    public String findPerson(@ModelAttribute FindPersonForm findPersonForm, Model model, RedirectAttributes redirectAttributes) {
        Person retrievedPersons = trainingService.findPerson(findPersonForm.getPersonId());
        if (retrievedPersons == null) {
            redirectAttributes.addFlashAttribute("error", "Person with ID: " + findPersonForm.getPersonId() + " does not exist");
            String viewName = "redirect:/findperson";
            return viewName;
        } else {
            model.addAttribute("retrievedPersons", retrievedPersons);
            String viewName = "/findperson";
            return viewName;
        }
    }

    @GetMapping("/findallperson")
    public String findAllPerson(Model model) {
        List<Person> retrievedPersons = trainingService.getAllPersons();
        model.addAttribute("retrievedPersons", retrievedPersons);
        String viewName = "/findallperson";
        return viewName;
    }

    @GetMapping("/addnewperson")
    public String addNewPersonPage(Model model) {
        String viewName = "addnewperson";
        model.addAttribute("person", new Person());
        return viewName;
    }

    @PostMapping("/addnewperson")
    public String addNewPerson(@Valid @ModelAttribute("person") Person person, RedirectAttributes redirectAttributes, BindingResult bindingResult, Model model) throws IOException {
        if(bindingResult.hasErrors()) {
            String viewName = "addnewperson";
            return viewName;
        }
        trainingService.addPerson(person);
        redirectAttributes.addFlashAttribute("success", "Person with ID: " + person.getPersonId() + " has been created");
        String viewName = "redirect:/addnewperson";
        return viewName;
    }

    @GetMapping("/deleteperson")
    public String deletePersonPage(Model model) {
        String viewName = "deleteperson";
        model.addAttribute("findPersonForm", new FindPersonForm());
        return viewName;
    }

    @PostMapping("/deleteperson")
    public String deletePersonById(@ModelAttribute FindPersonForm findPersonForm, Model model, RedirectAttributes redirectAttributes) {
        trainingService.deletePerson(findPersonForm.getPersonId());

        redirectAttributes.addFlashAttribute("success", "Person with ID: " + findPersonForm.getPersonId() + " is deleted");
        String viewName = "redirect:/deleteperson";
        return viewName;
    }
}
