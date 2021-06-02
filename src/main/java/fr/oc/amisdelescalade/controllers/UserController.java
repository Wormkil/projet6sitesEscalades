package fr.oc.amisdelescalade.controllers;

import fr.oc.amisdelescalade.Projet6Application;
import fr.oc.amisdelescalade.model.User;
import fr.oc.amisdelescalade.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger log = LoggerFactory.getLogger(Projet6Application.class);
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

    /**
     * Read - Get one user
     * @param email The email of the user
     * @return An User object full filled
     */
    @GetMapping("/userbyEmail")
    public Optional<User> getUserByEmail(@PathVariable("email") final String email) {
        return userService.getUserByEmail(email);

    }
    /**
     * Read - Get one user
     * @param user_name The user_name of the user
     * @return An User object full filled
     */
    /*@GetMapping("/userbyUser_name")
    public Optional<User> getUserByUser_name(@PathVariable("user_name") final String user_name) {
        return userService.getUserByUser_name(user_name);

    }*/

    /**
     * Read - Get one user
     * @param id The id of the user
     * @return An User object full filled
     */
    @GetMapping("/employee/{id}")
    public User getUserById(@PathVariable("id") final Long id) {
        Optional<User> user = userService.getUserById(id);
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
    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable("id") final Long id, @RequestBody User user) {
        Optional<User> u = userService.getUserById(id);
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
            log.info(currentUser.toString());
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

   /*@RequestMapping("/initUsersBD")
    public Iterable<User> initUsersBD() {
        User user = new User();

        user.setId(1);
        user.setOfficialMember("false");
        user.setCountry("France");
        user.setUserName("Aze");
        user.setCreationAccount("2021-05-29");
        user.setPasswordConfirm("123456789");
        user.setPassword("123456789");
        user.setEmail("aze@aze.com");
        user.setFullName("A ze");
        log.info(user.toString());
        userService.saveUser(user);

        user.setId(2);
        user.setOfficialMember("false");
        user.setCountry("France");
        user.setUserName("Qsd");
        user.setCreationAccount("2021-05-29");
        user.setPasswordConfirm("123456789");
        user.setPassword("123456789");
        user.setEmail("qsd@qsd.com");
        user.setFullName("Q sd");
        log.info(user.toString());
        userService.saveUser(user);

        user.setId(3);
        user.setOfficialMember("false");
        user.setCountry("France");
        user.setUserName("Wxc");
        user.setCreationAccount("2021-05-29");
        user.setPasswordConfirm("123456789");
        user.setPassword("123456789");
        user.setEmail("wxc@wxc.com");
        user.setFullName("W xc");
        log.info(user.toString());
        userService.saveUser(user);

        return userService.getUsers();
    }*/


}
