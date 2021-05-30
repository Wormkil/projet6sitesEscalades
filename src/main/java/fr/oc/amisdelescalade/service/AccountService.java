package fr.oc.amisdelescalade.service;

import fr.oc.amisdelescalade.Projet6Application;
import fr.oc.amisdelescalade.model.User;
import fr.oc.amisdelescalade.repository.user.UserRepository;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class AccountService {

    private static final Logger log = LoggerFactory.getLogger(Projet6Application.class);

    @Autowired
    private UserRepository userRepository;

    public User connectUser(String email, String password) {
        var user = userRepository.findByEmail(email);
        //User u = new User();
        if (user.isPresent()) {

            log.info(user.get().toString());

            return user.get();
        }
        return user.get();
    }
}
