package sk.luvar.pbtboard.enumapproach;


import net.jqwik.api.*;
import net.jqwik.api.statistics.Statistics;
import org.assertj.core.api.Assertions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class RoundActionCardProperties {
    @Property
    void initialMixIsCorrect(@ForAll("roundDeck") List<RoundActionCard> roundActionDeck) {
        Statistics.label("FirstRoundAction").collect(roundActionDeck.get(0));
        Assertions.assertThat(roundActionDeck)
                .hasSize(14);
        Assertions.assertThat(new HashSet<>(roundActionDeck))
                .as("Random deck have all cards unique in its list.")
                .hasSize(14);
        Assertions.assertThat(roundActionDeck.subList(0, 4))
                .as("All four rounds should contain cards for first stage only.")
                .containsExactlyInAnyOrder(RoundActionCard.FENCE, RoundActionCard.SMALL_OR_LARGE_EQUIPMENT, RoundActionCard.SHEEP, RoundActionCard.SOW_OR_BAKE);
        Assertions.assertThat(roundActionDeck.subList(4, 7))
                .extracting(RoundActionCard::getStage)
                .as("All three rounds in second stage, should contain cards for second stage only.")
                .containsOnly(2);
        Assertions.assertThat(roundActionDeck.subList(7, 9))
                .filteredOn("stage", 3)
                .as("All two rounds in third stage, should contain cards for third stage only.")
                .hasSize(2);
        Assertions.assertThat(roundActionDeck.subList(9, 11))
                .filteredOn("stage", 4)
                .as("All two rounds in fourth stage, should contain cards for fourth stage only.")
                .hasSize(2);
        Assertions.assertThat(roundActionDeck.subList(11, 13))
                .filteredOn("stage", 5)
                .as("All two rounds in fifth stage, should contain cards for fifth stage only.")
                .hasSize(2);
        Assertions.assertThat(roundActionDeck)
                .last()
                .as("Last action have to be exactly RENOVATION_AND_FENCE!")
                .isSameAs(RoundActionCard.RENOVATION_AND_FENCE);

    }

    /**
     * Arbitrary with mix of action cards deck. There is a rule, that on first four positions have to be only four
     * cards shuffled. Next three positions have to contain three specific cards shuffled, e.t.c..
     *
     * @return  returns arbitrary for action card deck
     */
    @Provide
    public Arbitrary<List<RoundActionCard>> roundDeck() {
        final Arbitrary<List<RoundActionCard>> stage1 = Arbitraries.shuffle(RoundActionCard.STAGE1);
        final Arbitrary<List<RoundActionCard>> stage2 = Arbitraries.shuffle(RoundActionCard.STAGE2);
        final Arbitrary<List<RoundActionCard>> stage3 = Arbitraries.shuffle(RoundActionCard.STAGE3);
        final Arbitrary<List<RoundActionCard>> stage4 = Arbitraries.shuffle(RoundActionCard.STAGE4);
        final Arbitrary<List<RoundActionCard>> stage5 = Arbitraries.shuffle(RoundActionCard.STAGE5);
        final Arbitrary<List<RoundActionCard>> stage6 = Arbitraries.shuffle(RoundActionCard.STAGE6);
        return Combinators.combine(stage1, stage2, stage3, stage4, stage5, stage6).as((s1, s2, s3, s4, s5, s6) -> {
            final ArrayList<RoundActionCard> deck = new ArrayList<>();
            deck.addAll(s1);
            deck.addAll(s2);
            deck.addAll(s3);
            deck.addAll(s4);
            deck.addAll(s5);
            deck.addAll(s6);
            return deck;
        });
    }
}
