package fr.hydroxios.twitchInteractive.twitch.command;

import com.github.twitch4j.common.events.domain.EventUser;
import fr.hydroxios.twitchInteractive.TwitchInteractive;
import fr.hydroxios.twitchInteractive.utils.References;
import fr.hydroxios.twitchInteractive.utils.Utils;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;
import java.util.UUID;

public class TCommandDiamond implements ITwitchCommand {

    @Override
    public String getName() {
        return "diamond";
    }

    @Override
    public void execute(EventUser sender, String[] args) {
        if (TwitchInteractive.INSTANCE.getConfig().contains("main_player")) {
            Player p = Bukkit
                    .getPlayer(UUID.fromString(TwitchInteractive.INSTANCE.getConfig().getString("main_player")));
            if (p == null || !p.isOnline())
                return;
            p.getInventory().addItem(new ItemStack(Material.DIAMOND));
            p.sendMessage(References.PREFIX
                    + Utils.formatColoredText("&6" + sender.getName() + " &fgave you a &bdiamond&f !"));
        } else {
            Bukkit.getScheduler().runTask(TwitchInteractive.INSTANCE, () -> {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.getInventory().addItem(new ItemStack(Material.DIAMOND));
                    p.sendMessage(References.PREFIX
                            + Utils.formatColoredText("&6" + sender.getName() + " &fgave you a &bdiamond&f !"));
                    p.playSound(p, Sound.ENTITY_PLAYER_LEVELUP, 1f, 0.75f);
                    spawnDiamondRain(p, (int) Math.round(Math.random() * 15));
                }
            });
        }
    }

    private static void spawnDiamondRain(Player player, int amount) {
        Random random = new Random();
        for (int i = 0; i < amount; i++) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    double x = player.getLocation().getX() + (random.nextDouble() - 0.5) * 4.0;
                    double y = player.getLocation().getY() + 2.0 + random.nextDouble();
                    double z = player.getLocation().getZ() + (random.nextDouble() - 0.5) * 4.0;

                    ItemStack stack = new ItemStack(Material.DIAMOND);
                    stack.addUnsafeEnchantment(Enchantment.EFFICIENCY, 1);
                    Item item = player.getWorld().dropItemNaturally(
                            new Location(player.getWorld(), x, y, z),
                            stack);
                    // Rends l'item impossible à ramasser
                    item.setPickupDelay(Integer.MAX_VALUE);
                    // Optionnel : petit effet de chute pour la pluie
                    item.setVelocity(new Vector(
                            (random.nextDouble() - 0.5) * 0.2,
                            0.2 + random.nextDouble() * 0.2,
                            (random.nextDouble() - 0.5) * 0.2));

                    // Remove l'item après 5 secondes
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            item.remove();
                        }
                    }.runTaskLater(TwitchInteractive.INSTANCE, 100L); // 100 ticks = 5 secondes
                }
            }.runTaskLater(TwitchInteractive.INSTANCE, 20L * i);

        }
    }
}
