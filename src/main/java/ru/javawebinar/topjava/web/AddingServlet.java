package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.dao;
import ru.javawebinar.topjava.model.Meal;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;


public class AddingServlet extends HttpServlet {

    private dao storage = dao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        LocalDateTime localDateTime = LocalDateTime.parse((String) req.getAttribute("datetime"));
        String description = (String) req.getAttribute("description");
        int calories = (int) req.getAttribute("calories");
        int id = (int) req.getAttribute("id");
        storage.addMeal(new Meal(localDateTime, description, calories, id));
        resp.sendRedirect("users.jsp");

    }

}
