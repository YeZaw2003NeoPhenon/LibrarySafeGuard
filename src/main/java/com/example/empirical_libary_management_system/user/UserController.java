package com.example.empirical_libary_management_system.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final userServiceImp userServiceImp;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<apiResponse<List<UserRecord>>> getAllUsers() {
        List<UserRecord> users = userServiceImp.getAllUsers();
        apiResponse<List<UserRecord>> response = new apiResponse<>(HttpStatus.OK, "Users retrieved successfully", users);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<apiResponse<User>> add(@Valid @RequestBody User user) {
        User newUser = userServiceImp.add(user);
        apiResponse<User> response = new apiResponse<>(HttpStatus.CREATED, "User added successfully", newUser);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{email}", method = RequestMethod.GET)
    public ResponseEntity<apiResponse<User>> getByEmail(@PathVariable("email") String email) {
        User user = userServiceImp.getUser(email);
        apiResponse<User> response = new apiResponse<>(HttpStatus.OK, "User retrieved successfully", user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/{email}", method = RequestMethod.DELETE)
    public ResponseEntity<apiResponse<Void>> delete(@PathVariable("email") String email) {
        userServiceImp.delete(email);
        apiResponse<Void> response = new apiResponse<>(HttpStatus.NO_CONTENT, "User deleted successfully", null);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<apiResponse<User>> update(@Valid @RequestBody User user, @PathVariable("id") Long id) {
        User updatedUser = userServiceImp.update(user, id);
        apiResponse<User> response = new apiResponse<>(HttpStatus.OK, "User updated successfully", updatedUser);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
