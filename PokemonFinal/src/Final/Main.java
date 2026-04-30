package Final;

public class Main { 
    public static void main(String[] args) {
        TypeChart.initialize();

        Pokedex pokedex = new Pokedex();
        PokedexLoader.loadFromCSV("pokemon_champions_database.csv", pokedex);
        // Loads Pokémon, forms, speeds, and abilities from the CSV file into the Pokédex.
        new PokedexGUI(pokedex);
    }
}
