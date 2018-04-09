package ru.javawebinar.topjava.service.Meal;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.AbstractMealServiceTest;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles({"datajpa"})
public class DataJpaMealServiceTest extends AbstractMealServiceTest {

    @Test
    public void getMealWithUser() throws Exception {
        Meal meal = service.getMealWithUser(MEAL1_ID, USER_ID);
        assertMatch(meal, MEAL1);
        UserTestData.assertMatch(meal.getUser(), USER);
    }

    @Test
    public void getMealWithUserNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.getMealWithUser(MEAL1_ID, ADMIN_ID);
    }

}