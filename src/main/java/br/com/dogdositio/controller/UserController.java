package br.com.dogdositio.controller;

import br.com.dogdositio.model.User;
import br.com.dogdositio.model.UserTypeEnum;
import br.com.dogdositio.service.BaseUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private BaseUserDAO userDAO;

    @PostMapping(value = {"/", ""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<User> save(@RequestBody User user) {
        if(user != null) {
            user.setCreationDate(new Date());
            if(user.getType() == null) {
                user.setType(UserTypeEnum.CUSTOMER);
            }
            User persistedUser = userDAO.save(user);
            if (persistedUser != null) {
                return new ResponseEntity(persistedUser, new HttpHeaders(), HttpStatus.CREATED);
            }
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = {"/", ""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<List<User>> findAll() {
        List<User> users = userDAO.findAll();
        return new ResponseEntity(users, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = {"/{id}/", "/{id}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<User> loadById(@PathVariable Long id) {
        User user = userDAO.loadById(id);
        if(user != null) {
            return new ResponseEntity(user, new HttpHeaders(), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = {"/{id}/", "/{id}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<User> delete(@PathVariable Long id) {
        User user = userDAO.loadById(id);
        if(user != null) {
            userDAO.delete(user);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
