package com.example.chess;

import com.example.chess.pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ChessEngine {
    private Player white;
    private Player black;
    private ChessBoard board;
    private ArrayList<Piece> piecesLeft;
    private Scanner s;
    private HashMap<Character,Integer> charNum = new HashMap<>();
    private HashMap<Integer,Character> numChar = new HashMap<>();
    public ChessEngine(){
        charNum.put('A', 0); charNum.put('B', 1); charNum.put('C', 2); charNum.put('D', 3); charNum.put('E', 4); charNum.put('F', 5); charNum.put('G', 6); charNum.put('H', 7); charNum.put('a', 0); charNum.put('b', 1); charNum.put('c', 2); charNum.put('d', 3); charNum.put('e', 4); charNum.put('f', 5); charNum.put('h', 6); charNum.put('h', 7);
        numChar.put(0,'A'); numChar.put( 1, 'B'); numChar.put( 2,'C'); numChar.put( 3, 'D'); numChar.put( 4, 'E'); numChar.put( 5, 'F'); numChar.put( 6, 'G'); numChar.put( 7, 'H');
        white=new Player(true, this);
        black=new Player(false, this);
        board=new ChessBoard();
        piecesLeft = new ArrayList<>();
        setupGame();
        s = new Scanner(System.in);
    }

    public ChessBoard getBoard(){
       return board;
    }
    public void setupGame(){
        // White Pawn
        for(int i=0;i<8;i++) {
            Piece p = new Pawn(white, white.getValuesArray()[0]);
                    board.setTile(new int[]{6,i},p);
            white.addPiece(p);
            piecesLeft.add(p);
        }
        // White Knight
        for(int i=0;i<2;i++){
            Piece p = new Knight(white, white.getValuesArray()[0]);
            board.setTile(new int[]{7,1+(i*5)},p);
            white.addPiece(p);
            piecesLeft.add(p);
        }
        // White Bishop
        for(int i=0;i<2;i++){
            Piece p = new Bishop(white, white.getValuesArray()[0]);
            board.setTile(new int[]{7,2+(i*3)},p);
            white.addPiece(p);
            piecesLeft.add(p);
        }
        // White Queen
        for(int i=0;i<1;i++){
            Piece p = new Queen(white, white.getValuesArray()[0]);
            board.setTile(new int[]{7,3+(i*5)},p);
            white.addPiece(p);
            piecesLeft.add(p);
        }
        // White King
        for(int i=0;i<1;i++){
            Piece p = new King(white, white.getValuesArray()[0]);
            board.setTile(new int[]{7,4+(i*5)},p);
            white.addPiece(p);
            piecesLeft.add(p);
        }
        // White Rook
        for(int i=0;i<2;i++){
            Piece p = new Rook(white, white.getValuesArray()[0]);
            board.setTile(new int[]{7,0+(i*7)},p);
            white.addPiece(p);
            piecesLeft.add(p);
        }
        // Black Pawn
        for(int i=0;i<8;i++) {
            Piece p = new Pawn(black, black.getValuesArray()[0]);
            board.setTile(new int[]{1,i},p);
            black.addPiece(p);
            piecesLeft.add(p);
        }
        // Black Knight
        for(int i=0;i<2;i++){
            Piece p = new Knight(black, black.getValuesArray()[0]);
            board.setTile(new int[]{0,1+(i*5)},p);
            black.addPiece(p);
            piecesLeft.add(p);
        }
        // Black Queen
        for(int i=0;i<1;i++){
            Piece p = new Queen(black, black.getValuesArray()[0]);
            board.setTile(new int[]{0,3+(i*5)},p);
            black.addPiece(p);
            piecesLeft.add(p);
        }
        // Black King
        for(int i=0;i<1;i++){
            Piece p = new King(black, black.getValuesArray()[0]);
            board.setTile(new int[]{0,4+(i*5)},p);
            black.addPiece(p);
            piecesLeft.add(p);
        }
        // Black Bishop
        for(int i=0;i<2;i++){
            Piece p = new Bishop(black, black.getValuesArray()[0]);
            board.setTile(new int[]{0,2+(i*3)},p);
            black.addPiece(p);
            piecesLeft.add(p);
        }
        // Black Rook
        for(int i=0;i<2;i++){
            Piece p = new Rook(black, black.getValuesArray()[0]);
            board.setTile(new int[]{0,0+(i*7)},p);
            black.addPiece(p);
            piecesLeft.add(p);
        }
    }
    public void reMove(Player p, String errorMessage){
        System.out.println("ReMove called");
        System.out.println("▒ "+errorMessage); System.out.println("▒");
        waitForMove(p);
    }
    public void waitForMove(Player p){
        boolean hasCastled = false;
        board.display(); System.out.println("▒");
        if(p.isWhite()) System.out.println("▒ Whites Turn"); else System.out.println("▒ Blacks Turn");
        System.out.println("▒"); System.out.print("▒ Input Piece: ");

        String input = s.nextLine();
        if(input.length() > 2) { reMove(p, "Invalid."); return;}
        int number = Integer.parseInt(input.substring(1))-1; int letter = charNum.get(input.charAt(0));
        int[] selectedPos = new int[]{number,letter};
        //input fuckup checker
        if(!board.isAllowed(selectedPos)) { reMove(p, "Illegal Move."); return;}
        Piece selected = getBoard().getTile(selectedPos);
        // Piece fuckup checker
        if(selected == null) { reMove(p, "Unknown Piece."); return;}
        if(!selected.getTeam().equals(p)) { reMove(p, "Wrong Team."); return; }

        int[] selectedMove = selectedPos;
        ArrayList<int[]> possibleMoves = selected.cycleMoves(selectedPos, board);

        //System.out.println("▒");
        board.displayMoves(possibleMoves);

        LOOOP: while(true) {
            System.out.println("▒");
            System.out.println("▒ Moves:");
            for(int[] move : possibleMoves) System.out.println("▒ "+numChar.get(move[1])+(move[0]+1));
            if(selected instanceof King){
                if(canLeftCastle(p,false)) {
                    System.out.println("▒ Castle Left (CL)");
                }
                if(canRightCastle(p,false)) {
                    System.out.println("▒ Castle Right (CR)");
                }
            }
            Pawn ppp = null;

            System.out.println("▒ Type X to reselect");
            System.out.print("▒  Select Move: ");

            input = s.nextLine();
            if(input.equals("X") || input.equals("x")) { waitForMove(p); return;}// reselect
            if(input.equals("CR")) { hasCastled = canRightCastle(p,true); break LOOOP;}
            else if(input.equals("CL")) {hasCastled =  canLeftCastle(p,true); break LOOOP;}


            else {
                System.out.println("/"+input);
                selectedMove = new int[]{Integer.parseInt(input.substring(1)) - 1, charNum.get(input.charAt(0))};

                for (int[] tPos : possibleMoves) {
                    if (tPos[0] == selectedMove[0] && tPos[1] == selectedMove[1]) break LOOOP;
                }
                System.out.println("▒ Invalid Move.");
            }
        }
        if(!hasCastled)
            piecesLeft.remove(board.movePiece(selectedPos, selectedMove));
        selected.update();
        board.display();
        System.out.println("▒");
        System.out.println("▒  -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("▒");
    }

    public Player getWhitePlayer() { return white; }
    public Player getBlackPlayer() { return black; }
    public boolean canLeftCastle(Player p, boolean doIt){
        int diff=0;
        if(p.isWhite()){ diff=7; }
        int[] king = new int[]{diff,4};
        int[] rook = new int[]{diff,0};
        int[] e1 = new int[]{diff,2};
        int[] e2= new int[]{diff,3};
        if(board.getTile(king) != null &&
                !board.getTile(king).hasMoved() &&
                board.getTile(rook) != null &&
                !board.getTile(rook).hasMoved() &&
                board.getTile(e1) == null &&
                board.getTile(e2) == null
        )
        { if(!doIt) {return true;}}
        else { return false; }
        board.movePiece(king,e1);
        board.movePiece(rook,e2);
        return true;
    }
    public boolean canRightCastle(Player p, boolean doIt){
        int diff=0;
        if(p.isWhite()){ diff=7; }
        int[] king = new int[]{diff,4};
        int[] rook = new int[]{diff,7};
        int[] e1 = new int[]{diff,5};
        int[] e2= new int[]{diff,6};
        if(board.getTile(king) != null &&
           !board.getTile(king).hasMoved() &&
           board.getTile(rook) != null &&
           !board.getTile(rook).hasMoved() &&
           board.getTile(e1) == null &&
           board.getTile(e2) == null
        )
        { if(!doIt) {return true;}}
        else { return false; }
        board.movePiece(king,e2);
        board.movePiece(rook,e1);
        return true;
    }

    public boolean checkForMate(){
        return false;
    }
    public static void main(String[] args){
        ChessEngine e = new ChessEngine();
        //e.setupGame();
        //e.getBoard().displayCoords();
        System.out.println("===================");
        //e.getBoard().display();

        //System.out.println("pdlqwdqwpdqdkqwkdwqkdqwdkqwod");
        //e.getBoard().display();
        int incTurn = 1;
        while(true){
            System.out.println("▒   TURN    : "+incTurn++);
            e.waitForMove( e.getWhitePlayer() );
            e.checkPassant( e.getBlackPlayer() );
            e.waitForMove( e.getBlackPlayer() );
            e.checkPassant( e.getWhitePlayer());
        }
        //System.out.println("next jkweqdqwedhjqwdoiwqdqd");
        //Scanner scan = new Scanner(System.in);
        //String next scan.nextLine();
        //System.out.println();

    }

    private void checkPassant(Player p) {
        for(Piece piece : piecesLeft){
            if((piece instanceof Pawn && ((Pawn) piece).checkPassant() && piece.getTeam().equals(p) )){
                Pawn pp = (Pawn) piece;
                pp.setPassant(false);
            }
        }
    }
}
