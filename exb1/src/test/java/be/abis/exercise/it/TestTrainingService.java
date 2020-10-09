package be.abis.exercise.it;

import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;
import be.abis.exercise.service.CourseService;
import be.abis.exercise.service.TrainingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTrainingService {

    @Autowired
    TrainingService trainingService;

    @Test
    public void findPersonById() {

        String expectedFirstName = "Bob";
        int id = 3;

        String resultFirstName = trainingService.findPerson(id).getFirstName();

        assertEquals(expectedFirstName, resultFirstName);

    }

    @Test
    public void findCoursebyIdFromTrainingService() {

        int id = 7900;
        String expectedCourseTitle = "Workshop SQL";

        String resultCourseTitle = trainingService.getCourseService().findCourse(id).getLongTitle();

        assertEquals(expectedCourseTitle, resultCourseTitle);
    }
}
