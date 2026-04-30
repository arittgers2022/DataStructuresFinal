package Final;

public class SpeedCalculator {
	// Calculates possible speed values based on base speed, IV bonus, and nature modifier.
    public static int slowNature(int base) {
        return (int)Math.floor(base * 0.9);
    }

    public static int neutral(int base) {
        return base;
    }

    public static int maxIV(int base) {
        return base + 32;
    }

    public static int maxIVFastNature(int base) {
        return (int)Math.floor((base + 32) * 1.1);
     // Pokémon games round down stat calculations, so decimals are truncated using integer casting.
    }

    public static String getAllSpeeds(int base) {
        return "Slow: " + slowNature(base) +
               ", Neutral: " + neutral(base) +
               ", Max IV: " + maxIV(base) +
               ", Max+Nature: " + maxIVFastNature(base);
    }
}