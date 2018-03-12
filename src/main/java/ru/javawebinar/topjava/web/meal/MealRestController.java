package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.ValidationUtil;


import java.time.LocalDate;
import java.util.List;


@Controller
public  class MealRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;


    public List<Meal> getAll(int userID, LocalDate startTime, LocalDate endTime) {
        log.info("getAll");
        return service.getAll(userID, startTime, endTime);
    }

    public List<Meal> getAll(int userID) {
        log.info("getAll");
        return service.getAll(userID);
    }

    public Meal get(int id, int userID) {
        log.info("get {}", id);
        return service.get(id, userID);
    }

    public Meal create(Meal meal, int userID) {
        log.info("create {}", meal);
        ValidationUtil.checkNew(meal);
        return service.create(meal, userID);
    }

    public void delete(int id, int userID) {
        log.info("delete {}", id);
        service.delete(id, userID);
    }

    public void update(Meal meal, int userID, int mealID) {
        log.info("update {} with Userid={}", meal, userID);
        ValidationUtil.assureIdConsistent(meal, mealID);
        service.update(meal, userID);
    }

}
