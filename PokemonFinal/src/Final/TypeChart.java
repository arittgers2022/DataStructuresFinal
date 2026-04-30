package Final;

import java.util.*;

public class TypeChart {

    // attack → defense → Effect
    public static HashMap<String, HashMap<String, Effect>> chart = new HashMap<>();

    public static void initialize() { //POKEMON TYPE CHART
    	 HashMap<String, Effect> normal = new HashMap<>();
    	 normal.put("Rock", Effect.RESIST);
    	 normal.put("Steel", Effect.RESIST);
    	 normal.put("Ghost", Effect.IMMUNE);
    	 chart.put("Normal", normal);
    	 
        HashMap<String, Effect> fire = new HashMap<>();
        fire.put("Grass", Effect.WEAK);
        fire.put("Ice", Effect.WEAK);
        fire.put("Bug", Effect.WEAK);
        fire.put("Steel", Effect.WEAK);
        fire.put("Water", Effect.RESIST);
        fire.put("Fire", Effect.RESIST);
        fire.put("Rock", Effect.RESIST);
        fire.put("Dragon", Effect.RESIST);
        chart.put("Fire", fire);

        HashMap<String, Effect> water = new HashMap<>();
        water.put("Fire", Effect.WEAK);
        water.put("Ground", Effect.WEAK);
        water.put("Rock", Effect.WEAK);
        water.put("Water", Effect.RESIST);
        water.put("Grass", Effect.RESIST);
        water.put("Dragon", Effect.RESIST);
        chart.put("Water", water);
        
        HashMap<String, Effect> grass = new HashMap<>();
        grass.put("Water", Effect.WEAK);
        grass.put("Ground", Effect.WEAK);
        grass.put("Rock", Effect.WEAK);
        grass.put("Fire", Effect.RESIST);
        grass.put("Grass", Effect.RESIST);
        grass.put("Poison", Effect.RESIST);
        grass.put("Flying", Effect.RESIST);
        grass.put("Bug", Effect.RESIST);
        grass.put("Dragon", Effect.RESIST);
        grass.put("Steeel", Effect.RESIST);
        chart.put("Grass", grass);
        
        HashMap<String, Effect> electric = new HashMap<>();
        electric.put("Water", Effect.WEAK);
        electric.put("Flying", Effect.WEAK);
        electric.put("Electric", Effect.RESIST);
        electric.put("Grass", Effect.RESIST);
        electric.put("Dragon", Effect.RESIST);
        electric.put("Ground", Effect.IMMUNE);
        chart.put("Electric", electric);
        
        HashMap<String, Effect> ice = new HashMap<>();
        ice.put("Grass", Effect.WEAK);
        ice.put("Ground", Effect.WEAK);
        ice.put("Flying", Effect.WEAK);
        ice.put("Dragon", Effect.WEAK);
        ice.put("Fire", Effect.RESIST);
        ice.put("Water", Effect.RESIST);
        ice.put("Ice", Effect.RESIST);
        ice.put("Steel", Effect.RESIST);
        chart.put("Ice", ice);
        
        HashMap<String, Effect> fighting = new HashMap<>();
        fighting.put("Normal", Effect.WEAK);
        fighting.put("Ice", Effect.WEAK);
        fighting.put("Rock", Effect.WEAK);
        fighting.put("Dark", Effect.WEAK);
        fighting.put("Steel", Effect.WEAK);
        fighting.put("Poison", Effect.RESIST);
        fighting.put("Flying", Effect.RESIST);
        fighting.put("Psychic", Effect.RESIST);
        fighting.put("Bug", Effect.RESIST);
        fighting.put("Fairy", Effect.RESIST);
        fighting.put("Ghost", Effect.IMMUNE);
        chart.put("Fighting", fighting);
        
        HashMap<String, Effect> poison = new HashMap<>();
        poison.put("Grass", Effect.WEAK);
        poison.put("Fairy", Effect.WEAK);
        poison.put("Poison", Effect.RESIST);
        poison.put("Ground", Effect.RESIST);
        poison.put("Rock", Effect.RESIST);
        poison.put("Ghost", Effect.RESIST);
        poison.put("Steel", Effect.IMMUNE);
        chart.put("Poison", poison);
        
        HashMap<String, Effect> ground = new HashMap<>();
        ground.put("Fire", Effect.WEAK);
        ground.put("Electric", Effect.WEAK);
        ground.put("Poison", Effect.WEAK);
        ground.put("Rock", Effect.WEAK);
        ground.put("Steel", Effect.WEAK);
        ground.put("Grass", Effect.RESIST);
        ground.put("Bug", Effect.RESIST);
        ground.put("Flying", Effect.IMMUNE);
        chart.put("Ground", ground);
        
        HashMap<String, Effect> flying = new HashMap<>();
        flying.put("Grass", Effect.WEAK);
        flying.put("Fighting", Effect.WEAK);
        flying.put("Bug", Effect.WEAK);
        flying.put("Electric", Effect.RESIST);
        flying.put("Rock", Effect.RESIST);
        flying.put("Steel", Effect.RESIST);
        chart.put("Flying", flying);
        
        HashMap<String, Effect> psychic = new HashMap<>();
        psychic.put("Fighting", Effect.WEAK);
        psychic.put("Poison", Effect.WEAK);
        psychic.put("Psychic", Effect.RESIST);
        psychic.put("Steel", Effect.RESIST);
        psychic.put("Dark", Effect.IMMUNE);
        chart.put("Psychic", psychic);
        
        HashMap<String, Effect> bug = new HashMap<>();
        bug.put("Grass", Effect.WEAK);
        bug.put("Psychic", Effect.WEAK);
        bug.put("Dark", Effect.WEAK);
        bug.put("Fire", Effect.RESIST);
        bug.put("Fighting", Effect.RESIST);
        bug.put("Poison", Effect.RESIST);
        bug.put("Flying", Effect.RESIST);
        bug.put("Ghost", Effect.RESIST);
        bug.put("Steel", Effect.RESIST);
        bug.put("Fairy", Effect.RESIST);
        chart.put("Bug", bug);
        
        HashMap<String, Effect> rock = new HashMap<>();
        rock.put("Fire", Effect.WEAK);
        rock.put("Ice", Effect.WEAK);
        rock.put("Flying", Effect.WEAK);
        rock.put("Bug", Effect.WEAK);
        rock.put("Fighting", Effect.RESIST);
        rock.put("Ground", Effect.RESIST);
        rock.put("Steel", Effect.RESIST);
        chart.put("Rock", rock);
        
        HashMap<String, Effect> ghost = new HashMap<>();
        ghost.put("Psychic", Effect.WEAK);
        ghost.put("Ghost", Effect.WEAK);
        ghost.put("Dark", Effect.RESIST);
        ghost.put("Normal", Effect.IMMUNE);
        chart.put("Ghost", ghost);
        
        HashMap<String, Effect> dragon = new HashMap<>();
        dragon.put("Dragon", Effect.WEAK);
        dragon.put("Steel", Effect.RESIST);
        dragon.put("Fairy", Effect.IMMUNE);
        chart.put("Dragon", dragon);
        
        HashMap<String, Effect> dark = new HashMap<>();
        dark.put("Psychic", Effect.WEAK);
        dark.put("Ghost", Effect.WEAK);
        dark.put("Fighting", Effect.RESIST);
        dark.put("Dark", Effect.RESIST);
        dark.put("Fairy", Effect.RESIST);
        chart.put("Dark", dark);
        
        HashMap<String, Effect> steel = new HashMap<>();
        steel.put("Ice", Effect.WEAK);
        steel.put("Rock", Effect.WEAK);
        steel.put("Fairy", Effect.WEAK);
        steel.put("Fire", Effect.RESIST);
        steel.put("Water", Effect.RESIST);
        steel.put("Electric", Effect.RESIST);
        steel.put("Steel", Effect.RESIST);
        chart.put("Steel", steel);
        
        HashMap<String, Effect> fairy = new HashMap<>();
        fairy.put("Fighting", Effect.WEAK);
        fairy.put("Dragon", Effect.WEAK);
        fairy.put("Dark", Effect.WEAK);
        fairy.put("Fire", Effect.RESIST);
        fairy.put("Poison", Effect.RESIST);
        fairy.put("Steel", Effect.RESIST);
        chart.put("Fairy", fairy);
        
        
    }

    public static HashMap<String, Double> calculateEffectiveness(Pokemon p) {
    	// Calculates defensive effectiveness by checking how each attack type affects the Pokémon's types.
        HashMap<String, Integer> exponentMap = new HashMap<>();

        // Step 1: initialize all attack types to 0
        for (String attackType : chart.keySet()) {

            for (String defendType : p.getTypes()) {

                // if already immune, stop because immune overrides everything
                if (exponentMap.get(attackType) != null &&
                    exponentMap.get(attackType) == -100) {
                    break;
                }

                Effect effect = chart.get(attackType).get(defendType);

                if (effect != null) {

                    if (effect == Effect.IMMUNE) {
                        exponentMap.put(attackType, -100);
                        break; // STOP HERE
                    } else {
                        exponentMap.put(
                            attackType,
                            exponentMap.getOrDefault(attackType, 0) + effect.getValue()
                        );
                    }
                }
            }
        }

        // Step 3: apply abilities
        for (Ability ability : p.getAbilities()) {

            for (String immune : ability.getImmunities()) {
                exponentMap.put(immune, -100);
            }

            for (String type : ability.getResistances()) {
                if (exponentMap.get(type) != -100) {
                    exponentMap.put(type, exponentMap.get(type) - 1);
                }
            }
        }

        // Step 4: convert to real multipliers
        HashMap<String, Double> result = new HashMap<>();

        for (String type : exponentMap.keySet()) {
            int exp = exponentMap.get(type);

            if (exp == -100) {
                result.put(type, 0.0);
            } else {
                result.put(type, Math.pow(2, exp));
            }
        }

        return result;
    }
    public static ArrayList<String> getWeaknesses(HashMap<String, Double> map) {
        ArrayList<String> list = new ArrayList<>();
        for (String type : map.keySet()) {
            if (map.get(type) > 1.0) {
                list.add(type + " x" + map.get(type));
            }
        }
        return list;
    }

    public static ArrayList<String> getResistances(HashMap<String, Double> map) {
        ArrayList<String> list = new ArrayList<>();
        for (String type : map.keySet()) {
            if (map.get(type) > 0.0 && map.get(type) < 1.0) {
                list.add(type + " x" + map.get(type));
            }
        }
        return list;
    }

    public static ArrayList<String> getImmunities(HashMap<String, Double> map) {
        ArrayList<String> list = new ArrayList<>();
        for (String type : map.keySet()) {
            if (map.get(type) == 0.0) {
                list.add(type);
            }
        }
        return list;
    }
    public enum Effect { //Take care of magic numbers
        WEAK(1),
        RESIST(-1),
        IMMUNE(-100), // special case
        NEUTRAL(0);

        private int value;

        Effect(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}