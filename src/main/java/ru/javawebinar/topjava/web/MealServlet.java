package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.Dao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;


import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

    private Dao dao = new Dao();

    {
        dao.init();
    }

    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        req.setCharacterEncoding("UTF-8");

        if (action.equalsIgnoreCase("delete")) {
            int mealId = Integer.parseInt(req.getParameter("mealId"));
            dao.deleteMeal(mealId);
            req.setAttribute("currentMeals", MealsUtil.getFilteredWithExceeded(dao.getAllMeal(), LocalTime.MIN, LocalTime.MAX, 2000));
            req.getRequestDispatcher("/meals.jsp").forward(req, resp);
        }

        if (action.equalsIgnoreCase("listmeals")) {
            req.setAttribute("currentMeals", MealsUtil.getFilteredWithExceeded(dao.getAllMeal(), LocalTime.MIN, LocalTime.MAX, 2000));
            req.getRequestDispatcher("/meals.jsp").forward(req, resp);
        }

        if (action.equalsIgnoreCase("edit")) {
            Meal meal = dao.getMeal(Integer.parseInt(req.getParameter("mealId")));
            req.setAttribute("meal", meal);
            req.getRequestDispatcher("/Meal.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        req.setCharacterEncoding("UTF-8");

        String mealid = req.getParameter("mealid");
        String description = req.getParameter("description");
        String calories = req.getParameter("calories");
        String dateTime = req.getParameter("dateTime");

        if (mealid == null || mealid.isEmpty()) {

            dao.addMeal(new Meal(LocalDateTime.of(
                    Integer.valueOf(dateTime.substring(0, 4)),
                    Integer.valueOf(dateTime.substring(5, 7)),
                    Integer.valueOf(dateTime.substring(8, 10)),
                    Integer.valueOf(dateTime.substring(11, 13)),
                    Integer.valueOf(dateTime.substring(14))),
                    description, Integer.valueOf(calories)));
            req.setCharacterEncoding("UTF-8");
            req.setAttribute("currentMeals", MealsUtil.getFilteredWithExceeded(dao.getAllMeal(), LocalTime.of(0, 0), LocalTime.of(23, 0), 2000));
            req.getRequestDispatcher("/meals.jsp").forward(req, resp);
        } else {
            //2015-03-30T10:00

            dao.updateMeal(Integer.valueOf(mealid), new Meal(LocalDateTime.of(
                    Integer.valueOf(dateTime.substring(0, 4)),
                    Integer.valueOf(dateTime.substring(5, 7)),
                    Integer.valueOf(dateTime.substring(8, 10)),
                    Integer.valueOf(dateTime.substring(11, 13)),
                    Integer.valueOf(dateTime.substring(14))),
                    description, Integer.valueOf(calories), Integer.valueOf(mealid)));
            req.setAttribute("currentMeals", MealsUtil.getFilteredWithExceeded(dao.getAllMeal(), LocalTime.of(0, 0), LocalTime.of(23, 0), 2000));
            req.getRequestDispatcher("/meals.jsp").forward(req, resp);

        }

    }
}