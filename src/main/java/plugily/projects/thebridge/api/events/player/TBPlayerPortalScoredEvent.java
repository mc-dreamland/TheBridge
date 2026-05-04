package plugily.projects.thebridge.api.events.player;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;
import plugily.projects.thebridge.arena.Arena;
import plugily.projects.thebridge.arena.base.Base;


public class TBPlayerPortalScoredEvent extends PlayerEvent {
  public static final HandlerList handlers = new HandlerList();
  public final Arena arena;
  public final Base base;

  public TBPlayerPortalScoredEvent(@NotNull Player who, Arena arena, Base base) {
    super(who);
    this.arena = arena;
    this.base = base;
  }

  @NotNull
  @Override
  public HandlerList getHandlers() {
    return handlers;
  }

  public static HandlerList getHandlerList(){
    return handlers;
  }


}
