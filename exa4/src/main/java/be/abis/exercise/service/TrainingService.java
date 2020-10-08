package be.abis.exercise.service;

import be.abis.exercise.exception.EnrollException;
import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface TrainingService {

    ArrayList<Person> getAllPersons();
    Person findPerson(int id);
    Person findPerson(String emailAddress, String passWord);
    void addPerson(Person p) throws IOException;
    public void deletePerson(int id);
    void changePassword(Person p, String newPswd) throws IOException;
    List<Course> showFollowedCourses(Person person);
    void enrollForSession(Person person, Course course, LocalDate date) throws EnrollException;
    CourseService getCourseService();
    void setCourseService(CourseService courseService);

}
