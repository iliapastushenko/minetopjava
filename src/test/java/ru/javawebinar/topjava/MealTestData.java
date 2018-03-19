package ru.javawebinar.topjava;


import ru.javawebinar.topjava.model.Meal;


import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;


public class MealTestData {

    public static final int USER_1_MEAL_1_ID = START_SEQ + 3;
    public static final int USER_1_MEAL_2_ID = START_SEQ + 4;
    public static final int USER_1_MEAL_3_ID = START_SEQ + 5;
    public static final int USER_1_MEAL_4_ID = START_SEQ + 6;
    public static final int USER_1_MEAL_5_ID = START_SEQ + 7;
    public static final int USER_1_MEAL_6_ID = START_SEQ + 8;

    public static final int USER_2_MEAL_1_ID = START_SEQ + 9;
    public static final int USER_2_MEAL_2_ID = START_SEQ + 10;
    public static final int USER_2_MEAL_3_ID = START_SEQ + 11;
    public static final int USER_2_MEAL_4_ID = START_SEQ + 12;
    public static final int USER_2_MEAL_5_ID = START_SEQ + 13;
    public static final int USER_2_MEAL_6_ID = START_SEQ + 14;

    public static final Meal USER_1_MEAL_1 = new Meal(USER_1_MEAL_1_ID, LocalDateTime.of(2015,
            5, 30, 10, 0), "Завтрак юзера 1", 500);
    public static final Meal USER_1_MEAL_2 = new Meal(USER_1_MEAL_2_ID, LocalDateTime.of(2015,
            5, 30, 13, 0), "Обед юзера 1", 1000);
    public static final Meal USER_1_MEAL_3 = new Meal(USER_1_MEAL_3_ID, LocalDateTime.of(2015,
            5, 30, 20, 0), "Ужин юзера 1", 500);
    public static final Meal USER_1_MEAL_4 = new Meal(USER_1_MEAL_4_ID, LocalDateTime.of(2018,
            5, 31, 10, 0), "Завтрак юзера 1", 500);
    public static final Meal USER_1_MEAL_5 = new Meal(USER_1_MEAL_5_ID, LocalDateTime.of(2018,
            5, 31, 13, 0), "Обед юзера 1", 1000);
    public static final Meal USER_1_MEAL_6 = new Meal(USER_1_MEAL_6_ID, LocalDateTime.of(2018,
            5, 31, 20, 0), "Ужин юзера 1", 501);


    public static final Meal USER_2_MEAL_1 = new Meal(USER_2_MEAL_1_ID, LocalDateTime.of(2015,
            5, 30, 10, 0), "Завтрак юзера 2", 500);
    public static final Meal USER_2_MEAL_2 = new Meal(USER_2_MEAL_2_ID, LocalDateTime.of(2015,
            5, 30, 13, 0), "Обед юзера 2", 1000);
    public static final Meal USER_2_MEAL_3 = new Meal(USER_2_MEAL_3_ID, LocalDateTime.of(2015,
            5, 30, 20, 0), "Ужин юзера 2", 500);
    public static final Meal USER_2_MEAL_4 = new Meal(USER_2_MEAL_4_ID, LocalDateTime.of(2018,
            5, 31, 10, 0), "Завтрак юзера 2", 500);
    public static final Meal USER_2_MEAL_5 = new Meal(USER_2_MEAL_5_ID, LocalDateTime.of(2018,
            5, 31, 13, 0), "Обед юзера 2", 1000);
    public static final Meal USER_2_MEAL_6 = new Meal(USER_2_MEAL_6_ID, LocalDateTime.of(2018,
            5, 31, 20, 0), "Ужин юзера 2", 501);


    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }
}