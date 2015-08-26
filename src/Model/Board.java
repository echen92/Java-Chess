package Model;

import Model.Piece.*;

import java.util.Stack;

/**
 * This class is a representation of a chess board. The Orientation of
 * the board is represented with X for rows and Y for columns.
 * @author erik chen
 */
public class Board {
    private static Piece[][] board;
    private Player currPlayer = Player.WHITE;
    private Stack<Piece> moveSrcs;
    private Stack<Piece> moveDests;
    private Stack<Move> moveHistory;
    private static int scoreBlack;
    private static int scoreWhite;
    private static int scoreTies;

    private boolean unitTesting = false;

    /**
     * Default constructor for a chessboard
     */
    public Board() {
        board = new Piece[8][8];
        moveHistory = new Stack<Move>();
        moveSrcs = new Stack<Piece>();
        moveDests = new Stack<Piece>();
        scoreBlack = scoreWhite = scoreTies = 0;
    }

    /**
     * Initialize the pieces on the board
     */
    public void initBoard() {
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(Player.WHITE);
            board[6][i] = new Pawn(Player.BLACK);
        }

        board[0][0] = new Rook(Player.WHITE);
        board[0][1] = new Knight(Player.WHITE);
        board[0][2] = new Bishop(Player.WHITE);
        board[0][3] = new Queen(Player.WHITE);
        board[0][4] = new King(Player.WHITE);
        board[0][5] = new Bishop(Player.WHITE);
        board[0][6] = new Knight(Player.WHITE);
        board[0][7] = new Rook(Player.WHITE);

        board[7][0] = new Rook(Player.BLACK);
        board[7][1] = new Knight(Player.BLACK);
        board[7][2] = new Bishop(Player.BLACK);
        board[7][3] = new Queen(Player.BLACK);
        board[7][4] = new King(Player.BLACK);
        board[7][5] = new Bishop(Player.BLACK);
        board[7][6] = new Knight(Player.BLACK);
        board[7][7] = new Rook(Player.BLACK);
    }

    /**
     * Sets up board with special pieces
     */
    public void setBoardSpecial() {
        board[0][3] = new Empress(Player.WHITE);
        board[7][3] = new Empress(Player.BLACK);
        board[0][6] = new Wraith(Player.WHITE);
        board[7][6] = new Wraith(Player.BLACK);
    }

    /**
     * Remove all pieces off the board
     */
    public void tearDownBoard() {
        while(!moveHistory.isEmpty()) {
            moveHistory.pop();
            moveSrcs.pop();
            moveDests.pop();
        }
        for (int i =0; i < 8; i++) {
            for (int j =0; j < 8; j++) {
                if (board[i][j] != null)
                    removePieceAt(i,j);
            }
        }
    }

    /**
     * Moves a piece on the board
     * @param move Representation of source and target location
     * @return True if move was successful, false otherwise
     */
    public boolean movePiece(Move move) {
        int startX = move.getStartX();
        int startY = move.getStartY();
        int endX = move.getEndX();
        int endY = move.getEndY();
        Piece pieceToMove = getPieceAt(startX, startY);

        if (unitTesting) ; // Ignore player turns if debugging is on. Required for testing
        else if(startX == endX && startY == endY)
            return false;
        else if (pieceToMove == null || pieceToMove.getColorOfPiece() != currPlayer)
            return false;

        if (moveIsWithinBounds(endX, endY) && pieceToMove.isValidMove(move)) {
            moveHistory.push(move);
            moveSrcs.push(getPieceAt(move.getStartX(), move.getStartY()));
            moveDests.push(removePieceAt(move.getEndX(), move.getEndY()));

            removePieceAt(move.getStartX(), move.getStartY());
            setPieceAt(pieceToMove, move.getEndX(), move.getEndY());

            setPlayerTurn();
            return true;
        }
        else
            return false;
    }

    /**
     * Undos the last move that was made
     * @return True if successful, false otherwise
     */
    public boolean undoMove() {
        if (moveSrcs.isEmpty())
            return false;

        Piece srcPiece = moveSrcs.pop();
        Piece destPiece = moveDests.pop();
        Move lastMove = moveHistory.pop();

        removePieceAt(lastMove.getStartX(), lastMove.getStartY());
        removePieceAt(lastMove.getEndX(), lastMove.getEndY());
        setPieceAt(srcPiece, lastMove.getStartX(), lastMove.getStartY());
        setPieceAt(destPiece, lastMove.getEndX(), lastMove.getEndY());

        setPlayerTurn();
        return true;
    }

    /**
     * Gets the array that holds the location of each piece
     * @return 2D array that represents the board
     */
    public static Piece[][] getBoard() {
        return board;
    }

    /**
     * Gets which players turn it is
     * @return the player that is to make a move
     */
    public Player getPlayerTurn() { return currPlayer; }

    /**
     * Gets score of the player controlling black pieces
     * @return Score of player black
     */
    public int getScoreBlack() { return scoreBlack; }

    /**
     * Gets score of the player controlling white pieces
     * @return Score of player white
     */
    public int getScoreWhite() { return scoreWhite; }

    /**
     * Gets the number of ties
     * @return Number of times game has tied
     */
    public int getScoreTies() {
        return scoreTies;
    }

    /**
     * Update a players score
     * @param c Player of the player
     */
    public void updateScore(Player c) {
        if (c == Player.BLACK)
            scoreBlack++;
        else if (c == Player.WHITE)
            scoreWhite++;
        else scoreTies++;
    }

    /**
     * Forfeit a game and player score
     * @param c Player of the player that is forfeiting
     */
    public void forfeit(Player c) {
        if (c == Player.WHITE)
            updateScore(Player.BLACK);
        else updateScore(Player.WHITE);
    }

    /**
     * Reset scores of players
     */
    public void resetScores() {
        scoreBlack = scoreWhite = scoreTies = 0;
    }

    /**
     * Set game state to be other player's turn to move
     */
    public void setPlayerTurn() {
        if (currPlayer == Player.WHITE)
            currPlayer = Player.BLACK;
        else currPlayer = Player.WHITE;
    }

    /**
     * Gets the piece specified at location on board
     * @param x The row of the piece
     * @param y The column of the piece
     * @return The piece at the location, null if none exists
     */
    public static Piece getPieceAt(int x, int y) {
        return board[x][y];
    }

    /**
     * Places a piece at the coordinates on the board
     * @param p The piece to place
     * @param x The row of the piece
     * @param y The column of the piece
     */
    public void setPieceAt(Piece p, int x, int y) {
        board[x][y] = p;
    }

    /**
     * Removes the piece at the location specified
     * @param x The row of the piece
     * @param y The column of the piece
     */
    public Piece removePieceAt(int x, int y) {
        Piece p = getPieceAt(x, y);
        board[x][y] = null;
        return p;
    }

    /**
     * Checks if the coordinates are within bounds on the board
     * @param x The row of the piece
     * @param y The column of the piece
     * @return True if in bounds, false otherwise
     */
    private boolean moveIsWithinBounds(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    /**
     * Prints a text representation of the board for debugging
     */
    public void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String temp = "";
                Piece pieceAt = board[i][j];

                if(pieceAt == null)
                    temp += "N  ";
                else if (pieceAt.getColorOfPiece() == Player.BLACK)
                    temp += "B";
                else temp += "W";


                if (pieceAt != null && pieceAt instanceof Pawn)
                    temp += "P ";
                else if(pieceAt != null && pieceAt instanceof Rook)
                    temp += "R ";
                else if(pieceAt != null && pieceAt instanceof Knight)
                    temp += "K ";
                else if(pieceAt != null && pieceAt instanceof Bishop)
                    temp += "B ";
                else if(pieceAt != null && pieceAt instanceof Queen)
                    temp += "Q ";
                else if(pieceAt != null && pieceAt instanceof King)
                    temp += "K ";
                else if(pieceAt != null && pieceAt instanceof Empress)
                    temp += "E ";
                else if(pieceAt != null && pieceAt instanceof Wraith)
                    temp += "W ";

                System.out.print(temp);
            }
            System.out.println();
        }
    }
}
