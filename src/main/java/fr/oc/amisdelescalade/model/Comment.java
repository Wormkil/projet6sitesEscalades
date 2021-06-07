package fr.oc.amisdelescalade.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private long userId;
    private long csId; // = climbingSiteId
    @Size(max = 1200)
    private String content;
    private String creationDate;

}
