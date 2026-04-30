package Final;

import java.util.*;

public class PokemonSpecies {
	// Represents a Pokémon species stored in the Pokédex database.
    private String name;
    private ArrayList<String> types;
    private int baseSpeed;
    private ArrayList<Ability> abilities;
    private ArrayList<PokemonSpecies> altForms;
    private ArrayList<PokemonSpecies> megaForms;
    public PokemonSpecies(String name, ArrayList<String> types, int baseSpeed, ArrayList<Ability> abilities) {
        this.name = name;
        this.types = types;
        this.baseSpeed = baseSpeed;
        this.abilities = abilities;
        this.altForms = new ArrayList<>();
        this.megaForms = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public int getBaseSpeed() {
        return baseSpeed;
    }

    public ArrayList<Ability> getAbilities() {
        return abilities;
    }
    // Stores alternate forms such as Alolan, Galarian, Hisuian, or other special forms.
    public void addAltForm(PokemonSpecies altForm) {
        altForms.add(altForm);
    }

    public ArrayList<PokemonSpecies> getAltForms() {
        return altForms;
    }
 // Stores Mega Evolution forms separately so they can be toggled in the GUI.
    public void addMegaForm(PokemonSpecies megaForm) {
        megaForms.add(megaForm);
    }

    public ArrayList<PokemonSpecies> getMegaForms() {
        return megaForms;
    }

    public boolean hasMegaForms() {
        return !megaForms.isEmpty();
    }
}
