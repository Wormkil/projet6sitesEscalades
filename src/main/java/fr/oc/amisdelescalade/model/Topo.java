package fr.oc.amisdelescalade.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "topos")
public class Topo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    //@Column(unique = true)
    private String name; // Le nom du topo
    @Size(max = 1200)
    private String description; //Une description du topo
    private String localisation; //Le lieu du topo
    private String releaseDate; //La date de parution du topo
    private String authorId; //L'id de l'auteur du topo
    private String available; //Le statut disponible ou non du topo
    private String beingReserved; //Le statut être réservée ou non du topo
    private String reserved; //Le statut réservée ou non du topo
    private String ownerId; //Le statut réservée ou non du topo

}
