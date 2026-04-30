package Final;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class PokedexGUI {
	// Builds the graphical interface for selecting both the user's team and the opponent's team.
    private Pokedex pokedex;

    private JComboBox<String>[] yourPokemonBoxes;
    private JComboBox<String>[] yourAltBoxes;
    private JComboBox<String>[] yourAbilityBoxes;
    private JToggleButton[] yourMegaButtons;

    private JComboBox<String>[] opponentPokemonBoxes;
    private JComboBox<String>[] opponentAltBoxes;
    private JComboBox<String>[] opponentAbilityBoxes;
    private JToggleButton[] opponentMegaButtons;

    private JTextArea yourOutput;
    private JTextArea opponentOutput;
    private JTextArea summaryOutput;

    public PokedexGUI(Pokedex pokedex) {
    	// Updates available forms and abilities when a Pokémon is selected.
        this.pokedex = pokedex;

        JFrame frame = new JFrame("Pokemon Team Analyzer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1300, 750);
        frame.setLayout(new BorderLayout());

        JPanel mainInputPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        mainInputPanel.add(createTeamInputPanel("Your Team", true));
        mainInputPanel.add(createTeamInputPanel("Opponent Team", false));

        JPanel outputPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        yourOutput = new JTextArea();
        opponentOutput = new JTextArea();

        yourOutput.setEditable(false);
        opponentOutput.setEditable(false);

        yourOutput.setFont(new Font("Monospaced", Font.PLAIN, 12));
        opponentOutput.setFont(new Font("Monospaced", Font.PLAIN, 12));

        outputPanel.add(new JScrollPane(yourOutput));
        outputPanel.add(new JScrollPane(opponentOutput));

        summaryOutput = new JTextArea(5, 20);
        summaryOutput.setEditable(false);
        summaryOutput.setFont(new Font("Monospaced", Font.BOLD, 13));

        JButton analyzeButton = new JButton("Analyze Teams");
        analyzeButton.addActionListener(e -> analyzeTeams());

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(analyzeButton, BorderLayout.NORTH);
        bottomPanel.add(new JScrollPane(summaryOutput), BorderLayout.CENTER);

        frame.add(mainInputPanel, BorderLayout.NORTH);
        frame.add(outputPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private JPanel createTeamInputPanel(String title, boolean isYourTeam) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(title));

        JPanel grid = new JPanel(new GridLayout(7, 4, 5, 5));

        grid.add(new JLabel("Pokemon"));
        grid.add(new JLabel("Alt Form"));
        grid.add(new JLabel("Ability"));
        grid.add(new JLabel("Mega"));

        JComboBox<String>[] pokemonBoxes = new JComboBox[6];
        JComboBox<String>[] altBoxes = new JComboBox[6];
        JComboBox<String>[] abilityBoxes = new JComboBox[6];
        JToggleButton[] megaButtons = new JToggleButton[6];

        ArrayList<String> names = pokedex.getAllNames();
        names.add(0, "None");

        for (int i = 0; i < 6; i++) {
            pokemonBoxes[i] = new JComboBox<>(names.toArray(new String[0]));
            altBoxes[i] = new JComboBox<>();
            abilityBoxes[i] = new JComboBox<>();
            megaButtons[i] = new JToggleButton("OFF");
            megaButtons[i].setEnabled(false);

            int row = i;

            pokemonBoxes[i].addActionListener(e ->
                    updateRow(pokemonBoxes[row], altBoxes[row], abilityBoxes[row], megaButtons[row]));

            altBoxes[i].addActionListener(e ->
                    updateAbilityBox(getSelectedSpecies(pokemonBoxes[row], altBoxes[row], megaButtons[row]),
                            abilityBoxes[row]));

            megaButtons[i].addActionListener(e -> {
                if (megaButtons[row].isSelected()) {
                    megaButtons[row].setText("ON");
                } else {
                    megaButtons[row].setText("OFF");
                }

                updateAbilityBox(getSelectedSpecies(pokemonBoxes[row], altBoxes[row], megaButtons[row]),
                        abilityBoxes[row]);
            });

            grid.add(pokemonBoxes[i]);
            grid.add(altBoxes[i]);
            grid.add(abilityBoxes[i]);
            grid.add(megaButtons[i]);
        }

        if (isYourTeam) {
            yourPokemonBoxes = pokemonBoxes;
            yourAltBoxes = altBoxes;
            yourAbilityBoxes = abilityBoxes;
            yourMegaButtons = megaButtons;
        } else {
            opponentPokemonBoxes = pokemonBoxes;
            opponentAltBoxes = altBoxes;
            opponentAbilityBoxes = abilityBoxes;
            opponentMegaButtons = megaButtons;
        }

        panel.add(grid, BorderLayout.CENTER);
        return panel;
    }

    private void updateRow(JComboBox<String> pokemonBox,
                           JComboBox<String> altBox,
                           JComboBox<String> abilityBox,
                           JToggleButton megaButton) {

        altBox.removeAllItems();
        abilityBox.removeAllItems();

        megaButton.setSelected(false);
        megaButton.setText("OFF");
        megaButton.setEnabled(false);

        String name = (String) pokemonBox.getSelectedItem();

        if (name == null || name.equals("None")) {
            return;
        }

        PokemonSpecies species = pokedex.get(name);

        if (species == null) {
            return;
        }

        altBox.addItem("Base");

        for (PokemonSpecies alt : species.getAltForms()) {
            altBox.addItem(alt.getName());
        }

        megaButton.setEnabled(!species.getMegaForms().isEmpty());

        updateAbilityBox(species, abilityBox);
    }

    private PokemonSpecies getSelectedSpecies(JComboBox<String> pokemonBox,
                                              JComboBox<String> altBox,
                                              JToggleButton megaButton) {
    	//Input validation!
        String name = (String) pokemonBox.getSelectedItem();

        if (name == null || name.equals("None")) {
            return null;
        }

        PokemonSpecies base = pokedex.get(name);

        if (base == null) {
            return null;
        }

        if (megaButton.isSelected() && !base.getMegaForms().isEmpty()) {
            return base.getMegaForms().get(0);
        }

        String altName = (String) altBox.getSelectedItem();

        if (altName != null && !altName.equals("Base")) {
            for (PokemonSpecies alt : base.getAltForms()) {
                if (alt.getName().equals(altName)) {
                    return alt;
                }
            }
        }

        return base;
    }

    private void updateAbilityBox(PokemonSpecies species, JComboBox<String> abilityBox) {
        abilityBox.removeAllItems();

        if (species == null) {
            return;
        }

        for (Ability ability : species.getAbilities()) {
            abilityBox.addItem(ability.getName());
        }
    }

    private Ability getSelectedAbility(PokemonSpecies species, JComboBox<String> abilityBox) {
        String abilityName = (String) abilityBox.getSelectedItem();

        for (Ability ability : species.getAbilities()) {
            if (ability.getName().equals(abilityName)) {
                return ability;
            }
        }

        return species.getAbilities().get(0);
    }

    private Pokemon buildPokemon(PokemonSpecies species, Ability ability) {
        return new Pokemon(
                species.getName(),
                species.getTypes(),
                new ArrayList<>(Arrays.asList(ability))
        );
    }

    private void analyzeTeams() {
        ArrayList<PokemonSpecies> yourTeam = collectTeam(yourPokemonBoxes, yourAltBoxes, yourAbilityBoxes, yourMegaButtons);
        ArrayList<PokemonSpecies> opponentTeam = collectTeam(opponentPokemonBoxes, opponentAltBoxes, opponentAbilityBoxes, opponentMegaButtons);

        yourOutput.setText(buildTeamReport("YOUR TEAM", yourTeam, yourAbilityBoxes));
        opponentOutput.setText(buildTeamReport("OPPONENT TEAM", opponentTeam, opponentAbilityBoxes));

        StringBuilder summary = new StringBuilder();
        summary.append(buildTeamWeaknessSummary("Your team", yourTeam, yourAbilityBoxes));
        summary.append("\n");
        summary.append(buildTeamWeaknessSummary("Opponent team", opponentTeam, opponentAbilityBoxes));

        summaryOutput.setText(summary.toString());
    }

    private ArrayList<PokemonSpecies> collectTeam(JComboBox<String>[] pokemonBoxes,
                                                  JComboBox<String>[] altBoxes,
                                                  JComboBox<String>[] abilityBoxes,
                                                  JToggleButton[] megaButtons) {

        ArrayList<PokemonSpecies> team = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            PokemonSpecies species = getSelectedSpecies(pokemonBoxes[i], altBoxes[i], megaButtons[i]);

            if (species != null) {
                team.add(species);
            }
        }

        Sorter.sortBySpeed(team);
        return team;
    }

    private String buildTeamReport(String title,
                                   ArrayList<PokemonSpecies> team,
                                   JComboBox<String>[] abilityBoxes) {

        StringBuilder output = new StringBuilder();
        output.append("========== ").append(title).append(" ==========\n\n");

        for (PokemonSpecies species : team) {
            Ability ability = species.getAbilities().get(0);

            Pokemon pokemon = buildPokemon(species, ability);
            HashMap<String, Double> effectiveness = TypeChart.calculateEffectiveness(pokemon);

            output.append("=== ").append(species.getName()).append(" ===\n");
            output.append("Types: ").append(species.getTypes()).append("\n");
            output.append("Ability: ").append(ability.getName()).append("\n");
            output.append("Speed: ").append(SpeedCalculator.getAllSpeeds(species.getBaseSpeed())).append("\n");
            output.append("Weaknesses: ").append(TypeChart.getWeaknesses(effectiveness)).append("\n");
            output.append("Resistances: ").append(TypeChart.getResistances(effectiveness)).append("\n");
            output.append("Immunities: ").append(TypeChart.getImmunities(effectiveness)).append("\n\n");
        }

        return output.toString();
    }

    private String buildTeamWeaknessSummary(String teamName,
                                            ArrayList<PokemonSpecies> team,
                                            JComboBox<String>[] abilityBoxes) {

        HashMap<String, Integer> weaknessCounts = new HashMap<>();

        for (PokemonSpecies species : team) {
            Ability ability = species.getAbilities().get(0);
            Pokemon pokemon = buildPokemon(species, ability);

            HashMap<String, Double> effectiveness = TypeChart.calculateEffectiveness(pokemon);

            for (String type : effectiveness.keySet()) {
                if (effectiveness.get(type) > 1.0) {
                    weaknessCounts.put(type, weaknessCounts.getOrDefault(type, 0) + 1);
                }
            }
        }

        StringBuilder result = new StringBuilder();

        for (String type : weaknessCounts.keySet()) {
            if (weaknessCounts.get(type) > 2) {
                result.append(teamName)
                        .append(" is weak to ")
                        .append(type)
                        .append(" type moves. ")
                        .append("(")
                        .append(weaknessCounts.get(type))
                        .append(" Pokemon)\n");
            }
        }

        if (result.length() == 0) {
            result.append(teamName).append(" has no major shared weaknesses.\n");
        }

        return result.toString();
    }
}
