package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

public interface MealRepository {
    Meal save(Meal meal, int userID);

    void delete(int id,int userID);

    Meal get(int mealID, int userID);

    Collection<Meal> getAll(int userID, LocalDate startTime, LocalDate endTime);
}
