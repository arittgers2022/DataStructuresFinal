package Final;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Before;
import org.junit.Test;

public class PokemonProjectTest {
	// Tests speed calculations, type effectiveness, ability effects, and sorting behavior.
	// Confirms that weaknesses, resistances, immunities, and ability modifiers work as expected.
    @Before
    public void setup() {
        TypeChart.initialize();
    }

    @Test
    public void testSpeedCalculations() {
        int baseSpeed = 100;

        assertEquals(90, SpeedCalculator.slowNature(baseSpeed));
        assertEquals(100, SpeedCalculator.neutral(baseSpeed));
        assertEquals(132, SpeedCalculator.maxIV(baseSpeed));
        assertEquals(145, SpeedCalculator.maxIVFastNature(baseSpeed));
    }

    @Test
    public void testSpeedRoundingDown() {
        int baseSpeed = 81;

        assertEquals(72, SpeedCalculator.slowNature(baseSpeed));
        assertEquals(124, SpeedCalculator.maxIVFastNature(baseSpeed));
    }

    @Test
    public void testGrassPokemonWeakToFire() {
        Pokemon pokemon = new Pokemon(
                "Test Grass",
                new ArrayList<>(Arrays.asList("Grass")),
                new ArrayList<>()
        );

        HashMap<String, Double> result = TypeChart.calculateEffectiveness(pokemon);

        assertTrue(result.get("Fire") > 1.0);
        assertEquals(2.0, result.get("Fire"), 0.01);
    }

    @Test
    public void testGrassPoisonVenusaurWeaknesses() {
        Pokemon venusaur = new Pokemon(
                "Venusaur",
                new ArrayList<>(Arrays.asList("Grass", "Poison")),
                new ArrayList<>()
        );

        HashMap<String, Double> result = TypeChart.calculateEffectiveness(venusaur);

        assertEquals(2.0, result.get("Fire"), 0.01);
        assertEquals(2.0, result.get("Ice"), 0.01);
        assertEquals(2.0, result.get("Flying"), 0.01);
        assertEquals(2.0, result.get("Psychic"), 0.01);
    }

    @Test
    public void testResistance() {
        Pokemon pokemon = new Pokemon(
                "Test Fire",
                new ArrayList<>(Arrays.asList("Fire")),
                new ArrayList<>()
        );

        HashMap<String, Double> result = TypeChart.calculateEffectiveness(pokemon);

        assertEquals(0.5, result.get("Grass"), 0.01);
    }

    @Test
    public void testDualTypeFourTimesWeakness() {
        Pokemon pokemon = new Pokemon(
                "Test Bug Steel",
                new ArrayList<>(Arrays.asList("Bug", "Steel")),
                new ArrayList<>()
        );

        HashMap<String, Double> result = TypeChart.calculateEffectiveness(pokemon);

        assertEquals(4.0, result.get("Fire"), 0.01);
    }

    @Test
    public void testWeaknessAndResistanceCancelOut() {
        Pokemon pokemon = new Pokemon(
                "Test Water Grass",
                new ArrayList<>(Arrays.asList("Water", "Grass")),
                new ArrayList<>()
        );

        HashMap<String, Double> result = TypeChart.calculateEffectiveness(pokemon);

        assertEquals(1.0, result.get("Fire"), 0.01);
    }

    @Test
    public void testGhostImmuneToFighting() {
        Pokemon pokemon = new Pokemon(
                "Gengar",
                new ArrayList<>(Arrays.asList("Ghost", "Poison")),
                new ArrayList<>()
        );

        HashMap<String, Double> result = TypeChart.calculateEffectiveness(pokemon);

        assertEquals(0.0, result.get("Fighting"), 0.01);
    }

    @Test
    public void testLevitateAddsGroundImmunity() {
        Ability levitate = new Ability("Levitate");
        levitate.addImmunity("Ground");

        Pokemon pokemon = new Pokemon(
                "Test Electric",
                new ArrayList<>(Arrays.asList("Electric")),
                new ArrayList<>(Arrays.asList(levitate))
        );

        HashMap<String, Double> result = TypeChart.calculateEffectiveness(pokemon);

        assertEquals(0.0, result.get("Ground"), 0.01);
    }

    @Test
    public void testThickFatReducesFireAndIceDamage() {
        Ability thickFat = new Ability("Thick Fat");
        thickFat.addResistance("Fire");
        thickFat.addResistance("Ice");

        Pokemon megaVenusaur = new Pokemon(
                "Mega Venusaur",
                new ArrayList<>(Arrays.asList("Grass", "Poison")),
                new ArrayList<>(Arrays.asList(thickFat))
        );

        HashMap<String, Double> result = TypeChart.calculateEffectiveness(megaVenusaur);

        assertEquals(1.0, result.get("Fire"), 0.01);
        assertEquals(1.0, result.get("Ice"), 0.01);
    }

    @Test
    public void testSorterSortsByMaxSpeedWithNature() {
        PokemonSpecies slow = new PokemonSpecies(
                "Slowmon",
                new ArrayList<>(Arrays.asList("Normal")),
                50,
                new ArrayList<>(Arrays.asList(new Ability("None")))
        );

        PokemonSpecies fast = new PokemonSpecies(
                "Fastmon",
                new ArrayList<>(Arrays.asList("Normal")),
                120,
                new ArrayList<>(Arrays.asList(new Ability("None")))
        );

        PokemonSpecies middle = new PokemonSpecies(
                "Middlemon",
                new ArrayList<>(Arrays.asList("Normal")),
                80,
                new ArrayList<>(Arrays.asList(new Ability("None")))
        );

        ArrayList<PokemonSpecies> list = new ArrayList<>();
        list.add(slow);
        list.add(fast);
        list.add(middle);

        Sorter.sortBySpeed(list);

        assertEquals("Fastmon", list.get(0).getName());
        assertEquals("Middlemon", list.get(1).getName());
        assertEquals("Slowmon", list.get(2).getName());
    }
}