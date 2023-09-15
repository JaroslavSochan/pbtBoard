package sk.luvar.pbtboard.enumapproach;

import java.util.Arrays;
import java.util.List;

/**
 * Represent one of card, which is on beginning of each round.
 */
public enum RoundActionCard {
    /**
     * Single fence for single wood.
     */
    FENCE(1),
    SMALL_OR_LARGE_EQUIPMENT(1),
    SHEEP(1),
    SOW_OR_BAKE(1),
    RENOVATION_AND_SMALL_OR_LARGE_EQUIPMENT(2),
    OFFSPRING_AND_SMALL_EQUIPMENT(2),
    ROCK(2),
    VEGETABLE(3),
    WILD_BOAR(3),
    ROCK2(4),
    COW(4),
    PLOUGH_AND_OR_OR_SOW(5),
    OFFSPRING_WITHOUT_FREE_ROOM(5),
    RENOVATION_AND_FENCE(6);

    /**
     * Stage (etapa). There are 6 stages:
     * <ol>
     *     <li>Rounds from 1 till 4,</li>
     *     <li>rounds from 5 till 7,</li>
     *     <li>rounds from 8 till 9,</li>
     *     <li>rounds from 10 till 11,</li>
     *     <li>rounds from 12 till 13,</li>
     *     <li>round 14, final round.</li>
     * </ol>
     */
    private final int stage;

    public static final List<RoundActionCard> STAGE1;
    public static final List<RoundActionCard> STAGE2;
    public static final List<RoundActionCard> STAGE3;
    public static final List<RoundActionCard> STAGE4;
    public static final List<RoundActionCard> STAGE5;
    public static final List<RoundActionCard> STAGE6;

    static {
        STAGE1 = Arrays.stream(RoundActionCard.values()).filter(c -> c.stage == 1).toList();
        STAGE2 = Arrays.stream(RoundActionCard.values()).filter(c -> c.stage == 2).toList();
        STAGE3 = Arrays.stream(RoundActionCard.values()).filter(c -> c.stage == 3).toList();
        STAGE4 = Arrays.stream(RoundActionCard.values()).filter(c -> c.stage == 4).toList();
        STAGE5 = Arrays.stream(RoundActionCard.values()).filter(c -> c.stage == 5).toList();
        STAGE6 = Arrays.stream(RoundActionCard.values()).filter(c -> c.stage == 6).toList();
    }
    RoundActionCard(int stage) {
        this.stage = stage;
    }

    public int getStage() {
        return stage;
    }
}
