package org.JudgementM.mineVentureRP.jobs;

public class JobProgression {
    public int getXpForNextLevel(int currentLevel) {
        return 100 * currentLevel;
    }

    public boolean canLevelUp(int currentXp, int currentLevel) {
        return currentXp >= getXpForNextLevel(currentLevel);
    }
}
