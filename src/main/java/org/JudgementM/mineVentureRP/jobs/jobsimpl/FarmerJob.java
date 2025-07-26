package org.JudgementM.mineVentureRP.jobs.jobsimpl;

import org.bukkit.entity.Player;
import org.JudgementM.mineVentureRP.jobs.Job;

public class FarmerJob implements Job {

    @Override
    public void onBlockBreak(Player player) {
        // z.B. bei Ernten XP oder Coins geben
    }

    @Override
    public void onCraft(Player player) {
        // evtl. craftbare Items beschränken
    }

    @Override
    public String getName() {
        return "Farmer";
    }

    @Override
    public int getTier() {
        return 1;
    }
}
