package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository {
    Meal save(Meal meal, int userID);

    Meal update(Meal meal, int userID);

    boolean delete(int id, int userID);

    Meal get(int mealID, int userID);

    List<Meal> getAllFilteredByDates(int userID, LocalDate startDate, LocalDate endDate);

    List<Meal> getAll(int userID);
}
