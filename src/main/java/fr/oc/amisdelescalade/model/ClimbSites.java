package fr.oc.amisdelescalade.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "climbsites")
public class ClimbSites {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String name;

    private String sector;
    private String description;
    private String difficulty;
    private String access;

    private long blocs_id;




}
