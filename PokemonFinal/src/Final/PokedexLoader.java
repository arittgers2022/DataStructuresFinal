package Final;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PokedexLoader {

    public static void loadFromCSV(String fileName, Pokedex pokedex) {
        ArrayList<String> rows = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(fileName));

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (!line.trim().isEmpty()) {
                    rows.add(line);
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("CSV file not found: " + fileName);
            return;
        }

        // First pass: load all base Pokémon.
        for (String line : rows) {
            String[] data = line.split(",", -1);

            if (data.length < 9) {
                System.out.println("Skipping bad CSV row, not enough columns: " + line);
                continue;
            }

            String formType = clean(data[2]);

            if (formType.equalsIgnoreCase("Base")) {
                PokemonSpecies pokemon = createPokemonFromRow(data, line);

                if (pokemon != null) {
                    pokedex.add(pokemon);
                }
            }
        }

        // Second pass: attach alternate forms and Mega forms.
        for (String line : rows) {
            String[] data = line.split(",", -1);

            if (data.length < 9) {
                continue;
            }

            String baseName = clean(data[0]);
            String formType = clean(data[2]);

            if (formType.equalsIgnoreCase("Alt") || formType.equalsIgnoreCase("Mega")) {
                PokemonSpecies formPokemon = createPokemonFromRow(data, line);

                if (formPokemon == null) {
                    continue;
                }

                PokemonSpecies basePokemon = pokedex.get(baseName);

                // If no base exists, make the first form act as the selectable base.
                if (basePokemon == null) {
                    PokemonSpecies newBase = new PokemonSpecies(
                            baseName,
                            formPokemon.getTypes(),
                            formPokemon.getBaseSpeed(),
                            formPokemon.getAbilities()
                    );

                    pokedex.add(newBase);
                    basePokemon = newBase;
                }

                if (formType.equalsIgnoreCase("Alt")) {
                    basePokemon.addAltForm(formPokemon);
                } else {
                    basePokemon.addMegaForm(formPokemon);
                }
            }
        }
    }

    private static PokemonSpecies createPokemonFromRow(String[] data, String line) {
        String baseName = clean(data[0]);
        String name = clean(data[1]);
        String formType = clean(data[2]);
        String type1 = clean(data[3]);
        String type2 = clean(data[4]);
        String baseSpeedText = clean(data[5]);
        String abilitiesText = clean(data[6]);

        if (baseName.isEmpty()) {
            System.out.println("Skipping bad CSV row, missing base Pokemon name: " + line);
            return null;
        }

        if (name.isEmpty()) {
            System.out.println("Skipping bad CSV row, missing Pokemon name: " + line);
            return null;
        }

        if (formType.isEmpty()) {
            System.out.println("Skipping bad CSV row, missing form type: " + line);
            return null;
        }

        if (type1.isEmpty()) {
            System.out.println("Skipping bad CSV row, missing primary type: " + line);
            return null;
        }

        int baseSpeed;

        try {
            baseSpeed = Integer.parseInt(baseSpeedText);
        } catch (NumberFormatException e) {
            System.out.println("Skipping bad CSV row, invalid speed: " + line);
            return null;
        }

        ArrayList<String> types = new ArrayList<>();
        types.add(type1);

        if (!type2.isEmpty()) {
            types.add(type2);
        }

        ArrayList<Ability> abilities = parseAbilities(abilitiesText);

        if (abilities.isEmpty()) {
            System.out.println("Skipping bad CSV row, missing abilities: " + line);
            return null;
        }

        return new PokemonSpecies(name, types, baseSpeed, abilities);
    }

    private static ArrayList<Ability> parseAbilities(String abilitiesText) {
        ArrayList<Ability> abilities = new ArrayList<>();
        String[] abilityNames = abilitiesText.split("\\|");

        for (String abilityName : abilityNames) {
            abilityName = clean(abilityName);

            if (!abilityName.isEmpty()) {
                abilities.add(createAbility(abilityName));
            }
        }

        return abilities;
    }

    private static Ability createAbility(String name) {
        Ability ability = new Ability(name);

        if (name.equalsIgnoreCase("Levitate")) {
            ability.addImmunity("Ground");
        }

        if (name.equalsIgnoreCase("Thick Fat")) {
            ability.addResistance("Fire");
            ability.addResistance("Ice");
        }

        if (name.equalsIgnoreCase("Flash Fire")) {
            ability.addImmunity("Fire");
        }

        if (name.equalsIgnoreCase("Water Absorb")) {
            ability.addImmunity("Water");
        }

        if (name.equalsIgnoreCase("Volt Absorb")
                || name.equalsIgnoreCase("Lightning Rod")
                || name.equalsIgnoreCase("Motor Drive")) {
            ability.addImmunity("Electric");
        }

        if (name.equalsIgnoreCase("Sap Sipper")) {
            ability.addImmunity("Grass");
        }

        return ability;
    }

    private static String clean(String text) {
        if (text == null) {
            return "";
        }

        return text.replace("\uFEFF", "").trim();
    }
}