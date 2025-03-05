package com.mateo.springcloud.msvc.users.msvc_users.Web.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mateo.springcloud.msvc.users.msvc_users.Domain.Models.UserDomain;
import com.mateo.springcloud.msvc.users.msvc_users.Domain.Service.UserService;

@RestController
@RequestMapping("/users")
@RefreshScope
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<UserDomain> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("/getByUserName/{userName}")
    public ResponseEntity<UserDomain> getByUserName(@PathVariable("userName") String userName) {
        return ResponseEntity.ok(userService.getByUserName(userName));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDomain>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @PostMapping("/save")
    public ResponseEntity<UserDomain> save(@RequestBody UserDomain user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @PutMapping("/update")
    public ResponseEntity<UserDomain> update(@RequestBody UserDomain user) {
        return ResponseEntity.ok(userService.update(user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
