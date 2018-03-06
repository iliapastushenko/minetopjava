package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class dao implements daoint {

    private Map<Integer, Meal> storage = new ConcurrentHashMap<>();

    private AtomicInteger count = new AtomicInteger(0);

    private static dao instance;

    public static dao getInstance() {
        if (instance == null) {
            instance = new dao();

            for (Meal meal : MealsUtil.firstinit())

            {

                instance.addMeal(meal);
            }
        }
        return instance;
    }

    @Override
    public void addMeal(Meal meal) {
        if (storage.containsKey(meal.getId())) {
            storage.put(meal.getId(), meal);
        } else {
            meal.setId(generateId());
            storage.put(meal.getId(), meal);
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
        List<Meal> meal = new ArrayList<>(storage.values());
        return meal;
    }

    @Override
    public int generateId() {
        return count.incrementAndGet();
    }
}