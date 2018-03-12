package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealServiceImpl;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {

        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
//            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);

//            MealRestController mealRestController = appCtx.getBean(MealRestController.class);
//
//            System.out.println(mealRestController);
//
//            MealServiceImpl mealService = appCtx.getBean(MealServiceImpl.class);
//
//            System.out.println(mealService.getRepository());

//
//            Meal meal1 = new Meal(LocalDateTime.of(2011, 1, 30,
//                    10, 0), "ТЕСТОВЫЙ ЗАВТРАК 1", 500);
//
//            Meal meal2 = new Meal(LocalDateTime.of(2014, 2, 25,
//                    10, 0), "ТЕСТОВЫЙ ЗАВТРАК 2", 500);
//
//            Meal meal3 = new Meal(LocalDateTime.of(2012, 3, 30,
//                    10, 0), "ТЕСТОВЫЙ ЗАВТРАК 3", 500);
//
//            Meal meal4 = new Meal(LocalDateTime.of(2016, 4, 20,
//                    10, 0), "ТЕСТОВЫЙ ЗАВТРАК 4", 500);
//
//            mealRestController.create(meal1, 2);
//            mealRestController.create(meal2, 2);
//            mealRestController.create(meal3, 2);
//            mealRestController.create(meal4, 2);
//
//            System.out.println(mealRestController.getAll(2, LocalDate.of(2000, 3, 30),
//                    LocalDate.of(2020, 4, 1)));
//
//            Meal meal5 = new Meal(LocalDateTime.of(2015, 3, 30,
//                    10, 0), "НОВЫЙ ТЕСТОВЫЙ ЗАВТРАК", 700);
//
//            mealRestController.update(meal5, 2, meal1.getId());
//
//            System.out.println(mealRestController.getAll(2, LocalDate.of(2015, 3, 29),
//                    LocalDate.of(2015, 4, 1)));
//
//            System.out.println(mealRestController.get(meal3.getId(), 2));


//            adminUserController.create(new User(null, "userName", "email", "password", Role.ROLE_ADMIN));
        }
    }
}
