package Model.chess;

import java.util.logging.Logger;

import Model.boardgame.BoardException;

public class ChessException extends BoardException {
    private static final Logger logger = Logger.getLogger(ChessException.class.getName());

    public ChessException(String msg){
        super(msg);        
        logger.severe("ChessException: " + msg);
    }
}
