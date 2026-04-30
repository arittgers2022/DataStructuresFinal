package Final;

import java.util.*;

public class Pokemon {
	// Represents a selected Pokémon used for type effectiveness calculations.
    private String name;
    private ArrayList<String> types;
    private ArrayList<Ability> abilities;

    public Pokemon(String name, ArrayList<String> types, ArrayList<Ability> abilities) {
        this.name = name;
        this.types = types;
        this.abilities = abilities;
    }

    public String getName() { return name; }
    public ArrayList<String> getTypes() { return types; }
    public ArrayList<Ability> getAbilities() { return abilities; }
}