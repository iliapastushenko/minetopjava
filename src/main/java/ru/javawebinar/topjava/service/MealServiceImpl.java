package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServiceImpl implements MealService {


    private MealRepository repository;

    public MealRepository getRepository() {
        return repository;
    }

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public Meal create(Meal meal, int userID) {
        return repository.save(meal, userID);
    }

    @Override
    public void update(Meal meal, int userID) {
        checkNotFoundWithId(repository.update(meal, userID), userID);
    }

    @Override
    public void delete(int id, int userID) throws NotFoundException {

        checkNotFoundWithId(repository.delete(id, userID), id);
    }

    @Override
    public Meal get(int id, int userID) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, userID), id);
    }

    @Override
    public List<Meal> getAllFilteredByDates(int userID, LocalDate startDate, LocalDate endDate) {
        return repository.getAllFilteredByDates(userID, startDate, endDate);
    }

    @Override
    public List<Meal> getAll(int userID) {
        return repository.getAll(userID);
    }
}