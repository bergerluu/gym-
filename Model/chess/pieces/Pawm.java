package Model.chess.pieces;

import Model.chess.ChessPiece;
import Model.chess.Color;
import Model.boardgame.Board;
import Model.boardgame.Position;

/**
 * Pawm
 */
public class Pawm extends ChessPiece {

    public Pawm(Board board, Color color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);

        if (getColor() == Color.WHITE) {
            // Logica do peão andar apenas 1 casa
            p.setValues(position.getRow() - 1, position.getColumn());
            if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            // Logica do peão andar 1 ou 2 casas caso for a primeira jogada dele
            p.setValues(position.getRow() - 2, position.getColumn());
            Position p2 = new Position(position.getRow() - 1, position.getColumn());
            if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            // Logica para o peão capturar peças na diagonal esquerda
            p.setValues(position.getRow() - 1, position.getColumn() - 1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            // Logica para o peão capturar peças na diagonal direita
            p.setValues(position.getRow() - 1, position.getColumn() + 1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }
       }
       else{
        p.setValues(position.getRow() + 1, position.getColumn());
        if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        // Logica do peão andar 1 ou 2 casas caso for a primeira jogada dele
        p.setValues(position.getRow() + 2, position.getColumn());
        Position p2 = new Position(position.getRow() + 1, position.getColumn());
        if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        // Logica para o peão capturar peças na diagonal esquerda
        p.setValues(position.getRow() + 1, position.getColumn() - 1);
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        // Logica para o peão capturar peças na diagonal direita
        p.setValues(position.getRow() + 1, position.getColumn() + 1);
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
       }

        return mat;
    }

    @Override
    public String toString() {
        return "P";
    }

    
    
}