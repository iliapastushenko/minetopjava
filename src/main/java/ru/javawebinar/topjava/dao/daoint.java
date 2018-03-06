package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;
import java.util.List;

public interface daoint {


    public void addMeal(Meal m);


    public void updateMeal(int id, Meal m);


    public Meal getMeal(int id);


    public void deleteMeal(int id);


    public List<Meal> getAllMeal();


        public  int generateId();
}
