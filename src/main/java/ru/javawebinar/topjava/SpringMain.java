package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);

            MealRestController  mealRestController = appCtx.getBean(MealRestController.class);

            Meal meal1 = new Meal(LocalDateTime.of(2015, 3, 30,
                    10, 0), "ТЕСТОВЫЙ ЗАВТРАК", 500);

            mealRestController.create(meal1,2);

            System.out.println("Meal1 id is " +meal1.getId());

            System.out.println(mealRestController.getAll(2, LocalDate.of(2015,3,30),
                    LocalDate.of(2015,4,1) ));

            Meal meal2 = new Meal(LocalDateTime.of(2015, 3, 30,
                    10, 0), "НОВЫЙ ТЕСТОВЫЙ ЗАВТРАК", 700);

            meal2.setId(7);

            mealRestController.update(meal2,2, meal1.getId());

            System.out.println(mealRestController.getAll(2, LocalDate.of(2015,3,30),
                    LocalDate.of(2015,4,1) ));

            adminUserController.create(new User(null, "userName", "email", "password", Role.ROLE_ADMIN));
        }
    }
}
