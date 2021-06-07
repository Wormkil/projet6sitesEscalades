package fr.oc.amisdelescalade.service;

import fr.oc.amisdelescalade.model.Comment;
import fr.oc.amisdelescalade.model.User;
import fr.oc.amisdelescalade.repository.CommentRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class CommentService {
    @Autowired
    private CommentRepository comRepo;
    @Autowired
    private UserService userService;

    public Optional<Comment> getComById(final Long id) {
        return comRepo.findById(id);
    }
    public Iterable<Comment> getComs() {
        return comRepo.findAll();
    }
    public void deleteCom(final Long id) {
        comRepo.deleteById(id);
    }
    public Comment saveCom(Comment com) {
        Comment savedCom = comRepo.save(com);
        return savedCom;
    }
    public List<Comment> findCommentByCsId(Long id){
        return comRepo.findCommentByCsId(id);
    }
    public List<User> getUserOfComment(Iterable<Comment> coms) {
        List<User> users = new ArrayList();
        for(Comment com : coms){
            users.add(userService.getUserById(com.getUserId()).get());
        }
        return users;
    }

}
