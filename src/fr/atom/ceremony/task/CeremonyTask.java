package fr.atom.ceremony.task;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import fr.atom.api.utils.MessageUtils;
import fr.atom.ceremony.config.ConfigManager;
import fr.atom.ceremony.manager.CeremonyManager;

public class CeremonyTask extends BukkitRunnable {

    private final CeremonyManager ceremonyManager = CeremonyManager.getINSTANCE();
    private final ConfigManager config = ConfigManager.getINSTANCE();

    private int taskTimer = 0;

    @Override
    public void run() {
        if (ceremonyManager.isCancel) {
        	this.ceremonyManager.setStarted(false);
            this.cancel();
            return;
        }

        if (taskTimer >= config.CEREMONY_TIME) {
        	this.ceremonyManager.setStarted(false);
            this.cancel();
            return;
        }

        Map<Integer, String> message = config.CEREMONY_TASK_MESSAGE;
        if (message.containsKey(taskTimer)) {
            String taskMessage = message.get(taskTimer);
            String processedMessage = processMessage(taskMessage);

            if (taskMessage.contains("{CHAT}")) {
                Bukkit.broadcastMessage(processedMessage.replace("{CHAT}", ""));
            } else if (taskMessage.contains("{TITLE}") && taskMessage.contains("{SUB-TITLE}")) {
                String[] parts = processedMessage.split("\\{SUB-TITLE\\}");
                String title = parts[0].replace("{TITLE}", "").trim();
                String subTitle = parts[1].trim();
                MessageUtils.clearTitleToAllPlayers();
                MessageUtils.sendTitleToAllPlayers(title, subTitle);
            } else if (taskMessage.contains("{TITLE}")) {
                String title = processedMessage.replace("{TITLE}", "").trim();
                MessageUtils.clearTitleToAllPlayers();
                MessageUtils.sendTitleToAllPlayers(title, "");
            } else if (taskMessage.contains("{SUB-TITLE}")) {
                String subTitle = processedMessage.replace("{SUB-TITLE}", "").trim();
                MessageUtils.sendTitleToAllPlayers("", subTitle);
            } else if (taskMessage.contains("{START-CEREMONY-SOUND}")) {
                ceremonyManager.startSound();
            } else if (taskMessage.contains("{START-THANK-ANIMATION}")) {
                if (config.END_ANIMATION_ACTIVE) {
                    ceremonyManager.startThankYouAnimation();
                }
            }
        }

        taskTimer++;
    }

    private String processMessage(String message) {
        return message.replace("&", "§");
    }
}

