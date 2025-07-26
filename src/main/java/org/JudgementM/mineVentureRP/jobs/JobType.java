package org.JudgementM.mineVentureRP.jobs;

public enum JobType {
    FARMER,        // Felder anlegen, schneller ernten, bessere Drops aus Pflanzen
    MINER,         // Erze abbauen, erhöhte Chance auf doppelte Drops
    SMITH,         // Schmiedet Eisen-/Diamantwerkzeuge & -Rüstung
    PIRATE,        // Fokus auf Plündern, Boote, Schatzsuchen – mehr RP-/Abenteuerklasse
    TRADER,        // Villager- und Itemhandel, Shoprechte
    BAKER,         // Brot, Kuchen, goldene Äpfel etc.
    COOK,          // Gekochtes Fleisch mit Bonus-Sättigung
    WEAPONSMITH,   // Herstellung & Reparatur von Waffen
    ARMORSMITH,    // Herstellung & Reparatur von Rüstungen
    TOOLSMITH,     // Herstellung & Reparatur von Werkzeugen
    HUNTER,        // Drops von Tieren & Monstern, Fokus auf Tierprodukte
    BUILDER,       // Bonus auf Crafting von Baublöcken, Zugriff auf Spezialblöcke
    ALCHEMIST,     // Tränke brauen mit verbesserten Rezepten
    ENCHANTER,     // Stärkere Verzauberungen, gezieltes Enchanting
    LUMBERJACK,    // Schneller Bäume fällen, Bonusdrops
    FISHER         // Besseres Angeln, schneller & seltener Loot
    ;

    public String getDisplayName() {
        return switch (this) {
            case FARMER -> "Farmer";
            case MINER -> "Bergarbeiter";
            case SMITH -> "Schmied";
            case PIRATE -> "Pirat";
            case TRADER -> "Händler";
            case BAKER -> "Bäcker";
            case COOK -> "Koch";
            case WEAPONSMITH -> "Waffenschmied";
            case ARMORSMITH -> "Rüstungsschmied";
            case TOOLSMITH -> "Werkzeugschmied";
            case HUNTER -> "Jäger";
            case BUILDER -> "Baumeister";
            case ALCHEMIST -> "Alchemist";
            case ENCHANTER -> "Verzauberer";
            case LUMBERJACK -> "Holzfäller";
            case FISHER -> "Fischer";
        };
    }
}
