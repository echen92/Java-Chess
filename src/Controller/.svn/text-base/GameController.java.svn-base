package Controller;


import Model.Board;
import Model.Move;
import Model.Player;
import View.GameBoard;

/**
 * The controller of a chess game
 */
public class GameController {
    private Board board;
    private GameBoard view;

    /**
     * Constructor for a controller
     * @param b game board
     * @param boardView the view
     */
    public GameController(Board b, GameBoard boardView) {
        board = b;
        view = boardView;
    }

    /**
     * Undos a move
     */
    public void undoMove() {
        if(!board.undoMove()) {
            view.noMovesToUndo();
            return;
        }
        view.redrawBoard();
    }

    /**
     * Performs the move
     * @param srcX Source X coord
     * @param srcY Source Y coord
     * @param destX Destination X coord
     * @param destY Destination Y coord
     */
    public void makeMove(int srcX, int srcY, int destX, int destY) {
        Move move = new Move(srcX, srcY, destX, destY);
        board.movePiece(move);
        view.redrawBoard();
    }

    /**
     * Forfeit the game
     * @param c The player to forfeit
     */
    public void forfeit(Player c) {
        board.forfeit(c);

    }

    /**
     * Update the score accordingly
     * @param c The player whos score should be updated
     */
    public void updateScore(Player c) {
        if (c == Player.BLACK || c == Player.WHITE)
            board.updateScore(c);
        else board.updateScore(null);
    }

    public void resetTurns() {
        if (board.getPlayerTurn() == Player.BLACK)
            board.setPlayerTurn();
    }
}
