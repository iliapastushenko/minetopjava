package ru.javawebinar.topjava.repository.jdbc.Meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import javax.sql.DataSource;


@Repository
@Profile("hsqldb")
public class HsqldbJdbcMealRepositoryImpl extends AbstractJdbcMealRepositoryImpl {
    @Autowired
    public HsqldbJdbcMealRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(dataSource, jdbcTemplate, namedParameterJdbcTemplate);
    }
}