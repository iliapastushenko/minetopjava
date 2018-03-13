package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.ValidationUtil;


import java.time.LocalDate;
import java.util.List;


@Controller
public class MealRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public List<Meal> getAllFilteredByDates(String startDateString, String endDateForString) {

        log.info("getAllFilteredByDates");

        LocalDate startLocalDate;
        LocalDate endLocalDate;

        if (startDateString.isEmpty()) startLocalDate = LocalDate.MIN;
        else startLocalDate = LocalDate.parse(startDateString);
        if (endDateForString.isEmpty()) endLocalDate = LocalDate.MAX;
        else endLocalDate = LocalDate.parse(endDateForString);

        return service.getAllFilteredByDates(AuthorizedUser.id(), startLocalDate, endLocalDate);
    }

    public List<Meal> getAll() {
        log.info("getAll");
        return service.getAll(AuthorizedUser.id());
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