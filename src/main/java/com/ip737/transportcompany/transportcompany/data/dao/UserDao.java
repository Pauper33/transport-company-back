package com.ip737.transportcompany.transportcompany.data.dao;

import com.ip737.transportcompany.transportcompany.data.entities.User;

public interface UserDao {

    User getByEmail(String email);

    User save(User user, int roleId);

    void update(User user);

    User getByActivationUrl(String activationUrl);

    User getByRecoverUrl(String recoverUrl);
}
