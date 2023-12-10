package xadrez.pecas;

import tabuleiro.XadrezTabuleiro;
import tabuleiro.Posicao;
import xadrez.Color;
import xadrez.PecasXadrez;
import xadrez.PartidaXadrez;

public class King extends PecasXadrez {

	private PartidaXadrez partidaXadrez;
    public King(XadrezTabuleiro tabuleiro, Color color, PartidaXadrez partidaXadrez) {
        super(tabuleiro, color);
        this.partidaXadrez = partidaXadrez;
    }

    @Override
    public String toString() {
        return "K";
    }
    
    private boolean podeMover(Posicao posicao) {
        PecasXadrez peca = (PecasXadrez) getTabuleiro().pecas(posicao);
        return peca == null || peca.getColor() != getColor();
    }
    
    private boolean testeTorreRoque(Posicao posicao) {
		PecasXadrez pecas = (PecasXadrez)getTabuleiro().pecas(posicao);
		return pecas != null && pecas instanceof Rook && pecas.getColor() == getColor() && pecas.getNumeroJogadas() == 0;
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
	     
	     // roque
	     if (getNumeroJogadas() == 0 && !partidaXadrez.getXeque()) {
	         // #movimentoespecial roque lado do rei
	         Posicao posT1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
	         if (testeTorreRoque(posT1)) {
	             Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
	             Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
	             if (getTabuleiro().pecas(p1) == null && getTabuleiro().pecas(p2) == null) {
	                 mat[posicao.getLinha()][posicao.getColuna() + 2] = true;
	             }
	         }
	         // #movimentoespecial roque lado da rainha
	         Posicao posT2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 4);
	         if (testeTorreRoque(posT2)) {
	             Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
	             Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
	             Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
	             if (getTabuleiro().pecas(p1) == null && getTabuleiro().pecas(p2) == null && getTabuleiro().pecas(p3) == null) {
	                 mat[posicao.getLinha()][posicao.getColuna() - 2] = true;
	             }
	         }
	     }


        return mat;
    }

}
