package be.abis.exercise.it;

import be.abis.exercise.repository.CourseRepository;
import be.abis.exercise.service.AbisCourseService;
import be.abis.exercise.service.CourseService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCourseService {

    @Autowired
    CourseService courseService;

//    @Before
//    public void setUp() {
//        courseService = new AbisCourseService();
//        System.out.println("courseService instance = " + courseService);
//    }

    @Test
    public void findCourseById() {
        //arrange
        String expectedTitle = "Workshop SQL";
        int id = 7900;

        //act
        String resultTitle = courseService.findCourse(id).getShortTitle();

        //assert
        assertEquals(expectedTitle, resultTitle);
    }

    @Test
    public void priceCourseId7900IsHigherThan400Euros() {

        double pricePerDayToCompare = 400;
        
        double pricePerDayForId7900 = courseService.findCourse(7900).getPricePerDay();

        assertThat(pricePerDayForId7900, greaterThan(pricePerDayToCompare));
    }
}
