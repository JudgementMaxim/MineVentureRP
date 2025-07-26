package org.JudgementM.mineVentureRP.jobs;

import org.bukkit.entity.Player;

public interface Job {
    void onBlockBreak(Player player); // z.B. Belohnung
    void onCraft(Player player);
    String getName();
    int getTier();
}
