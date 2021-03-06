package com.myHome.myrest.controller;

import com.myHome.myrest.model.Board;
import com.myHome.myrest.model.User;
import com.myHome.myrest.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserApiController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    List<User> all(@RequestParam(required = false) String method, @RequestParam(required = false) String text) {
        List<User> users = null;
        if("query".equals(method))
            return users = userRepository.findByUsernameQuery(text);
        else if("nativeQuery".equals(method))
            return users = userRepository.findByUsernameNativeQuery(text);
        else if("querydsl".equals(method)) {
//            QUser user = QUser.user;
//            Predicate predicate = user.username.contains(text);
//            userRepository.findAll(predicate);
        } else if("querydslCustom".equals(method)) {
            return userRepository.findByCustomUsername(text);
        } else if("jdbcQuery".equals(method)) {
            return userRepository.findByUsernameJdbc(text);
        }

        return users = userRepository.findAll();

//        log.debug("getBoards().size 호출 전");
//        log.debug("getBoards().size 호출: {}",users.get(0).getBoards().size());
//        log.debug("getBoards().size 호출 후");
    }

    @PostMapping("/users")
    User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    // Single item
    @GetMapping("/users/{id}")
    User one(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @PutMapping("/users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        System.out.println("PUT users mapped id: "+id);
        return userRepository.findById(id)
                .map(user -> {
//                    user.setTitle(newUser.getTitle());
//                    user.setContent(newUser.getContent());
                    user.getBoards().clear();
                    user.getBoards().addAll(newUser.getBoards());
                    for(Board board : user.getBoards()) {
                        System.out.println("for board: "+board.getId()+", title: "+board.getTitle());
                        board.setUser(user);
                    }
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return userRepository.save(newUser);
                });
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
