package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.ValidationUtil;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Controller
public class MealRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public List<MealWithExceed> getAllFilteredByDates(int calories, String startDate, String endDate, String startTime, String endTime) {

        log.info("getAllFilteredByDates");

        LocalDate startLocalDate = startDate.isEmpty() ? LocalDate.MIN : LocalDate.parse(startDate);
        LocalDate endLocalDate = endDate.isEmpty() ? LocalDate.MAX : LocalDate.parse(endDate);
        LocalTime startLocalTime = startTime.isEmpty() ? LocalTime.MIN : LocalTime.parse(startTime);
        LocalTime endLocalTime = endTime.isEmpty() ? LocalTime.MAX : LocalTime.parse(endTime);

        return MealsUtil.getFilteredWithExceeded(service.getAllFilteredByDates(AuthorizedUser.id(),
                startLocalDate, endLocalDate), calories, startLocalTime, endLocalTime);
    }

    public List<MealWithExceed> getAll(int calories) {
        log.info("getAll");

        return MealsUtil.getWithExceeded(service.getAll(AuthorizedUser.id()), calories);
    }

    public Meal get(int id) {
        log.info("get {}", id);
        return service.get(id, AuthorizedUser.id());
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        ValidationUtil.checkNew(meal);
        return service.create(meal, AuthorizedUser.id());
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id, AuthorizedUser.id());
    }

    public void update(Meal meal, int mealID) {
        log.info("update {} with Userid={}", meal, AuthorizedUser.id());
        ValidationUtil.assureIdConsistent(meal, mealID);
        service.update(meal, AuthorizedUser.id());
    }
}