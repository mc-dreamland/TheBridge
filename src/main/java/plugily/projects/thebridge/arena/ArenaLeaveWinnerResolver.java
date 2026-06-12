package plugily.projects.thebridge.arena;

import java.util.List;
import java.util.OptionalInt;

final class ArenaLeaveWinnerResolver {

  private ArenaLeaveWinnerResolver() {
  }

  static OptionalInt findWinnerBaseIndexAfterLeave(List<Integer> basePlayerSizes, int leftBaseIndex) {
    if(leftBaseIndex < 0 || basePlayerSizes.get(leftBaseIndex) > 0) {
      return OptionalInt.empty();
    }
    OptionalInt winnerBaseIndex = OptionalInt.empty();
    for(int i = 0; i < basePlayerSizes.size(); i++) {
      if(basePlayerSizes.get(i) > 0) {
        if(winnerBaseIndex.isPresent()) {
          return OptionalInt.empty();
        }
        winnerBaseIndex = OptionalInt.of(i);
      }
    }
    return winnerBaseIndex;
  }
}
