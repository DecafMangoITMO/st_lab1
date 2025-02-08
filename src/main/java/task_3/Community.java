package task_3;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Community {

    private final CreatureType membersType;
    private final List<Creature> members;

    public Community(CreatureType memberType) {
        this.membersType = memberType;
        this.members = new ArrayList<>();
    }

    public void addMember(Creature creature) {
        if (creature == null)
            throw new NullPointerException("Creature cannot be null");

        if (creature.getCreatureType() == CreatureType.HUMAN && membersType == CreatureType.DOLPHIN)
            throw new IllegalArgumentException("Humans cannot live with dolphins");

        if (creature.getCreatureType() == CreatureType.DOLPHIN && membersType == CreatureType.HUMAN)
            throw new IllegalArgumentException("Dolphins cannot live with humans");

        for (Creature member : members) {
            if (member.equals(creature))
                throw new RuntimeException("Visitor already exists");
        }

        members.add(creature);
        System.out.printf("Added creature: %s\n", creature);
    }

    public void removeMember(Creature creature) {
        Creature foundMember = null;
        for (Creature member : members) {
            if (member.equals(creature)) {
                foundMember = member;
                break;
            }
        }
        if (foundMember == null)
            throw new NoSuchElementException("No visitor " + creature + " found");

        members.remove(foundMember);
        System.out.printf("Removed member: %s\n", creature);
    }

    public ReactionType getReaction(Discovery discovery) {
        if (membersType == CreatureType.HUMAN)
            return ReactionType.NEUTRAL;

        return switch (discovery.getType()) {
            case TECHNOLOGICAL -> ReactionType.NEGATIVE;
            case SOCIAL -> ReactionType.NEUTRAL;
            case ECOLOGICAL -> ReactionType.POSITIVE;
        };
    }

    public List<Creature> getMembers() {
        return members;
    }
}
