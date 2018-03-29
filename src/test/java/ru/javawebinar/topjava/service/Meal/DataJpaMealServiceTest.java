package ru.javawebinar.topjava.service.Meal;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.AbstractMealServiceTest;

@ActiveProfiles({"common_jpa", "datajpa"})
public class DataJpaMealServiceTest extends AbstractMealServiceTest {
}