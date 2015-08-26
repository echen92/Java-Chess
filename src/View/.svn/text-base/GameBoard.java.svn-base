package View;

import Controller.GameController;
import Model.Board;
import Model.Piece.*;
import Model.Player;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Builds and maintains the view of the chessboard
 */
public class GameBoard implements ActionListener {
    private JFrame frame;
    private JPanel gridPanel;
    private JLabel turnLabel;
    private JLabel scoreLabel;
    private JLabel selectedPieceLabel;
    private Board board;
    private GameController controller;
    private String currentGameType;
    private int moveX = -1, moveY = -1;

    /**
     * Default constructor for a game view
     */
    public GameBoard() {
        board = new Board();
        board.initBoard();
        controller = new GameController(board, this);

        frame = new JFrame("CS242 Chess");
        frame.setSize(600, 600);
        frame.setLayout(new BorderLayout());

        setupMenuBar();
        setupStatusBar();
        gridPanel = createGrid();
        frame.add(gridPanel, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        if (action.contains(",")) {
            int x = Integer.parseInt(action.substring(0, 1));
            int y = Integer.parseInt(action.substring(2, 3));
            processMove(x, y, action);
        }

        else if (action.equals("Start classic game"))
            startNewGame("Classic");

        else if (action.equals("Start eccentric game"))
            startNewGame("Eccentric");

        else if (action.equals("Exit"))
            System.exit(0);

        else if (action.equals("Reset game")) {
            if (currentGameType == null) {
                noGameStarted();
                return;
            }

            boolean blackConf = confirmReset("Black");
            boolean whiteConf = confirmReset("White");

            if (!blackConf || !whiteConf) {
                JOptionPane.showMessageDialog(null, "Both players did not agree to restart");
                return;
            }

            startNewGame(currentGameType);
            controller.updateScore(null);
            redrawBoard();
        }

        else if (action.equals("Forfeit")) {
            Player playerToForfeit = forfeitDialog();

            controller.forfeit(playerToForfeit);
            redrawBoard();
            askStartNewGame();
        }

        else if (action.equals("Undo"))
            controller.undoMove();

    }

    private boolean confirmReset(String s) {
        int option = JOptionPane.showConfirmDialog(null, s + ", do you agree to restart the game?");
        if (option == JOptionPane.YES_OPTION)
            return true;
        else return false;
    }

    private Player forfeitDialog() {
        Object[] options = {"Black", "White"};
        int result = JOptionPane.showOptionDialog( null,"Who would like to forfeit?","TO FORFEIT == SHAME",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,options,options[0] );
        if (result == JOptionPane.YES_OPTION)
            return Player.BLACK;
        else //if (result == JOptionPane.NO_OPTION)
            return Player.WHITE;
    }

    private void startNewGame(String s) {
        currentGameType = s;

        if (s.equals("Classic")) {
            board.tearDownBoard();
            board.initBoard();
            controller.resetTurns();
            redrawBoard();
        }
        else if (s.equals("Eccentric")) {
            board.tearDownBoard();
            board.initBoard();
            board.setBoardSpecial();
            controller.resetTurns();
            redrawBoard();
        }
    }

    /**
     * Redraws the chessboard
     */
    public void redrawBoard() {
        gridPanel.removeAll();
        populateBoard();
        setStatusLabel("");
        frame.validate();
    }

    /**
     * Popup dialog asking to start a new game
     */
    public void askStartNewGame() {
        int option = JOptionPane.showConfirmDialog(null, "Would you like to start a new game?");

        if (option == JOptionPane.YES_OPTION)
            startNewGame(currentGameType);
        else if (option == JOptionPane.NO_OPTION)
            JOptionPane.showMessageDialog(frame, "BOO YOU LOSER");
    }

    /**
     * Warning message that no game has been started
     */
    public void noGameStarted() {
        JOptionPane.showMessageDialog(frame, "A game has not been started yet!");
    }

    /**
     * Warning message about lack of moves to undo
     */
    public void noMovesToUndo() {
        JOptionPane.showMessageDialog(frame, "There are no moves to undo!");
    }

    private void setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu gameMenu = new JMenu("Game");

        addMenuItem("Start classic game", fileMenu);
        addMenuItem("Start eccentric game", fileMenu);
        addMenuItem("Exit", fileMenu);
        addMenuItem("Reset game", gameMenu);
        addMenuItem("Forfeit", gameMenu);
        addMenuItem("Undo", gameMenu);

        menuBar.add(fileMenu);
        menuBar.add(gameMenu);
        frame.setJMenuBar(menuBar);
    }

    private void addMenuItem(String descr, JMenu menu) {
        JMenuItem menuItem = new JMenuItem(descr);
        menuItem.addActionListener(this);
        menu.add(menuItem);
    }

    private void setupStatusBar() {
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));

        // http://stackoverflow.com/questions/3035880/how-can-i-create-a-bar-in-the-bottom-of-a-java-app-like-a-status-bar
        statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        frame.add(statusPanel, BorderLayout.SOUTH);
        statusPanel.setPreferredSize(new Dimension(frame.getWidth(), 16));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));

        turnLabel = new JLabel();
        scoreLabel = new JLabel();
        selectedPieceLabel = new JLabel();
        setStatusLabel("");

        statusPanel.add(turnLabel);
        statusPanel.add(Box.createHorizontalGlue());
        statusPanel.add(selectedPieceLabel);
        statusPanel.add(Box.createHorizontalGlue());
        statusPanel.add(scoreLabel);

    }

    /**
     * Sets and updates the status label at the bottom of the window
     * @param s The current square selected as a string
     */
    public void setStatusLabel(String s) {
        String turn = "TURN: " + board.getPlayerTurn();
        String score = "SCORE White: " + board.getScoreWhite() + " | Black: " + board.getScoreBlack() + " | Ties: " + board.getScoreTies();
        String selected = "Selected: " + s;

        turnLabel.setText(turn);
        scoreLabel.setText(score);
        selectedPieceLabel.setText(selected);
        frame.validate();
    }

    private JPanel createGrid() {
        JPanel gPanel = new JPanel();
        gPanel.setLayout(new GridLayout(8, 8));
        frame.add(gPanel, BorderLayout.CENTER);
        return gPanel;
    }

    // Got idea from mike
    private void processMove(int x, int y, String s) {
        if (moveX == -1 && moveY == -1) {
            moveX = x;
            moveY = y;
            setStatusLabel(s);
        } else {
            controller.makeMove(moveX, moveY, x, y);
            moveX = -1;
            moveY = -1;
            setStatusLabel("");
        }
    }

    private void populateBoard() {
        ChessButton[][] pieceButtons = buildGUIHelper();

        // Reversed to keep consistency with board model
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {

                // Setup board square colors
                if ((i + j) % 2 == 0)
                    pieceButtons[i][j].setBackground(Color.lightGray);
                else pieceButtons[i][j].setBackground(Color.white);

                gridPanel.add(pieceButtons[i][j]);
            }
        }
        gridPanel.setVisible(true);
        frame.revalidate();
    }

    private ChessButton[][] buildGUIHelper() {
        ChessButton[][] ret = new ChessButton[8][8];

        // White bottom row, Black top. Keep consistency with Board model
        for (int i = 7; i >= 0; i--) {
            for (int j = 7; j >= 0; j--) {
                Piece p = Board.getPieceAt(i, j);

                if (p == null)
                    ret[i][j] = new ChessButton("");
                else {
                    if (p.getColorOfPiece() == Player.BLACK) {
                        if (p instanceof King)
                            ret[i][j] = (new ChessButton("\u265A"));
                        else if (p instanceof Queen)
                            ret[i][j] = (new ChessButton("\u265B"));
                        else if (p instanceof Rook)
                            ret[i][j] = (new ChessButton("\u265C"));
                        else if (p instanceof Bishop)
                            ret[i][j] = (new ChessButton("\u265D"));
                        else if (p instanceof Knight)
                            ret[i][j] = (new ChessButton("\u265E"));
                        else if (p instanceof Pawn)
                            ret[i][j] = (new ChessButton("\u265F"));
                        else if (p instanceof Empress)
                            ret[i][j] = (new ChessButton("\u2660"));
                        else if (p instanceof Wraith)
                            ret[i][j] = (new ChessButton("\u2666"));
                    }
                    else if (p.getColorOfPiece() == Player.WHITE) {
                        if (p instanceof King)
                            ret[i][j] = (new ChessButton("\u2654"));
                        else if (p instanceof Queen)
                            ret[i][j] = (new ChessButton("\u2655"));
                        else if (p instanceof Rook)
                            ret[i][j] = (new ChessButton("\u2656"));
                        else if (p instanceof Bishop)
                            ret[i][j] = (new ChessButton("\u2657"));
                        else if (p instanceof Knight)
                            ret[i][j] = (new ChessButton("\u2658"));
                        else if (p instanceof Pawn)
                            ret[i][j] = (new ChessButton("\u2659"));
                        else if (p instanceof Empress)
                            ret[i][j] = (new ChessButton("\u2664"));
                        else if (p instanceof Wraith)
                            ret[i][j] = (new ChessButton("\u2662"));
                    }
                }

                ret[i][j].addActionListener(this);
                ret[i][j].setActionCommand(Integer.toString(i) + "," + j);
            }
        }

        return ret;
    }


}
