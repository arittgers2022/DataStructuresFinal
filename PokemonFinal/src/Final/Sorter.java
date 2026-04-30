package Final;

import java.util.*;

public class Sorter {

    public static void sortBySpeed(ArrayList<PokemonSpecies> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            int maxIndex = i;

            for (int j = i + 1; j < list.size(); j++) {

                int speedJ = SpeedCalculator.maxIVFastNature(list.get(j).getBaseSpeed());
                int speedMax = SpeedCalculator.maxIVFastNature(list.get(maxIndex).getBaseSpeed());

                if (speedJ > speedMax) {
                    maxIndex = j;
                }
            }

            PokemonSpecies temp = list.get(i);
            list.set(i, list.get(maxIndex));
            list.set(maxIndex, temp);
        }
    }
}