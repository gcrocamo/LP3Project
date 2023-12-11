package xadrez.pecas;

import tabuleiro.XadrezTabuleiro;
import tabuleiro.Posicao;
import xadrez.PartidaXadrez;
import xadrez.PecasXadrez;
import xadrez.Color;

public class Pawn extends PecasXadrez {

	private PartidaXadrez partidaXadrez;
    public Pawn(XadrezTabuleiro tabuleiro, Color color, PartidaXadrez partidaXadrez) {
        super(tabuleiro, color);
        this.partidaXadrez = partidaXadrez;
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
			if (posicao.getLinha() == 3) {
				Posicao left = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().posicaoExiste(left) && existePecaInimiga(left) && getTabuleiro().pecas(left) == partidaXadrez.getEnPassantSuscetivel()) {
					mat[left.getLinha() - 1][left.getColuna()] = true;
				}
				Posicao right = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().posicaoExiste(right) && existePecaInimiga(right) && getTabuleiro().pecas(right) == partidaXadrez.getEnPassantSuscetivel()) {
					mat[right.getLinha() - 1][right.getColuna()] = true;
				}
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
            if (posicao.getLinha() == 4) {
				Posicao left = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().posicaoExiste(left) && existePecaInimiga(left) && getTabuleiro().pecas(left) == partidaXadrez.getEnPassantSuscetivel()) {
					mat[left.getLinha() + 1][left.getColuna()] = true;
				}
				Posicao right = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().posicaoExiste(right) && existePecaInimiga(right) && getTabuleiro().pecas(right) == partidaXadrez.getEnPassantSuscetivel()) {
					mat[right.getLinha() + 1][right.getColuna()] = true;
				}
			}	
        }
        return mat;
    }

    @Override
    public String toString() {
        return "P";
    }

}
