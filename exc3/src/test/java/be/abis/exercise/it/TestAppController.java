package be.abis.exercise.it;

import be.abis.exercise.controller.AppController;
import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;
import be.abis.exercise.service.CourseService;
import be.abis.exercise.service.TrainingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.print.Book;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@WebMvcTest(AppController.class)
@RunWith(SpringRunner.class)
public class TestAppController {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    TrainingService trainingService;
    @MockBean
    CourseService courseService;

    @Before
    public void init() {

        Course expectedCourse = new Course("7900","Workshop SQL","Workshop SQL",3,475.0);

        when(courseService.findCourse(7900)).thenReturn(expectedCourse);

        Person expectedPerson = new Person();

        when(trainingService.findPerson(3)).thenReturn(expectedPerson);

    }

    @Test
    public void testShowExercisePage() throws Exception {

        System.out.println(trainingService);
        System.out.println(courseService);
        when(trainingService.getCourseService()).thenReturn(courseService);
        mockMvc.perform(get("/exercise"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("exercise"));
    }
}
