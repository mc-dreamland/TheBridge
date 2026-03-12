package plugily.projects.thebridge.events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;
import plugily.projects.thebridge.arena.Arena;
import plugily.projects.thebridge.arena.base.Base;


public class BridgePortalScoredEvent extends PlayerEvent {
  public static final HandlerList handler = new HandlerList();
  public final Arena arena;
  public final Base base;

  public BridgePortalScoredEvent(@NotNull Player who, Arena arena, Base base) {
    super(who);
    this.arena = arena;
    this.base = base;
  }

  @NotNull
  @Override
  public HandlerList getHandlers() {
    return handler;
  }
}
