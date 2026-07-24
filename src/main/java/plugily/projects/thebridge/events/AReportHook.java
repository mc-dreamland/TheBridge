package plugily.projects.thebridge.events;

import com.andan.areport.event.AReportPluginMessageEvent;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import plugily.projects.minigamesbox.api.arena.IArenaState;
import plugily.projects.minigamesbox.api.arena.IPluginArena;
import plugily.projects.minigamesbox.api.arena.IPluginArenaRegistry;
import plugily.projects.minigamesbox.classic.utils.version.VersionUtils;

import java.util.Map;
import java.util.UUID;

public final class AReportHook implements Listener {

  private static final long TARGET_TTL_MILLIS = 15_000L;

  private final Table<String, String, Long> allSuspects = HashBasedTable.create();

  public void load(JavaPlugin plugin) {
    Bukkit.getPluginManager().registerEvents(this, plugin);
  }

  @EventHandler(priority = EventPriority.HIGH)
  public void onLogin(PlayerLoginEvent e) {
    if (allSuspects.containsRow(e.getPlayer().getName())) {
      e.allow();
    }
  }


  @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
  public void onPluginMessage(AReportPluginMessageEvent event) {
    if (event.getType() == null) {
      return;
    }

    String pl = event.getPlayerName();
    String t = event.getKey();
    if (pl == null || pl.isEmpty() || t == null) {
      return;
    }

    long timeMillis = System.currentTimeMillis();
    allSuspects.put(pl, t, timeMillis + TARGET_TTL_MILLIS);
    // GC
    if (allSuspects.size() > Bukkit.getMaxPlayers()) {
      allSuspects.values().removeIf(l -> l > timeMillis);
    }
  }

  public boolean isPolice(@NotNull String who) {
    return allSuspects.containsRow(who);
  }

  @Nullable
  public Player getSuspectOf(String of) {
    Map<String, Long> row = allSuspects.row(of);
    if (row.isEmpty()) {
      return null;
    }

    long timeMillis = System.currentTimeMillis();
    for (Map.Entry<String, Long> l : row.entrySet()) {
      if (l.getValue() > timeMillis) {
        String key = l.getKey();
        Player t = key.length() == 36 ? Bukkit.getPlayer(UUID.fromString(key)) : Bukkit.getPlayerExact(key);
        if (t != null) {
          return t;
        }
      }
    }
    return null;
  }
}
