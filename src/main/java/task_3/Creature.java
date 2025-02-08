package task_3;

import java.util.Objects;

public class Creature {

    private final long id;
    private final String name;
    private final CreatureType creatureType;

    public Creature(long id, String name, CreatureType creatureType) {
        this.id = id;
        this.name = name;
        this.creatureType = creatureType;
    }

    public String getName() {
        return name;
    }

    public CreatureType getCreatureType() {
        return creatureType;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Creature creature = (Creature) object;
        return id == creature.id && Objects.equals(name, creature.name) && creatureType == creature.creatureType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, creatureType);
    }

    @Override
    public String toString() {
        return String.format("Creature [id=" + id + ", name=" + name + ", creatureType=" + creatureType + "]");
    }
}
