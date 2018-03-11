package ru.javawebinar.topjava.repository.mock;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryMealRepositoryImpl implements MealRepository {

    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();

    private AtomicInteger counter = new AtomicInteger(0);

//    {
//        MealsUtil.MEALS.forEach(this::save);
//    }



    @Override
    public Meal get(int mealID, int userID) {

        return repository.entrySet().stream().filter(e -> e.getValue().getUserId() == userID)
                .filter(e -> e.getKey() == mealID).map(Map.Entry::getValue).findFirst().get();
    }


    @Override
    public Meal save(Meal meal, int userID) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(userID);
            repository.put(meal.getId(), meal);
            return meal;
        }
        // treat case: update, but absent in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public void delete(int mealID, int userID) {

        repository.entrySet().removeIf(entry -> entry.getKey() == mealID && entry.getValue().getUserId() == userID);

    }


    @Override
    public Collection<Meal> getAll(int userID, LocalDate startDate, LocalDate endDate) {
        return repository.entrySet().
                stream().filter(e -> e.getValue().getUserId() == userID)
                .filter(e -> DateTimeUtil.DateisBetween(e.getValue().getDate(), startDate, endDate))
                .sorted().
                        map(Map.Entry::getValue).
                        collect(Collectors.toList());
    }
}

