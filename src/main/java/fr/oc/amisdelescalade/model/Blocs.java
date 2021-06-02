package fr.oc.amisdelescalade.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "blocs")
public class Blocs {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String name;
}
