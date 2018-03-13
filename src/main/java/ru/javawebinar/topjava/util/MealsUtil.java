package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class MealsUtil {
    public static final List<Meal> MEALS = Arrays.asList(
            new Meal(LocalDateTime.of(2018, Month.MAY, 30, 10, 0), "Завтрак юзера 1", 500, 1),
            new Meal(LocalDateTime.of(2017, Month.MAY, 30, 13, 0), "Обед юзера 2", 1000, 2),
            new Meal(LocalDateTime.of(2020, Month.MAY, 30, 20, 0), "Ужин юзера 1", 500, 1),
            new Meal(LocalDateTime.of(2018, Month.MAY, 31, 10, 0), "Завтрак юзера 2", 1000, 2),
            new Meal(LocalDateTime.of(2018, Month.MAY, 31, 13, 0), "Обед юзера 1", 500, 1),
            new Meal(LocalDateTime.of(2018, Month.MAY, 31, 20, 0), "Ужин юзера 2", 510, 2),

            new Meal(LocalDateTime.of(2020, Month.MAY, 30, 10, 0), "Завтрак юзера 1", 500, 1),
            new Meal(LocalDateTime.of(2025, Month.MAY, 30, 13, 0), "Обед юзера 1", 1000, 1),
            new Meal(LocalDateTime.of(2030, Month.MAY, 30, 20, 0), "Ужин юзера 1", 500, 1),
            new Meal(LocalDateTime.of(2035, Month.MAY, 31, 10, 0), "Завтрак юзера 2", 1000, 2),
            new Meal(LocalDateTime.of(2022, Month.MAY, 31, 13, 0), "Обед юзера 2", 500, 2),
            new Meal(LocalDateTime.of(2021, Month.MAY, 31, 20, 0), "Ужин юзера 2", 510, 2)
    );

    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    public static List<MealWithExceed> getWithExceeded(Collection<Meal> meals, int caloriesPerDay) {
        return getFilteredWithExceeded(meals, caloriesPerDay, meal -> true);
    }

    public static List<MealWithExceed> getFilteredWithExceeded(Collection<Meal> meals, int caloriesPerDay, String startTime, String endTime) {

        LocalTime startLocalTime;
        LocalTime endLocalTime;

        if (startTime.isEmpty()) startLocalTime = LocalTime.MIN;
        else startLocalTime = LocalTime.parse(startTime);
        if (endTime.isEmpty()) endLocalTime = LocalTime.MAX;
        else endLocalTime = LocalTime.parse(endTime);

        return getFilteredWithExceeded(meals, caloriesPerDay, meal -> DateTimeUtil.isBetween(meal.getTime(), startLocalTime, endLocalTime));
    }

    private static List<MealWithExceed> getFilteredWithExceeded(Collection<Meal> meals, int caloriesPerDay, Predicate<Meal> filter) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
//                      Collectors.toMap(Meal::getDate, Meal::getCalories, Integer::sum)
                );
        return meals.stream()
                .filter(filter)
                .map(meal -> createWithExceed(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(toList());
    }

    private static MealWithExceed createWithExceed(Meal meal, boolean exceeded) {
        return new MealWithExceed(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), exceeded);
    }
}