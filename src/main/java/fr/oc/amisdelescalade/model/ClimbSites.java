package fr.oc.amisdelescalade.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

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
    @Size(max = 1200)
    private String description;
    private String difficulty;
    @Size(max = 1200)
    private String access;

    @Column(name = "path_map_acess")
    private String pathMapAcess;

    private long blocsId;




}
