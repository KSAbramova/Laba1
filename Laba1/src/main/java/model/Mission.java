package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)   // игнорирует лишние поля
public class Mission {

    public enum Outcome {
        SUCCESS,
        FAILURE
    }

    private String missionId;
    private LocalDate date;
    private String location;
    private Outcome outcome;
    private long damageCost;
    private String note;

    private final List<Sorcerer> sorcerers = new ArrayList<>();
    private final List<Technique> techniques = new ArrayList<>();
    private Curse curse;

    public Mission() {
    }

    public String getMissionId() { 
        return missionId;
    }
    
    public void setMissionId(String missionId) { 
        this.missionId = missionId; 
    }

    public LocalDate getDate() { 
        return date; 
    }
    
    public void setDate(LocalDate date) { 
        this.date = date; 
    }

    public String getLocation() { 
        return location; 
    }
    
    public void setLocation(String location) { 
        this.location = location; 
    }

    public Outcome getOutcome() { 
        return outcome; 
    }
    
    public void setOutcome(Outcome outcome) { 
        this.outcome = outcome; 
    }

    public long getDamageCost() { 
        return damageCost; 
    }
    
    public void setDamageCost(long damageCost) { 
        this.damageCost = damageCost; 
    }
    
    @JsonProperty("comment")
    public String getNote() { 
        return note; 
    }
    
    public void setNote(String note) { 
        this.note = note; 
    }

    public Curse getCurse() { 
        return curse; 
    }
    
    public void setCurse(Curse curse) { 
        this.curse = curse; 
    }

    public void addSorcerer(Sorcerer sorcerer) {
        this.sorcerers.add(sorcerer);
    }

    public void addTechnique(Technique technique) {
        this.techniques.add(technique);
    }

    public List<Sorcerer> getSorcerers() {
        return new ArrayList<>(sorcerers);
    }
    
    public void setSorcerers(List<Sorcerer> sorcerers) {
        this.sorcerers.clear();
        if (sorcerers != null) this.sorcerers.addAll(sorcerers);
    }

    public void setTechniques(List<Technique> techniques) {
        this.techniques.clear();
        if (techniques != null) this.techniques.addAll(techniques);
    }

    public List<Technique> getTechniques() {
        return new ArrayList<>(techniques);
    }
    
    public int getSorcerersSize() {
        return this.sorcerers.size();
    }
    
    public int geTechniqueSize() {
        return this.techniques.size();
    }
}