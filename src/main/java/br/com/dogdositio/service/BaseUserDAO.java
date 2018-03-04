package br.com.dogdositio.service;

import br.com.dogdositio.model.User;

import java.util.List;

public interface BaseUserDAO {

    User save(User user);

    User loadById(Long id);

    void delete(User user);

    List<User> findAll();

}
