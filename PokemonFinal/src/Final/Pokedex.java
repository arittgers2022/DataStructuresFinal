package Final;

import java.util.*;

public class Pokedex {
	// Uses a HashMap to store Pokémon by name for fast lookup.
    private HashMap<String, PokemonSpecies> map = new HashMap<>();
    
    public void add(PokemonSpecies p) {
        map.put(p.getName().toLowerCase(), p);
    }

    public PokemonSpecies get(String name) {
        if (name == null) {
            return null;
        }
        return map.get(name.toLowerCase());
    }
 // Returns all Pokémon names so they can be displayed in GUI dropdown menus.
    public ArrayList<String> getAllNames() {
        ArrayList<String> list = new ArrayList<>();
        for (PokemonSpecies p : map.values()) {
            list.add(p.getName());
        }
        Collections.sort(list);
        return list;
    }
    
    
}
