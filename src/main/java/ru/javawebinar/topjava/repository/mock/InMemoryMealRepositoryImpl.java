package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
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
            // For demonstration purposes only!
            // I don't want to create "save" method without "userID because of laziness
            this.save(meal, meal.getUserId());
        }
    }

    @Override
    public Meal get(int mealID, int userID) {
        Meal meal = repository.get(mealID);
        if (meal.getUserId() == userID) return meal;
        else return null;
    }

    @Override
    public Meal save(Meal meal, int userID) {

        meal.setId(counter.incrementAndGet());
        meal.setUserId(userID);
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public Meal update(Meal meal, int userID) {

        if (repository.get(meal.getId()).getUserId() == userID) {
            meal.setUserId(userID);
            return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
        }
        return null;
    }

    @Override
    public boolean delete(int mealID, int userID) {
        if (repository.get(mealID).getUserId() == userID) {
            return null != repository.remove(mealID);
        }
        return false;
    }

    @Override
    public List<Meal> getAll(int userID) {

        return repository.entrySet().
                stream().filter(e -> e.getValue().getUserId() == userID)
                .map(Map.Entry::getValue).sorted(Collections.reverseOrder(Comparator.comparing(Meal::getDateTime))).
                        collect(Collectors.toList());
    }

    public List<Meal> getAllFilteredByDates(int userID, LocalDate startDate, LocalDate endDate) {

    return     getAll(userID).stream().filter(e -> DateTimeUtil.isBetweenDate(e.getDate(), startDate, endDate)).
                sorted(Collections.reverseOrder(Comparator.comparing(Meal::getDateTime))).
                collect(Collectors.toList());

//        return repository.entrySet().
//                stream().filter(e -> e.getValue().getUserId() == userID)
//                .filter(e -> DateTimeUtil.isBetweenDate(e.getValue().getDate(), startDate, endDate)).
//                        map(Map.Entry::getValue).sorted(Collections.reverseOrder(Comparator.comparing(Meal::getDateTime))).
//                        collect(Collectors.toList());
    }
}