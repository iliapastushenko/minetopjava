package ru.javawebinar.topjava.web;

import org.junit.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.mock.InMemoryUserRepositoryImpl;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.UserTestData.ADMIN;

public class InMemoryAdminRestControllerTest {

    private static ConfigurableApplicationContext appCtx;
    private static InMemoryUserRepositoryImpl repository;

    @BeforeClass
    public static void beforeClass() {
        appCtx = new ClassPathXmlApplicationContext("spring-test.xml");
        repository = appCtx.getBean(InMemoryUserRepositoryImpl.class);
    }

    @AfterClass
    public static void afterClass() {
        appCtx.close();
    }

    @Before
    public void setUp() throws Exception {
        // re-initialize
        InMemoryUserRepositoryImpl repository = appCtx.getBean(InMemoryUserRepositoryImpl.class);
        repository.init();
    }

    @Test
    public void testDelete() throws Exception {
        repository.delete(UserTestData.USER_1_ID);
        Collection<User> users = repository.getAll();
        Assert.assertEquals(users.size(), 1);
        assertThat(users.iterator().next()).isEqualToIgnoringGivenFields(ADMIN, "registered", "roles");
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        if (!repository.delete(10)) throw new NotFoundException("Not found");
    }
}