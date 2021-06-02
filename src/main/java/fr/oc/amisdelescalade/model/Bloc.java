package fr.oc.amisdelescalade.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bloc")
public class Bloc {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String number;
    private String quotes;
    private String notes;

    private long blocs_id;
}

