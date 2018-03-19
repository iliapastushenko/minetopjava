package ru.javawebinar.topjava.web;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.mock.InMemoryUserRepositoryImpl;
import ru.javawebinar.topjava.util.exception.NotFoundException;


import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.UserTestData.ADMIN;

@ContextConfiguration({"/spring-test.xml",})
@RunWith(SpringRunner.class)
public class InMemoryAdminRestControllerSpringTest {

    @Autowired
    private InMemoryUserRepositoryImpl repository;

    @Before
    public void setUp() throws Exception {
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