package xadrez.pecas;

import tabuleiro.XadrezTabuleiro;
import tabuleiro.Posicao;
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
    
    private boolean podeMover(Posicao posicao) {
        PecasXadrez peca = (PecasXadrez) getTabuleiro().pecas(posicao);
        return peca == null || peca.getColor() != getColor();
    }
    
    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        Posicao p = new Posicao(0, 0);

	     // acima
	     p.definirValores(posicao.getLinha() - 1, posicao.getColuna());
	     if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
	         mat[p.getLinha()][p.getColuna()] = true;
	     }
	
	     // abaixo
	     p.definirValores(posicao.getLinha() + 1, posicao.getColuna());
	     if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
	         mat[p.getLinha()][p.getColuna()] = true;
	     }

	     // esquerda
	     p.definirValores(posicao.getLinha(), posicao.getColuna() - 1);
	     if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
	         mat[p.getLinha()][p.getColuna()] = true;
	     }
	
	     // direita
	     p.definirValores(posicao.getLinha(), posicao.getColuna() + 1);
	     if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
	         mat[p.getLinha()][p.getColuna()] = true;
	     }
	
	     // noroeste
	     p.definirValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
	     if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
	         mat[p.getLinha()][p.getColuna()] = true;
	     }
	
	     // nordeste
	     p.definirValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
	     if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
	         mat[p.getLinha()][p.getColuna()] = true;
	     }
	
	     // sudoeste
	     p.definirValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
	     if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
	         mat[p.getLinha()][p.getColuna()] = true;
	     }
	
	     // sudeste
	     p.definirValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
	     if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
	         mat[p.getLinha()][p.getColuna()] = true;
	     }

        return mat;
    }

}
