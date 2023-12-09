package xadrez;

import tabuleiro.XadrezTabuleiro;
import tabuleiro.Peca;
//import tabuleiro.Posicao;

public abstract class PecasXadrez extends Peca {

    private Color color;

    public PecasXadrez(XadrezTabuleiro tabuleiro, Color color) {
        super(tabuleiro);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
