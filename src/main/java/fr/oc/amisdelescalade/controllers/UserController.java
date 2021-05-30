package fr.oc.amisdelescalade.controllers;

import fr.oc.amisdelescalade.model.User;
import fr.oc.amisdelescalade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Create - Add a new user
     * @param user An object user
     * @return The user object saved
     */
    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    /**
     * Read - Get all users
     * @return - An Iterable object of Users full filled
     */
    @GetMapping("/users")
    public Iterable<User> getUsers() {
        return userService.getUsers();
    }


    @GetMapping("/users")
    public Optional<User> getUser(@PathVariable("email") final String email) {
        return userService.getUser(email);
    }

    /**
     * Read - Get one user
     * @param id The id of the user
     * @return An User object full filled
     */
    @GetMapping("/employee/{id}")
    public User getUser(@PathVariable("id") final Long id) {
        Optional<User> user = userService.getUser(id);
        if(user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }

    /**
     * Update - Update an existing user
     * @param id - The id of the user to update
     * @param user - The user object updated
     * @return
     */
    @PutMapping("/employee/{id}")
    public User updateEmployee(@PathVariable("id") final Long id, @RequestBody User user) {
        Optional<User> u = userService.getUser(id);
        if(u.isPresent()) {
            User currentUser = u.get();

            String fullName = user.getFullName();
            if(fullName != null) {
                currentUser.setFullName(fullName);
            }
            String userName = user.getUserName();
            if(userName != null) {
                currentUser.setUserName(userName);;
            }
            String mail = user.getEmail();
            if(mail != null) {
                currentUser.setEmail(mail);
            }
            String password = user.getPassword();
            if(password != null) {
                currentUser.setPassword(password);;
            }
            String passwordConfirm = user.getPasswordConfirm();
            if(passwordConfirm != null){
                currentUser.setPasswordConfirm(passwordConfirm);
            }
            String country = user.getCountry();
            if(country != null){
                currentUser.setCountry(country);
            }
            String creationAccount = user.getCreationAccount();
            if(creationAccount != null) {
                currentUser.setCreationAccount(creationAccount);
            }
            String officialMember = user.getOfficialMember();
            if(officialMember != null){
                currentUser.setOfficialMember(officialMember);
            }
            userService.saveUser(currentUser);
            return currentUser;

        } else {
            return null;
        }
    }

    /**
     * Delete - Delete an user
     * @param id - The id of the user to delete
     */
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable("id") final Long id) {
        userService.deleteUser(id);
    }


}
