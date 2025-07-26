package org.JudgementM.mineVentureRP.jobs;

import org.JudgementM.mineVentureRP.database.PlayerDataDAO;
import org.JudgementM.mineVentureRP.database.PlayerDataDAO.PlayerData;
import org.JudgementM.mineVentureRP.jobs.jobsimpl.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JobManager {

    private final PlayerDataDAO playerDataDAO;
    private final Map<UUID, Job> activeJobs = new HashMap<>();

    public JobManager(PlayerDataDAO playerDataDAO) {
        this.playerDataDAO = playerDataDAO;
    }

    /**
     * Lädt den Job für einen Spieler anhand der gespeicherten Daten
     */
    public void loadPlayerJob(UUID uuid) {
        try {
            PlayerData data = playerDataDAO.getPlayerData(uuid);
            if (data != null) {
                JobType jobType = JobType.valueOf(data.getJob().toUpperCase());
                Job job = createJobInstance(jobType);
                activeJobs.put(uuid, job);
            }
        } catch (SQLException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gibt den aktiven Job eines Spielers zurück
     */
    public Job getPlayerJob(UUID uuid) {
        return activeJobs.get(uuid);
    }

    /**
     * Setzt einen neuen Job für einen Spieler und speichert ihn in der Datenbank
     */
    public void setPlayerJob(UUID uuid, JobType jobType) {
        Job job = createJobInstance(jobType);
        activeJobs.put(uuid, job);
        try {
            PlayerData data = playerDataDAO.getPlayerData(uuid);
            if (data != null) {
                playerDataDAO.savePlayerData(uuid, jobType.name(), data.getTown(), data.getCoins());
            } else {
                playerDataDAO.savePlayerData(uuid, jobType.name(), "Unbekannt", 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private Job createJobInstance(JobType type) {
        return switch (type) {
            case FARMER -> null;
            case MINER -> null;
            case SMITH -> null;
            case PIRATE -> null;
            case TRADER -> null;
            case BAKER -> null;
            case COOK -> null;
            case WEAPONSMITH -> null;
            case ARMORSMITH -> null;
            case TOOLSMITH -> null;
            case HUNTER -> null;
            case BUILDER -> null;
            case ALCHEMIST -> null;
            case ENCHANTER -> null;
            case LUMBERJACK -> null;
            case FISHER -> null;
        };

    }


    public void unloadPlayerJob(UUID uuid) {
        activeJobs.remove(uuid);
    }
}
