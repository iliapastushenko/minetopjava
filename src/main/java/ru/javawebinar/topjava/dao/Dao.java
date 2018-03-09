package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Dao implements daoint {

    private Map<Integer, Meal> storage = new ConcurrentHashMap<>();

    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public Meal addMeal(Meal meal) {
        if (storage.containsKey(meal.getId())) {
            storage.put(meal.getId(), meal);
        } else {
            meal.setId(generateId());
            storage.put(meal.getId(), meal);
        }

        return meal;
    }

    public void init() {

        for (Meal meal : MealsUtil.firstinit())

        {

            addMeal(meal);
        }


    }

    @Override
    public void updateMeal(int id, Meal m) {
        storage.put(id, m);
    }

    @Override
    public Meal getMeal(int id) {
        return storage.get(id);
    }

    @Override
    public void deleteMeal(int id) {
        storage.remove(id);
    }

    @Override
    public List<Meal> getAllMeal() {
        return new ArrayList<>(storage.values());
    }


    private int generateId() {
        return count.incrementAndGet();
    }
}