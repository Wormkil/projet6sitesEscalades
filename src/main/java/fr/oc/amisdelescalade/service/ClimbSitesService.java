package fr.oc.amisdelescalade.service;

import fr.oc.amisdelescalade.Projet6Application;
import fr.oc.amisdelescalade.model.ClimbSites;
import fr.oc.amisdelescalade.repository.CSRepository;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class ClimbSitesService {

    private static final Logger log = LoggerFactory.getLogger(Projet6Application.class);
    private static final List<String> csDifficulties = List.of("1a", "7d"); //TODO middle

    @Autowired
    private CSRepository cSRepository;

    public Optional<ClimbSites> getCSById(final Long id) {
        return cSRepository.findById(id);
    }
    public Iterable<ClimbSites> getCSs() {
        return cSRepository.findAll();
    }
    public void deleteCS(final Long id) {
        cSRepository.deleteById(id);
    }
    public ClimbSites saveCS(ClimbSites cs) {
        ClimbSites savedCS = cSRepository.save(cs);
        return savedCS;
    }

    public Boolean compareCs(ClimbSites csInBase, ClimbSites csSearched){
        if (csSearched.getName().equals("") && csSearched.getRegion().equals("") &&
            csSearched.getCountry().equals("") && csSearched.getRegion().equals("") &&
            csSearched.getCotationsRange().equals("") && csSearched.getNbRoute().equals("") &&
            csSearched.getMaxHeight().equals("") && csSearched.getOfficial().equals("all") &&
            csSearched.getProfile() == null && csSearched.getStoneType() == null &&
            csSearched.getEquipment() == null && csSearched.getOrientations() == null &&
            csSearched.getBestSeason() == null && csSearched.getPlugType() == null
        ) return true;

        boolean isSearched = false;
        if(!csSearched.getName().equals("")) if (csInBase.getName().contains(csSearched.getName())) isSearched = true; else return false;
        if(!csSearched.getRegion().equals("")) if (csInBase.getRegion().contains(csSearched.getRegion())) isSearched = true; else return false;
        if(!csSearched.getCountry().equals("")) if (csInBase.getCountry().contains(csSearched.getCountry())) isSearched = true; else return false;

        if(csSearched.getOfficial().equals("all")) isSearched = true; else if(csInBase.getOfficial().equals(csSearched.getOfficial())) isSearched = true; else return false;

        if(csSearched.getProfile() != null) if (csInBase.getProfile().contains(csSearched.getProfile())) isSearched = true; else return false;
        if(csSearched.getStoneType() != null) if (csInBase.getStoneType().contains(csSearched.getStoneType())) isSearched = true; else return false;
        if(csSearched.getEquipment() != null) if (csInBase.getEquipment().contains(csSearched.getEquipment())) isSearched = true; else return false;
        if(csSearched.getEquipment() != null) if (csInBase.getEquipment().contains(csSearched.getEquipment())) isSearched = true; else return false;
        if(csSearched.getOrientations() != null) if (csInBase.getOrientations().contains(csSearched.getOrientations())) isSearched = true; else return false;
        if(csSearched.getBestSeason() != null) if (csInBase.getBestSeason().contains(csSearched.getBestSeason())) isSearched = true; else return false;

        if (!csSearched.getNbRoute().equals("")) if (csInBase.getNbRoute().equals(csSearched.getNbRoute())) isSearched = true; else return false;
        if (!csSearched.getMaxHeight().equals(""))
            if (Integer.parseInt(csInBase.getMaxHeight().substring(0, csInBase.getMaxHeight().length() - 1)) <= Integer.parseInt(csSearched.getMaxHeight()))
                isSearched = true;
            else return false;

        if(csSearched.getCotationsRange() != null) if (isInCotationRange(csSearched.getCotationsRange(), csInBase.getCotationsRange())) isSearched = true; else return false;

        return isSearched;
    }

    public boolean isInCotationRange(String cotation, String cotationRange) {
        final var startDifficulty = cotationRange.substring(3, cotationRange.length() - 6);
        final var endDifficulty = cotationRange.substring(9);
        final var indexOfStart = csDifficulties.indexOf(startDifficulty);
        final var indexOfEnd = csDifficulties.indexOf(endDifficulty);

        final var difficultyRange = csDifficulties.subList(indexOfStart, indexOfEnd);
        return difficultyRange.contains(cotation);

        //return createCotationRange(cotationRange.substring(3, cotationRange.length() - 6), cotationRange.substring(9, cotationRange.length())).contains(cotation);
    }

    public String createCotationRange(String cotaMin, String cotaMax){
        var string = "";
        var starter = Character.getNumericValue(cotaMin.charAt(0));
        int starterChar = cotaMin.charAt(1);
        var ender = Character.getNumericValue(cotaMax.charAt(0));
        var enderChar = cotaMax.charAt(1);
        for (int i = starter; i < ender+1; i++) {
            for(int j = starterChar; j < 100; j++) {
                if (i == ender && j > enderChar) {
                    j = 100;
                }
                else
                    string = string + i + (char)j + " ";
            }
            starterChar = 97;
        }
        return string;
    }

}
