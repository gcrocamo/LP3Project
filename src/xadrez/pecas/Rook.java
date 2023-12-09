package xadrez.pecas;

import tabuleiro.XadrezTabuleiro;
import xadrez.Color;
import xadrez.PecasXadrez;
import tabuleiro.Posicao;

public class Rook extends PecasXadrez {
    
    public Rook(XadrezTabuleiro tabuleiro, Color color) {
        super(tabuleiro, color);
    }

    @Override
    public String toString() {
        return "R";
    }
    
    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        Posicao p = new Posicao(0, 0);

     // acima
        p.definirValores(posicao.getLinha() - 1, posicao.getColuna());
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existePeca(p)) {
         	mat[p.getLinha()][p.getColuna()] = true;
         	p.setLinha(p.getLinha() - 1);
     	}
     	if (getTabuleiro().posicaoExiste(p) && existePecaInimiga(p)) {
         	mat[p.getLinha()][p.getColuna()] = true;
     	}

     	// esquerda
     	p.definirValores(posicao.getLinha(), posicao.getColuna() - 1);
     	while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existePeca(p)) {
         	mat[p.getLinha()][p.getColuna()] = true;
         	p.setColuna(p.getColuna() - 1);
     	}
     	if (getTabuleiro().posicaoExiste(p) && existePecaInimiga(p)) {
        	 mat[p.getLinha()][p.getColuna()] = true;
     }	

     	// direita
     	p.definirValores(posicao.getLinha(), posicao.getColuna() + 1);
     	while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existePeca(p)) {
        	mat[p.getLinha()][p.getColuna()] = true;
        	p.setColuna(p.getColuna() + 1);
     	}
     	if (getTabuleiro().posicaoExiste(p) && existePecaInimiga(p)) {
     		mat[p.getLinha()][p.getColuna()] = true;
     	}

     	// abaixo
     	p.definirValores(posicao.getLinha() + 1, posicao.getColuna());
     	while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existePeca(p)) {
     		mat[p.getLinha()][p.getColuna()] = true;
     		p.setLinha(p.getLinha() + 1);
     	}
     	if (getTabuleiro().posicaoExiste(p) && existePecaInimiga(p)) {
     		mat[p.getLinha()][p.getColuna()] = true;
     	}

        return mat;
    }
}
