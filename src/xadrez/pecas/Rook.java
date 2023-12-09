package xadrez.pecas;

import tabuleiro.XadrezTabuleiro;
import xadrez.Color;
import xadrez.PecasXadrez;

public class Rook extends PecasXadrez {
    
    public Rook(XadrezTabuleiro tabuleiro, Color color) {
        super(tabuleiro, color);
    }

    @Override
    public String toString() {
        return "R";
    }
}
