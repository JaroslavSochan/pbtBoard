# pbtBoard
Showcase of property based testing of some board game engine.

## Motivation

After removing dust from our [Agricola](https://en.wikipedia.org/wiki/Agricola_(board_game)) after few years of "no time to play" time,
I was curious, how it would be possible to correctly generate deck of round cards.
There is rule, that first four cards should be only subset of whole deck, next three cards have to be another subset, e.t.c..
I have modeled this in `RoundActionCardProperties` class in its `initialMixIsCorrect` method.

Then I became curious, how such an engine for some [Eurogame](https://en.wikipedia.org/wiki/Eurogame) type would be implemented.
First approach came to me using `enum` for each action, for each card, e.t.c.

This was a beginning of this project.
