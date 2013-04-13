package pl.edu.agh.to1.dice.logic;

import java.util.Arrays;
import java.util.List;

/**
 * @author Michal Partyka
 */
public enum Figure {
    ONES("1") {
        public Integer getScore(DiceBox diceBox) {
            return diceBox.sum(1);
        }
    }, TWOES("2") {
        public Integer getScore(DiceBox diceBox) {
            return diceBox.sum(2);
        }
    }, THREES("3") {
        public Integer getScore(DiceBox diceBox) {
            return diceBox.sum(3);
        }
    }, FOURS("4") {
        public Integer getScore(DiceBox diceBox) {
            return diceBox.sum(4);
        }
    }, FITHS("5") {
        public Integer getScore(DiceBox diceBox) {
            return diceBox.sum(5);
        }
    }, SIXES("6") {
        public Integer getScore(DiceBox diceBox) {
            return diceBox.sum(6);
        }
    }, THREE_EQUALS("3-eq") {
        public Integer getScore(DiceBox diceBox) {
            int[] count = diceBox.getMapCount();
            for (int i : count) {
                if(i > 2) {
                    return diceBox.sum();
                }
            }
            return 0;
        }
    }, FOUR_EQUALS("4-eq") {
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
    FULL("ful") {
        public Integer getScore(DiceBox diceBox) {
            int[] count = diceBox.getMapCount();
            for (int i : count) {
                if(i == 5 || i == 4 || i == 1) {
                    return 0;
                }
            }
            return 25;
        }
    }, SMALL_STRIT("ms") {
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
    }, BIG_STRIT("ds") {
        public Integer getScore(DiceBox diceBox) {
            int[] map1 = new int[] {0, 1, 1, 1, 1, 1, 0};
            int[] map2 = new int[] {0, 0, 1, 1, 1, 1, 1};
            int[] count = diceBox.getMapCount();

            if (matches(count, map1) || matches(count, map2)) {
                return 40;
            }
            return 0;
        }

    }, GENERAL("g") {
        public Integer getScore(DiceBox diceBox) {
            int[] mapCount = diceBox.getMapCount();
            for (int i : mapCount) {
                if (i != 0 && i != 5) {
                    return 0;
                }
            }
            return 50;
        }
    }, CHANCE("sz") {
        public Integer getScore(DiceBox diceBox) {
            return diceBox.sum();
        }
    };

    private static boolean matches(int[] count, int[] map1) {
        for (int i = 0; i < count.length; i++) {
            if ( count[i] < map1[i] ) {
                return false;
            }
        }
        return true;
    }

    public Integer getScore(DiceBox diceBox) {
        return 0;
    }

    Figure(String abr) {
    }

    public static List<Figure> countForBonus() {
        return Arrays.asList(ONES, TWOES, THREES, FOURS, FITHS, SIXES);
    }
}
