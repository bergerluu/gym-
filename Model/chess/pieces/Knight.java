package Model.chess.pieces;

import Model.chess.ChessPiece;
import Model.chess.Color;
import Model.boardgame.Board;
import Model.boardgame.Position;

public class Knight extends ChessPiece {

    public Knight(Board board, Color color) {
        super(board, color);
    }
    
    @Override
    public String toString(){
        return "N";
    }

    private boolean canMove(Position position){
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return p == null || p.getColor() != getColor();
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);
        
        //ABOVE
        p.setValues(position.getRow() - 1, position.getColumn() - 2);  
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        //BELOW
        p.setValues(position.getRow() - 2, position.getColumn() - 1);  
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        //LEFT
        p.setValues(position.getRow() - 2, position.getColumn() + 1);  
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        //RIGHT
        p.setValues(position.getRow() - 1, position.getColumn() + 2);  
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        //NW
        p.setValues(position.getRow() + 1, position.getColumn() + 2);  
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        //NE
        p.setValues(position.getRow() + 2, position.getColumn() + 1);  
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        //SW
        p.setValues(position.getRow() + 2, position.getColumn() - 1);  
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        //SE
        p.setValues(position.getRow() + 1, position.getColumn() - 2);  
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        return mat;
    }
}
