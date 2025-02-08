package task_3;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class World {
    private long idCounter = 1;

    private final Community humanCommunity;
    private final Community dolphinCommunity;
    private final List<Discovery> discoveries;

    public World() {
        this.humanCommunity = new Community(CreatureType.HUMAN);
        this.dolphinCommunity = new Community(CreatureType.DOLPHIN);
        this.discoveries = new ArrayList<>();
    }

    public void addHuman(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("name cannot be null or empty");
        Creature human = new Creature(idCounter++, name, CreatureType.HUMAN);

        humanCommunity.addMember(human);
    }

    public void removeHuman(Creature human) {
        humanCommunity.removeMember(human);
    }

    public void addDolphin(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("name cannot be null or empty");
        Creature dolphin = new Creature(idCounter++, name, CreatureType.DOLPHIN);

        dolphinCommunity.addMember(dolphin);
    }

    public void removeDolphin(Creature dolphin) {
        dolphinCommunity.removeMember(dolphin);
    }

    public void createNewDiscovery(String name, DiscoveryType type) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("name cannot be null or empty");

        if (type == null)
            throw new IllegalArgumentException("type cannot be null");

        Discovery discovery = new Discovery(name, type);

        for (Discovery existingDiscovery : discoveries) {
            if (existingDiscovery.equals(discovery))
                throw new RuntimeException("Discovery already exists");
        }

        discoveries.add(discovery);
    }

    public String getDolphinsReaction() {
        if (discoveries.isEmpty())
            return "Nothing to react";

        StringBuilder sb = new StringBuilder();
        for (Discovery discovery : discoveries)
            sb.append("Dolphins reaction to discovery \"").append(discovery.getName()).append("\" is ").append(dolphinCommunity.getReaction(discovery)).append("\n");

        return sb.toString();
    }

    public double getHarmonyScore() {
        int totalReactions = 0;
        int positiveReactions = 0;

        if (discoveries.isEmpty())
            return 0d;

        for (Discovery discovery : discoveries) {
            totalReactions++;

            if (dolphinCommunity.getReaction(discovery) == ReactionType.POSITIVE)
                positiveReactions++;
        }

        return (double) positiveReactions / (double) totalReactions * 100;
    }

    public List<Discovery> getDiscoveries() {
        return discoveries;
    }
}
