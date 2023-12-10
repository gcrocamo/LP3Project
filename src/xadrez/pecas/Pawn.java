package xadrez.pecas;

import tabuleiro.XadrezTabuleiro;
import tabuleiro.Posicao;
import xadrez.PecasXadrez;
import xadrez.Color;

public class Pawn extends PecasXadrez {

    public Pawn(XadrezTabuleiro tabuleiro, Color color) {
        super(tabuleiro, color);
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        Posicao p = new Posicao(0, 0);

        if (getColor() == Color.WHITE) {
            p.definirValores(posicao.getLinha() - 1, posicao.getColuna());
            if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existePeca(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            p.definirValores(posicao.getLinha() - 2, posicao.getColuna());
            Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
            if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existePeca(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().existePeca(p2) && getNumeroJogadas() == 0) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            p.definirValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
            if (getTabuleiro().posicaoExiste(p) && existePecaInimiga(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }            
            p.definirValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
            if (getTabuleiro().posicaoExiste(p) && existePecaInimiga(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }            
        }
        else {
            p.definirValores(posicao.getLinha() + 1, posicao.getColuna());
            if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existePeca(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            p.definirValores(posicao.getLinha() + 2, posicao.getColuna());
            Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
            if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existePeca(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().existePeca(p2) && getNumeroJogadas() == 0) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            p.definirValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
            if (getTabuleiro().posicaoExiste(p) && existePecaInimiga(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }            
            p.definirValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
            if (getTabuleiro().posicaoExiste(p) && existePecaInimiga(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }    
        }
        return mat;
    }

    @Override
    public String toString() {
        return "P";
    }

}
