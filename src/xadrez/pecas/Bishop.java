package xadrez.pecas;

import tabuleiro.XadrezTabuleiro;
import tabuleiro.Posicao;
import xadrez.PecasXadrez;
import xadrez.Color;

public class Bishop extends PecasXadrez {

    public Bishop(XadrezTabuleiro tabuleiro, Color color) {
        super(tabuleiro, color);
    }

    @Override
    public String toString() {
        return "B";
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        // NO
        p.definirValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existePeca(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
            p.definirValores(p.getLinha() - 1, p.getColuna() - 1);
        }
        if (getTabuleiro().posicaoExiste(p) && existePecaInimiga(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // NE
        p.definirValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existePeca(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
            p.definirValores(p.getLinha() - 1, p.getColuna() + 1);
        }
        if (getTabuleiro().posicaoExiste(p) && existePecaInimiga(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // SE
        p.definirValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existePeca(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
            p.definirValores(p.getLinha() + 1, p.getColuna() + 1);
        }
        if (getTabuleiro().posicaoExiste(p) && existePecaInimiga(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // SO
        p.definirValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existePeca(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
            p.definirValores(p.getLinha() + 1, p.getColuna() - 1);
        }
        if (getTabuleiro().posicaoExiste(p) && existePecaInimiga(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        return mat;
    }
}
