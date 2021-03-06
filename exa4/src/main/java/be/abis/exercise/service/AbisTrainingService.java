package be.abis.exercise.service;

import be.abis.exercise.exception.EnrollException;
import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AbisTrainingService implements TrainingService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    CourseService courseService;

    @Override
    public ArrayList<Person> getAllPersons() {
        return personRepository.getAllPersons();
    }

    @Override
    public Person findPerson(int id) {
        return personRepository.findPerson(id);
    }

    @Override
    public Person findPerson(String emailAddress, String passWord) {
        return personRepository.findPerson(emailAddress, passWord);
    }

    @Override
    public void addPerson(Person p) throws IOException {
        personRepository.addPerson(p);
    }

    @Override
    public void deletePerson(int id) {
        personRepository.deletePerson(id);
    }

    @Override
    public void changePassword(Person p, String newPswd) throws IOException {
        personRepository.changePassword(p, newPswd);
    }

    @Override
    public List<Course> showFollowedCourses(Person person) {
        return null;
    }

    @Override
    public void enrollForSession(Person person, Course course, LocalDate date) throws EnrollException {

    }

    @Override
    public CourseService getCourseService() {
        return courseService;
    }

    @Override
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }
}
