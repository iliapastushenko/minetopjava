package ru.javawebinar.topjava.Comparators;

import ru.javawebinar.topjava.model.Meal;

import java.util.Comparator;

public class MealComparator implements Comparator<Meal> {
    @Override
    public int compare(Meal m1, Meal m2) {

        if (m1 == null && m2 != null) return -1;
        if (m1 != null && m2 == null) return -1;
        if (m1 == null) return 0;

        if (m1.getClass().equals(m2.getClass())
                && m1.getId().equals(m2.getId())
                && m1.getDateTime().equals(m2.getDateTime())
                && m1.getCalories() == m2.getCalories()
                && m1.getDescription().equals(m2.getDescription())) return 0;
        else return -1;
    }
}
