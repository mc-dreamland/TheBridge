package plugily.projects.thebridge.api.events.player;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;
import plugily.projects.thebridge.arena.Arena;

public class TBPlayerKillEvent extends PlayerEvent {

  private static final HandlerList handlers = new HandlerList();
  private final Player victim;
  private final Arena arena;

  public TBPlayerKillEvent(@NotNull Player killer, @NotNull Player victim, @NotNull Arena arena) {
    super(killer);
    this.victim = victim;
    this.arena = arena;
  }

  public static HandlerList getHandlerList() {
    return handlers;
  }

  public Player getKiller() {
    return getPlayer();
  }

  public Player getVictim() {
    return victim;
  }

  public Arena getArena() {
    return arena;
  }

  @NotNull
  @Override
  public HandlerList getHandlers() {
    return handlers;
  }
}
