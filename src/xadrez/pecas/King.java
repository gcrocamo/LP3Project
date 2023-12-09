package xadrez.pecas;

import tabuleiro.XadrezTabuleiro;
import xadrez.Color;
import xadrez.PecasXadrez;

public class King extends PecasXadrez {

    public King(XadrezTabuleiro tabuleiro, Color color) {
        super(tabuleiro, color);
    }

    @Override
    public String toString() {
        return "K";
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        return mat;
    }

}
