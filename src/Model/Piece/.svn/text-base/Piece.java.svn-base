package Model.Piece;

import Model.Move;
import Model.Player;
/**
 * An abstract class representing a chess piece
 */
public abstract class Piece {
    protected Player player;

    /**
     * Constructor for a chess piece with a color
     * @param c Player of the piece
     */
    public Piece(Player c) {
        this.player = c;
    }

    /**
     * Gets the color of a piece
     * @return Player of the piece
     */
    public Player getColorOfPiece() {
        return this.player;
    }

    /**
     * Checks if a move is valid for a piece
     * @param move The move to check
     * @return True if move is valid, false otherwise
     */
    public abstract boolean isValidMove(Move move);

    /**
     * Checks if the path to the destination is blocked
     * @param move The move to check
     * @return True if path is blocked, false otherwise
     */
    public abstract boolean isPathObstructed(Move move);
}
