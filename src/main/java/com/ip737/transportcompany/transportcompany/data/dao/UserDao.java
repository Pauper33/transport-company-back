package com.ip737.transportcompany.transportcompany.data.dao;

import com.ip737.transportcompany.transportcompany.configs.constants.SqlConstants;
import com.ip737.transportcompany.transportcompany.data.entities.User;
import com.ip737.transportcompany.transportcompany.data.rowmappers.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.*;


@Repository
@Slf4j
public class UserDao {

    final private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User getByActivationUrl(String activationUrl) {
        try {
            return jdbcTemplate.queryForObject(SqlConstants.USERS_GET_BY_ACTIVATION_URL,
                    new Object[]{activationUrl},
                    new UserMapper());
        } catch (EmptyResultDataAccessException exception) {
            return null;
        }
    }

    public User getByRecoverUrl(String recoverUrl) {
        try {
            return jdbcTemplate.queryForObject(SqlConstants.USERS_GET_BY_RECOVERY_URL,
                    new Object[]{recoverUrl},
                    new UserMapper());
        } catch (EmptyResultDataAccessException exception) {
            return null;
        }
    }

    public User getById(UUID id) {
        try {
            return jdbcTemplate.queryForObject(SqlConstants.USER_GET_BY_ID,
                    new Object[]{id},
                    new UserMapper());
        } catch (EmptyResultDataAccessException exception) {
            return null;
        }
    }

    public User getByEmail(String email) {
        try {
            return jdbcTemplate.queryForObject(SqlConstants.USER_GET_BY_EMAIL,
                    new Object[]{email},
                    new UserMapper());
        } catch (EmptyResultDataAccessException exception) {
            return null;
        }
    }

    public void save(User user, int roleId) {
        jdbcTemplate.update(SqlConstants.USER_SAVE_QUERY,
                UUID.randomUUID(), user.getFullname(), user.getEmail(), roleId, user.getPassword(),
                user.isActivated(), user.getLink(), user.getRecoveryLink()
        );
    }

    public void update(User user) {
        jdbcTemplate.update(SqlConstants.USER_UPDATE_QUERY_BY_ID,
                user.getFullname(), user.getEmail(), user.getPassword(), user.isActivated(),
                user.getLink(), user.getRecoveryLink(), user.getId());

    }

    public List<Date> getListOfApprovedDays(String userId, List<Date> days) {
        var res = new ArrayList<Date>();
        System.out.println(userId);
        for(var date: days)
        {
            var ret = jdbcTemplate.queryForList(SqlConstants.IS_DATE_BUSY,
                    new Object[]{date, userId});
            System.out.println(ret);
            //b9c991b2-7a71-43a8-924e-6b3aad3bf3e3
        }
        return res;
    }

    public void delete(String email, String password) {
        jdbcTemplate.update(SqlConstants.DELETE_USER_QUERY,
                email, password);

    }
}
