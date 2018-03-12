package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.AuthorizedUser;
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


        for (Meal meal : MealsUtil.MEALS)

        {

            this.save(meal, AuthorizedUser.id());
        }

    }


    @Override
    public Meal get(int mealID, int userID) {

        return repository.entrySet().stream().filter(e -> e.getValue().getUserId() == userID)
                .filter(e -> e.getKey() == mealID).map(Map.Entry::getValue).findFirst().get();
    }


    @Override
    public Meal save(Meal meal, int userID) {
        if (
                meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(userID);
            repository.put(meal.getId(), meal);
            return meal;
        }
        // treat case: update, but absent in storage

        meal.setUserId(userID);

        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int mealID, int userID) {

        return repository.entrySet().removeIf(entry -> entry.getKey() == mealID && entry.getValue().getUserId() == userID);

    }


    @Override
    public List<Meal> getAll(int userID, LocalDate startDate, LocalDate endDate) {

        return repository.entrySet().
                stream().filter(e -> e.getValue().getUserId() == userID)
                .filter(e -> DateTimeUtil.isBetweenDate(e.getValue().getDate(), startDate, endDate)).
                        map(Map.Entry::getValue).sorted(Collections.reverseOrder(Comparator.comparing(Meal::getDateTime))).
                        collect(Collectors.toList());
    }

    @Override
    public List<Meal> getAll(int userID) {

        return repository.entrySet().
                stream().filter(e -> e.getValue().getUserId() == userID)
                .map(Map.Entry::getValue).sorted(Collections.reverseOrder(Comparator.comparing(Meal::getDateTime))).
                        collect(Collectors.toList());
    }
}

