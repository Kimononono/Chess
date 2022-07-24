package com.example.chess.pieces;

import com.example.chess.ChessBoard;
import com.example.chess.Piece;
import com.example.chess.Player;

import java.util.ArrayList;

public class Knight implements Piece {
    private Player team;
    private ChessBoard board;
    private ArrayList<int[]> coveredTiles;
    private int tMulti;
    private boolean hasMoved;
    private double value;

    public Knight(Player team, double value){
        this.team = team;
        tMulti = -1;
        if(team.isWhite()) tMulti = 1;
        hasMoved = false;
        this.value = value;
    }

    @Override
    public Player getTeam(){
        return team;
    }
    @Override
    public double getValue() { return value; }
    @Override
    public ArrayList<int[]> cycleMoves(int[] cPos, ChessBoard board) {
        ArrayList<int[]> possibleMoves = new ArrayList<>();
        int[] topLeftAttack= new int[]{cPos[0]-(2*tMulti), cPos[1]-tMulti};
        int[] topRightAttack= new int[]{cPos[0]-(2*tMulti), cPos[1]+tMulti};
        int[] rightTopAttack= new int[]{cPos[0]-(tMulti), cPos[1]+(2*tMulti)};
        int[] rightBotAttack= new int[]{cPos[0]+(tMulti), cPos[1]+(2*tMulti)};
        int[] leftTopAttack= new int[]{cPos[0]-(tMulti), cPos[1]-(2*tMulti)};
        int[] leftBotAttack= new int[]{cPos[0]+(tMulti), cPos[1]-(2*tMulti)};
        int[] botLeftAttack= new int[]{cPos[0]+(2*tMulti), cPos[1]-tMulti};
        int[] botRightAttack= new int[]{cPos[0]+(2*tMulti), cPos[1]+tMulti};

        if(isAllowed(topLeftAttack) && (board.getTile(topLeftAttack) == null || board.getTile(topLeftAttack).getTeam() != team)) possibleMoves.add(topLeftAttack);
        if(isAllowed(topRightAttack) && (board.getTile(topRightAttack) == null || board.getTile(topRightAttack).getTeam() != team)) possibleMoves.add(topRightAttack);
        if(isAllowed(rightTopAttack) && (board.getTile(rightTopAttack) == null || board.getTile(rightTopAttack).getTeam() != team)) possibleMoves.add(rightTopAttack);
        if(isAllowed(rightBotAttack) && (board.getTile(rightBotAttack) == null || board.getTile(rightBotAttack).getTeam() != team)) possibleMoves.add(rightBotAttack);
        if(isAllowed(leftTopAttack) && (board.getTile(leftTopAttack) == null || board.getTile(leftTopAttack).getTeam() != team)) possibleMoves.add(leftTopAttack);
        if(isAllowed(leftBotAttack) && (board.getTile(leftBotAttack) == null || board.getTile(leftBotAttack).getTeam() != team)) possibleMoves.add(leftBotAttack);
        if(isAllowed(botLeftAttack) && (board.getTile(botLeftAttack) == null || board.getTile(botLeftAttack).getTeam() != team)) possibleMoves.add(botLeftAttack);
        if(isAllowed(botRightAttack) && (board.getTile(botRightAttack) == null || board.getTile(botRightAttack).getTeam() != team)) possibleMoves.add(botRightAttack);

        coveredTiles = possibleMoves;
        return possibleMoves;
    }
    @Override
    public String toString(){
        if(tMulti>0){
            return "▪9▪";
        } else return "▫9▫";
    }
    public void setCovered(int[] pos) {
        coveredTiles.clear();
        coveredTiles.add(new int[]{pos[0]-tMulti, pos[1]-tMulti});
        coveredTiles.add(new int[]{pos[0]-tMulti, pos[1]+tMulti});
    }
    public boolean isAllowed(int[] pos){
        boolean isIt = true;
        if(pos[0]>7 || pos[0]<0) isIt = false;
        if(pos[1]>7 || pos[1]<0) isIt = false;
        return isIt;
    }
    @Override
    public boolean hasMoved(){
        return hasMoved;
    }
    public void update(){

    }
}
