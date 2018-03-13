package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {

    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();

    private AtomicInteger counter = new AtomicInteger(0);

    {
        for (Meal meal : MealsUtil.MEALS) {
            this.save(meal, meal.getUserId());
        }
    }

    @Override
    public Meal get(int mealID, int userID) {
        Meal meal = repository.get(mealID);
        if (meal != null && meal.getUserId() == userID) return meal;
        else return null;
    }

    @Override
    public Meal save(Meal meal, int userID) {
        if (meal != null) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(userID);
            repository.put(meal.getId(), meal);
        }
        return meal;
    }

    @Override
    public Meal update(Meal meal, int userID) {
        if (meal != null && meal.getId() != null &&
                repository.get(meal.getId()).getUserId() == userID) {
            meal.setUserId(userID);
            return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
        }
        return null;
    }

    @Override
    public boolean delete(int mealID, int userID) {
        Meal meal = repository.get(mealID);
        if (meal != null && meal.getUserId() == userID) {
            return null != repository.remove(mealID);
        }
        return false;
    }

    @Override
    public List<Meal> getAll(int userID) {
        return repository.values().stream().filter(e -> e.getUserId() == userID)
                .sorted(Collections.reverseOrder(Comparator.comparing(Meal::getDateTime))).
                        collect(Collectors.toList());
    }

    @Override
    public List<Meal> getAllFilteredByDates(int userID, LocalDate startDate, LocalDate endDate) {
        return repository.values().stream().filter(e -> e.getUserId() == userID)
                .filter(e -> DateTimeUtil.isBetweenDate(e.getDate(), startDate, endDate))
                .sorted(Collections.reverseOrder(Comparator.comparing(Meal::getDateTime))).
                        collect(Collectors.toList());
    }
}