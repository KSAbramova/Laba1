package model;

public class Technique {

    public enum Type {
        INNATE,
        SHIKIGAMI
    }

    private String name;
    private Type type;
    private String owner;
    private long damage;

    public Technique() {
    }
    
    public Technique(String name, Type type, String owner, long damage) {
        this.name = name;
        this.type = type;
        this.owner = owner;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public long getDamage() {
        return damage;
    }

    public void setDamage(long damage) {
        this.damage = damage;
    }
}
