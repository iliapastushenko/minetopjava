package ru.javawebinar.topjava.service.Meal;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.AbstractMealServiceTest;

@ActiveProfiles({"common_jpa", "jpa"})
public class JpaMealServiceTest extends AbstractMealServiceTest {
}