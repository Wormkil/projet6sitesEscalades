package fr.oc.amisdelescalade.model;

public class climbingSites {

    private String name, sector, description, difficultyProfile, access;
    private String[][] tabBlocs;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSector() {
        return sector;
    }
    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDifficultyProfile() {
        return difficultyProfile;
    }
    public void setDifficultyProfile(String difficultyProfile) {
        this.difficultyProfile = difficultyProfile;
    }

    public String getAccess() {
        return access;
    }
    public void setAccess(String access) {
        this.access = access;
    }

    public String[][] getTabBlocs() {
        return tabBlocs;
    }
    public void setTabBlocs(String[][] tabBlocs) {
        this.tabBlocs = tabBlocs;
    }
}
