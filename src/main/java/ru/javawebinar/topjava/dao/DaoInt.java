package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;
import java.util.List;

public interface DaoInt {


    Meal addMeal(Meal m);


    void updateMeal(int id, Meal m);


    Meal getMeal(int id);


    void deleteMeal(int id);


    List<Meal> getAllMeal();


}
