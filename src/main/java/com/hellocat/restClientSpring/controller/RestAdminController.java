package com.hellocat.restClientSpring.controller;

import com.hellocat.restClientSpring.dto.UserDto;
import com.hellocat.restClientSpring.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/*")
public class RestAdminController {

    @Autowired
    private SiteService siteService;

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.POST)
    public ResponseEntity<List<UserDto>> getUserList() {
        return new ResponseEntity<>(siteService.findAllUsers(), HttpStatus.OK);
    }

    @RequestMapping("/getUser/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(siteService.findUserById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody UserDto user) {
        boolean rezult = siteService.saveUser(user);
        if (!rezult) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ResponseEntity<Void> updateUser(@RequestBody UserDto user) {
        try {
            siteService.updateUser(user);
        } catch (Exception e) {
            //bad girl too
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        siteService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
