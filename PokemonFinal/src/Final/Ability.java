package Final;

import java.util.*;

public class Ability {
	// Represents an ability that may modify a Pokémon's defensive matchups.
    private String name;
    private ArrayList<String> immunities = new ArrayList<>();
    private ArrayList<String> resistances = new ArrayList<>();

    public Ability(String name) {
        this.name = name;
    }
 // Some abilities add immunities, such as Levitate making the Pokémon immune to Ground-type moves.
    public void addImmunity(String type) {
        immunities.add(type);
    }
 // Some abilities add resistances, such as Thick Fat reducing Fire- and Ice-type damage.
    public void addResistance(String type) {
        resistances.add(type);
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getImmunities() {
        return immunities;
    }

    public ArrayList<String> getResistances() {
        return resistances;
    }
}