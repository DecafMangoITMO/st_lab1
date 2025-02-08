package task_3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;


public class WorldTest {

    private World world;

    @BeforeEach
    public void setup() {
        world = new World();
    }

    @Test
    public void checkStandardAddHuman() {
        String humanName = "1";

        assertDoesNotThrow(() -> world.addHuman(humanName));
    }

    @Test
    public void checkAddHumanNullEmptyBlankName() {
        String nullName = null;
        String emptyString = "";
        String blankName = " ";

        assertThrows(IllegalArgumentException.class, () -> world.addHuman(nullName));
        assertThrows(IllegalArgumentException.class, () -> world.addHuman(emptyString));
        assertThrows(IllegalArgumentException.class, () -> world.addHuman(blankName));
    }

    @Test
    public void checkStandardRemoveHuman() {
        Creature human = new Creature(1, "1", CreatureType.HUMAN);

        assertDoesNotThrow(() -> world.addHuman(human.getName()));
        assertDoesNotThrow(() -> world.removeHuman(human));
    }

    @Test
    public void checkRemoveHumanNotFound() {
        Creature human = new Creature(1, "1", CreatureType.HUMAN);

        assertThrows(NoSuchElementException.class, () -> world.removeHuman(human));
    }

    @Test
    public void checkStandardAddDolphin() {
        String dolphinName = "1";

        assertDoesNotThrow(() -> world.addDolphin(dolphinName));
    }

    @Test
    public void checkAddDolphinNullEmptyBlankName() {
        String nullName = null;
        String emptyString = "";
        String blankName = " ";

        assertThrows(IllegalArgumentException.class, () -> world.addDolphin(nullName));
        assertThrows(IllegalArgumentException.class, () -> world.addDolphin(emptyString));
        assertThrows(IllegalArgumentException.class, () -> world.addDolphin(blankName));
    }

    @Test
    public void checkStandardRemoveDolphin() {
        Creature dolphin = new Creature(1, "1", CreatureType.DOLPHIN);

        assertDoesNotThrow(() -> world.addDolphin(dolphin.getName()));
        assertDoesNotThrow(() -> world.removeDolphin(dolphin));
    }

    @Test
    public void checkRemoveDolphinNotFound() {
        Creature dolphin = new Creature(1, "1", CreatureType.DOLPHIN);

        assertThrows(NoSuchElementException.class, () -> world.removeDolphin(dolphin));
    }

    @ParameterizedTest
    @EnumSource(DiscoveryType.class)
    public void checkStandardCreateNewDiscovery(DiscoveryType type) {
        String name = "1";

        assertDoesNotThrow(() -> world.createNewDiscovery(name, type));

        List<Discovery> discoveries = world.getDiscoveries();
        assertEquals(1, discoveries.size());
        assertEquals(name, discoveries.get(0).getName());
        assertEquals(type, discoveries.get(0).getType());
    }

    @Test
    public void checkDuplicates() {
        String name = "1";
        DiscoveryType type = DiscoveryType.TECHNOLOGICAL;

        assertDoesNotThrow(() -> world.createNewDiscovery(name, type));
        assertThrows(RuntimeException.class, () -> world.createNewDiscovery(name, type));
    }

    @Test
    public void checkStandardDolphinsReaction() {
        String negativeName = "negative";
        String neutralName = "neutral";
        String positiveName = "positive";

        world.createNewDiscovery(negativeName, DiscoveryType.TECHNOLOGICAL);
        world.createNewDiscovery(neutralName, DiscoveryType.SOCIAL);
        world.createNewDiscovery(positiveName, DiscoveryType.ECOLOGICAL);

        String expected = "Dolphins reaction to discovery \"negative\" is NEGATIVE\n" +
                "Dolphins reaction to discovery \"neutral\" is NEUTRAL\n" +
                "Dolphins reaction to discovery \"positive\" is POSITIVE\n";
        String actual = world.getDolphinsReaction();

        assertEquals(expected, actual);
    }

    @Test
    public void checkDolphinsReactionToNothing() {
        String expected = "Nothing to react";
        String actual = world.getDolphinsReaction();

        assertEquals(expected, actual);
    }

    @Test
    public void checkStandardHarmonyScore() {
        String negativeName = "negative";
        String neutralName = "neutral";
        String positiveName = "positive";

        world.createNewDiscovery(negativeName, DiscoveryType.TECHNOLOGICAL);
        world.createNewDiscovery(neutralName, DiscoveryType.SOCIAL);
        world.createNewDiscovery(positiveName, DiscoveryType.ECOLOGICAL);

        assertEquals(1d/3d * 100, world.getHarmonyScore());
    }

    @Test
    public void checkHarmonyScoreToNothing() {
        assertEquals(0, world.getHarmonyScore());
    }

}
