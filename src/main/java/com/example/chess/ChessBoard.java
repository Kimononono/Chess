package com.example.chess;

import com.example.chess.pieces.Pawn;

import java.util.ArrayList;
import java.util.HashMap;

// Each letter is the first []
public class ChessBoard {
    private Piece[][] board;
    private HashMap<Integer,Character> numChar = new HashMap<>();
    private String hh = "━━━━━";
    private String vv = "┃";
    private String h4 = "╋";
    private String v3 = "┣";
    private String v6 = "┫";
    private String tL = "┏";
    private String tH = "┳";
    private String tR = "┓";
    private String bL = "┗";
    private String bR = "┛";
    private String bH = "┻";
    public ChessBoard(){
        board = new Piece[8][8];
        numChar.put(0,'A'); numChar.put( 1, 'B'); numChar.put( 2,'C'); numChar.put( 3, 'D'); numChar.put( 4, 'E'); numChar.put( 5, 'F'); numChar.put( 6, 'G'); numChar.put( 7, 'H');
    }
    // sets *pos* to *piece* returns what it replaced
    public Piece setTile(int[] pos, Piece piece){
        Piece rPiece = board[pos[0]][pos[1]];
        board[pos[0]][pos[1]] = piece;
        return rPiece;
    }
    public Piece removePiece(int[] pos){
        Piece rPiece = board[pos[0]][pos[1]];
        board[pos[0]][pos[1]] = null;
        return rPiece;
    }
    public Piece movePiece(int[] from, int[] to){
        Piece piece = getTile(from);
        Piece captured = getTile(to);
        if(piece instanceof Pawn && ((Pawn) piece).isDoPassant() != null)
        {
            captured = getTile(((Pawn) piece).isDoPassant());
            setTile(((Pawn) piece).isDoPassant(), null);
            ((Pawn) piece).resetDoPassant();
        }
        setTile(to, piece);
        setTile(from, null);
        return captured;
    }
    public Piece getTile(int[] pos){
       // if(!isAllowed(pos)) return null;
        Piece rPiece = board[pos[0]][pos[1]];
        return rPiece;
    }
    public Piece[][] getBoard(){
        return board;
    }
    public boolean isAllowed(int[] pos){
        boolean isIt = true;
        if(pos[0]>7 || pos[0]<-1) isIt = false;
        if(pos[1]>7 || pos[1]<-1) isIt = false;
        return isIt;
    }
    public void display(){
        System.out.println("Display Called");
        //System.out.print("\033[H\033[2J");
        //System.out.flush();
        boolean whiteTile = true;
        //System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
        String letters = "▒         A     B     C     D     E     F     G     H    ";
        String[] roww = new String[]{"▒      "," 1  "+vv," 2  "+vv," 3  "+vv," 4  "+vv," 5  "+vv," 6  "+vv," 7  "+vv," 8  "+vv};
        System.out.println("▒");
        int rowwI = 0;
        System.out.print(roww[rowwI++]);
        for(int i=0;i<8;i++) System.out.print(h4+hh); System.out.println(h4); // first chess line ╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋━━━━━╋
        for(Piece[] col : board){
            System.out.print("▒  "+roww[rowwI++]); // 1 2 3 4 5 6 7 8..
            for(Piece piece : col) { // Body of .  . . . .
                if(piece != null) System.out.print(" "+piece+" "+vv);
                else {
                    if(whiteTile){
                        System.out.print("  ■  "+vv);

                    } else System.out.print("  □  "+vv);
                }
                whiteTile = !whiteTile;
            }
            whiteTile = !whiteTile;
            System.out.println(" "); // next
            System.out.print("▒      ");
            for(int i=0;i<8;i++) System.out.print(h4+hh);
            System.out.println(h4);
            //System.out.println("▒▒▒▒   |▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁|  ▒▒▒▒");

        }
        System.out.println(letters);
        //System.out.println("▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
        }

    public void displayMoves(ArrayList<int[]> possibleMoves){
        System.out.println("DisplayMoves called");
        boolean whiteTile = true;
        //System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
        String letters = "▒         A     B     C     D     E     F     G     H    ";
        String[] roww = new String[]{"     "," 1  "+vv," 2  "+vv," 3  "+vv," 4  "+vv," 5  "+vv," 6  "+vv," 7  "+vv," 8  "+vv};
        int rowwInc = 0;
        System.out.print(roww[rowwInc++]);
        System.out.println();
        System.out.print("▒      ");
        for(int i=0;i<8;i++) System.out.print(h4+hh);
        System.out.println(h4);
        LOOP1: for(int let=0;let<8;let++){
            System.out.print("▒  "+roww[rowwInc++]); // 1 2 3 4 5 6 7 8..
            LOOP2: for(int num=0;num<8;num++) { // Body of .  . . . .
                Piece piece = board[let][num];
                int[] iPos = new int[]{let,num};
                for(int[] tPos : possibleMoves){
                    if(iPos[0] == tPos[0] && iPos[1] == tPos[1]) {
                        if(piece != null) System.out.print("▷"+piece+"◁"+vv);
                        else {
                            if(whiteTile){
                                System.out.print("  ╳  "+vv);

                            } else System.out.print("  ╳  "+vv);

                        }
                        whiteTile = !whiteTile;
                        continue LOOP2;
                    }
                }
                if(piece != null) System.out.print(" "+piece+" "+vv);
                else {
                    if(whiteTile){
                        System.out.print("  ■  "+vv);

                    } else System.out.print("  □  "+vv);
                }
                whiteTile = !whiteTile;
            }
            whiteTile = !whiteTile;
            System.out.println(" "); // next
            System.out.print("▒      ");
            //for(int i=0;i<8;i++) System.out.print("┿  -  ");
              for(int i=0;i<8;i++) System.out.print(h4+hh);
            System.out.println(h4);
            //System.out.println("▒▒▒▒   |▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁|  ▒▒▒▒");

        }
        System.out.println(letters);
        //System.out.println("▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
    }

    public void displayCoords(){
        for(int let = 0;let<8;let++){

            for(int num = 0; num<8;num++){
                if(board[let][num] != null){
                    System.out.println(board[let][num]+" at: "+"["+let+" ("+numChar.get(let)+")"+"]"+"["+num+"]");
                }
            }
        }
    }

}
