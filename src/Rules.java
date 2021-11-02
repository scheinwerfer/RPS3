public class Rules {
    public String whoWin(int playerMove, int computerMove, int argsLength) {
        if (playerMove == computerMove) {
            return "Draw";
        } else if (computerMove > playerMove && computerMove - playerMove > (argsLength / 2)){
            return "Win";
        } else if (playerMove > computerMove && playerMove - computerMove < (argsLength / 2)){
            return "Win";
        }
        return "Lose";
    }
}