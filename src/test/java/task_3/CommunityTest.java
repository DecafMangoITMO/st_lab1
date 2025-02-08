package task_3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class CommunityTest {

    private Community humanCommunity;
    private Community dolphinCommunity;

    @BeforeEach
    public void setup() {
        humanCommunity = new Community(CreatureType.HUMAN);
        dolphinCommunity = new Community(CreatureType.DOLPHIN);
    }

    @Test
    public void checkStandardHumansAdd() {
        Creature human1 = new Creature(1, "1", CreatureType.HUMAN);
        Creature human2 = new Creature(2, "2", CreatureType.HUMAN);
        Creature human3 = new Creature(3, "3", CreatureType.HUMAN);

        humanCommunity.addMember(human1);
        humanCommunity.addMember(human2);
        humanCommunity.addMember(human3);

        List<Creature> humanCommunityMembers = humanCommunity.getMembers();

        assertEquals(3, humanCommunityMembers.size());
        assertEquals(human1, humanCommunityMembers.get(0));
        assertEquals(human2, humanCommunityMembers.get(1));
        assertEquals(human3, humanCommunityMembers.get(2));
    }

    @Test
    public void checkStandardDolphinsAdd() {
        Creature dolphin1 = new Creature(1, "1", CreatureType.DOLPHIN);
        Creature dolphin2 = new Creature(2, "2", CreatureType.DOLPHIN);
        Creature dolphin3 = new Creature(3, "3", CreatureType.DOLPHIN);

        dolphinCommunity.addMember(dolphin1);
        dolphinCommunity.addMember(dolphin2);
        dolphinCommunity.addMember(dolphin3);

        List<Creature> dolphinCommunityMembers = dolphinCommunity.getMembers();

        assertEquals(3, dolphinCommunityMembers.size());
        assertEquals(dolphin1, dolphinCommunityMembers.get(0));
        assertEquals(dolphin2, dolphinCommunityMembers.get(1));
        assertEquals(dolphin3, dolphinCommunityMembers.get(2));
    }

    @Test
    public void checkAddMemberNull() {
        assertThrows(NullPointerException.class, () -> humanCommunity.addMember(null));
    }

    @Test
    public void checkAddHumanToDolphins() {
        Creature human = new Creature(1, "1", CreatureType.HUMAN);

        assertThrows(IllegalArgumentException.class, () -> dolphinCommunity.addMember(human));
    }

    @Test
    public void checkAddDolphinToHuman() {
        Creature dolphin = new Creature(1, "1", CreatureType.DOLPHIN);

        assertThrows(IllegalArgumentException.class, () -> humanCommunity.addMember(dolphin));
    }

    @Test
    public void checkAddDuplicates() {
        Creature human = new Creature(1, "1", CreatureType.HUMAN);
        Creature copyHuman = new Creature(1, "1", CreatureType.HUMAN);

        humanCommunity.addMember(human);

        assertThrows(RuntimeException.class, () -> humanCommunity.addMember(copyHuman));
    }

    @Test
    public void checkStandardRemove() {
        Creature human = new Creature(1, "1", CreatureType.HUMAN);
        humanCommunity.addMember(human);

        assertDoesNotThrow(() -> humanCommunity.removeMember(human));
        assertEquals(0, humanCommunity.getMembers().size());
    }

    @Test
    public void checkRemoveNotFound() {
        Creature human = new Creature(1, "1", CreatureType.HUMAN);

        assertThrows(NoSuchElementException.class, () -> humanCommunity.removeMember(human));
    }

    @Test
    public void checkHumansReaction() {
        List<Discovery> discoveries = List.of(
                new Discovery("1", DiscoveryType.TECHNOLOGICAL),
                new Discovery("2", DiscoveryType.SOCIAL),
                new Discovery("3", DiscoveryType.ECOLOGICAL)
        );

        for (Discovery discovery : discoveries)
            assertEquals(ReactionType.NEUTRAL, humanCommunity.getReaction(discovery));
    }

    @Test
    public void checkDolphinsReaction() {
        Discovery negativeDiscovery = new Discovery("1", DiscoveryType.TECHNOLOGICAL);
        Discovery neutralDiscovery = new Discovery("2", DiscoveryType.SOCIAL);
        Discovery positiveDiscovery = new Discovery("3", DiscoveryType.ECOLOGICAL);

        assertEquals(ReactionType.NEGATIVE, dolphinCommunity.getReaction(negativeDiscovery));
        assertEquals(ReactionType.NEUTRAL, dolphinCommunity.getReaction(neutralDiscovery));
        assertEquals(ReactionType.POSITIVE, dolphinCommunity.getReaction(positiveDiscovery));
    }

}
