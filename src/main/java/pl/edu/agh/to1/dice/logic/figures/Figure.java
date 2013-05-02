package pl.edu.agh.to1.dice.logic.figures;

import pl.edu.agh.to1.dice.logic.dices.DiceBox;

import java.util.Arrays;
import java.util.List;

/**
 * Enum type for the figures in DiceGame
 * @author Michal Partyka
 */
public enum Figure implements IFigure {
    ONES {
        public Integer getScore(DiceBox diceBox) {
            return diceBox.sum(1);
        }
    }, TWOES {
        public Integer getScore(DiceBox diceBox) {
            return diceBox.sum(2);
        }
    }, THREES {
        public Integer getScore(DiceBox diceBox) {
            return diceBox.sum(3);
        }
    }, FOURS {
        public Integer getScore(DiceBox diceBox) {
            return diceBox.sum(4);
        }
    }, FITHS {
        public Integer getScore(DiceBox diceBox) {
            return diceBox.sum(5);
        }
    }, SIXES {
        public Integer getScore(DiceBox diceBox) {
            return diceBox.sum(6);
        }
    }, THREE_EQUALS {
        public Integer getScore(DiceBox diceBox) {
            int[] count = diceBox.getMapCount();
            for (int i : count) {
                if(i > 2) {
                    return diceBox.sum();
                }
            }
            return 0;
        }
    }, FOUR_EQUALS {
        public Integer getScore(DiceBox diceBox) {
            int[] count = diceBox.getMapCount();
            for (int i : count) {
                if(i > 3) {
                    return diceBox.sum();
                }
            }
            return 0;
        }
    },
    FULL {
        public Integer getScore(DiceBox diceBox) {
            int[] count = diceBox.getMapCount();
            for (int i : count) {
                if(i == 5 || i == 4 || i == 1) {
                    return 0;
                }
            }
            return 25;
        }
    }, SMALL_STRIT {
        public Integer getScore(DiceBox diceBox) {
            int[] map1 = new int[] {0, 1, 1, 1, 1, 0, 0};
            int[] map2 = new int[] {0, 0, 1, 1, 1, 1, 0};
            int[] map3 = new int[] {0, 0, 0, 1, 1, 1, 1};
            int[] count = diceBox.getMapCount();

            if (matches(count, map1) || matches(count, map2) || matches(count, map3) ||
                    Figure.BIG_STRIT.getScore(diceBox) == 40) {
                return 30;
            }
            return 0;
        }
    }, BIG_STRIT {
        public Integer getScore(DiceBox diceBox) {
            int[] map1 = new int[] {0, 1, 1, 1, 1, 1, 0};
            int[] map2 = new int[] {0, 0, 1, 1, 1, 1, 1};
            int[] count = diceBox.getMapCount();

            if (matches(count, map1) || matches(count, map2)) {
                return 40;
            }
            return 0;
        }

    }, GENERAL {
        public Integer getScore(DiceBox diceBox) {
            int[] mapCount = diceBox.getMapCount();
            for (int i : mapCount) {
                if (i != 0 && i != 5) {
                    return 0;
                }
            }
            return 50;
        }
    }, CHANCE {
        public Integer getScore(DiceBox diceBox) {
            return diceBox.sum();
        }
    }, EVEN {
        public Integer getScore(DiceBox diceBox) {
            return diceBox.sum(2) + diceBox.sum(4) + diceBox.sum(6);
        }
    }, ODD {
        public Integer getScore(DiceBox diceBox) {
            return diceBox.sum(1) + diceBox.sum(3) + diceBox.sum(5);
        }
    };

    /**
     * @param diceBox user diceBox
     * @return score of this figure with given diceBox
     */
    public Integer getScore(DiceBox diceBox) {
        return 0;
    }

    public String getSignature() {
        return toString();
    }

    private static boolean matches(int[] count, int[] map1) {
        for (int i = 0; i < count.length; i++) {
            if ( count[i] < map1[i] ) {
                return false;
            }
        }
        return true;
    }

    //TODO: this cant be a static list
    public static List<Figure> countForBonus() {
        return Arrays.asList(ONES, TWOES, THREES, FOURS, FITHS, SIXES);
    }
}
