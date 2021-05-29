package fr.oc.amisdelescalade.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Topo {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
    LocalDate localDate = LocalDate.now();
    private String name, description, localisation, author;
    private String releaseDate = dtf.format(localDate);
    private Boolean available = false;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocalisation() {
        return localisation;
    }
    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public Boolean getAvailable() {
        return available;
    }
    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Topo{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", localisation='" + localisation + '\'' +
                ", author='" + author + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", available=" + available +
                '}';
    }
}
