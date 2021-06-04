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
    private String name; // Le nom du sites d'escalades
    @Size(max = 1200)
    private String description; //Une description du sites d'escalades
    @Size(max = 1200)
    private String access; //Une description sur le moyen d'acceder au sites d'escalades
    private String region; //La région du site d'escalade
    private String bestSeason; // Les meilleurs saisons pour découvrir le sites
    private String country; //Le pays du site d'escalade
    private String orientations; // N/S/E/W
    private String cotationsRange; // Par exemple : de a1 à c5
    private String nbRoute; // Le nombre de voie/lignes d'escalades du site
    private String equipment; // Type d'équipement présent
    private String maxHeight; // La hauteur maximal des voies
    private String stoneType; // Le type de roche des voies
    private String profile; //Les profiles de l'escalade Ex : dévers, vertical, dalle
    private String plugType; //Ex : trous, réglettes, aplats
    @Size(max = 1200)
    private String infoSup; // N'importe quelle information suplémentaire ou temporaire

    private String pathImages; // Le chemin d'accès vers les photos du sites
    private long blocsId; //L'id du blocs qui contiendra tous les bloc de sites d'escalades

    @Column(name = "path_map_acess")
    private String pathMapAcess; // A supprimé <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
}
