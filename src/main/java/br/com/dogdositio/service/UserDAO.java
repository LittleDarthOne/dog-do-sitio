package br.com.dogdositio.service;

import br.com.dogdositio.model.User;
import br.com.dogdositio.service.repository.UserRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO implements BaseUserDAO {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User loadById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public List<User> findAll() {
        Iterable<User> users = userRepository.findAll();
        if(users != null) {
            return Lists.newArrayList(users);
        }
        return new ArrayList<>();
    }
}
