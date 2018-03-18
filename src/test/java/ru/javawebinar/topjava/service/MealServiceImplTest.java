package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_1_ID;
import static ru.javawebinar.topjava.UserTestData.USER_2_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceImplTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void create() {
        Meal newMeal = new Meal(LocalDateTime.of(2018, Month.MAY, 30, 10, 0), "Завтрак", 500);
        Meal created = service.create(newMeal, USER_1_ID);
        newMeal.setId(created.getId());
        assertMatch(service.getAll(USER_1_ID),
                USER_1_MEAL_1, USER_1_MEAL_2, USER_1_MEAL_3, newMeal,
                USER_1_MEAL_4, USER_1_MEAL_5, USER_1_MEAL_6
        );
    }

    @Test
    public void update() {
        Meal updated = new Meal(USER_1_MEAL_1);
        updated.setDescription("Другой прием пищи");
        updated.setCalories(700);
        service.update(updated, USER_1_ID);
        assertMatch(service.get(USER_1_MEAL_1_ID, USER_1_ID), updated);
    }

    @Test
    public void delete() {
        service.delete(USER_1_MEAL_1_ID, USER_1_ID);
        assertMatch(service.getAll(USER_1_ID),
                USER_1_MEAL_2, USER_1_MEAL_3, USER_1_MEAL_4, USER_1_MEAL_5, USER_1_MEAL_6);
    }

    @Test
    public void get() {
        Meal meal = service.get(USER_1_MEAL_1_ID, USER_1_ID);
        assertMatch(meal, USER_1_MEAL_1);
    }

    @Test
    public void getAll() {
        assertMatch(service.getAll(USER_1_ID),
                USER_1_MEAL_1, USER_1_MEAL_2, USER_1_MEAL_3, USER_1_MEAL_4, USER_1_MEAL_5, USER_1_MEAL_6
        );
    }

    @Test
    public void getBetweenDateTimes() {
        List<Meal> meals = service.getBetweenDateTimes(
                LocalDateTime.of(2014, Month.MAY, 30, 10, 0),
                LocalDateTime.of(2016, Month.MAY, 30, 10, 0),
                USER_1_ID);
        assertMatch(meals, USER_1_MEAL_1, USER_1_MEAL_2, USER_1_MEAL_3);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(USER_1_MEAL_1_ID, USER_2_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        service.delete(USER_1_MEAL_1_ID, USER_2_ID);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() throws Exception {
        Meal updated = new Meal(USER_1_MEAL_1);
        updated.setDescription("Другой прием пищи");
        updated.setCalories(700);
        service.update(updated, USER_2_ID);
    }

    @Test(expected = DataAccessException.class)
    public void duplicatedDateTimeCreate() {
        Meal newMeal = new Meal(LocalDateTime.of(2018, Month.MAY, 30, 10, 0), "Завтрак", 500);
        Meal sameMeal = new Meal(LocalDateTime.of(2018, Month.MAY, 30, 10, 0), "Полдник", 700);
        service.create(newMeal, USER_1_ID);
        service.create(sameMeal, USER_1_ID);
    }

}