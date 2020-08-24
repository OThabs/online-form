package com.user.interview;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@CrossOrigin(origins = "*")
@RestController
public class InterviewServiceController {

    private static final Map<String, User> personMap = new HashMap<>();
    static {
        User user = new User();
        user.setFullName("Josh Oth");
        user.setIdNumber("9111115891080");
        user.setId("1");
        personMap.put(user.getId(), user);
    }

    @RequestMapping(value = "/persons", method = RequestMethod.POST)
    public ResponseEntity<Object> createPerson(@RequestBody User user) {
        if(!isValid(user)) {
            return new ResponseEntity<>("Invalid Name", HttpStatus.BAD_REQUEST);
        } if(!isValidSAIDNumber(user.getIdNumber())){
            return new ResponseEntity<>("Invalid ID number", HttpStatus.BAD_REQUEST);
        }
        personMap.put(user.getId(), user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/persons/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updatePerson(@PathVariable("id") String id, @RequestBody User user){
        personMap.remove(id);
        personMap.put(id, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public ResponseEntity<Object> getPersons(){
        return new ResponseEntity<>(personMap.values(), HttpStatus.OK);
    }

    private boolean isValid(User user){
        return isValidName(user.getFullName());
    }

    private boolean isValidSAIDNumber(String idNumber) {
        String trimmedIdNumber = idNumber.replaceAll(" ", "");
        return !idNumber.isEmpty() && trimmedIdNumber.matches("[0-9]+")
                && trimmedIdNumber.length() == 13;
    }

    private boolean isValidName(String fullName){
        return !fullName.isEmpty() && fullName.replaceAll(" ", "").matches("[a-zA-Z]+");
    }
}

class User {
    private String idNumber;
    private String fullName;
    private String id;

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

