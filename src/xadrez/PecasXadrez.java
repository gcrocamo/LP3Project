package xadrez;

import tabuleiro.XadrezTabuleiro;
import tabuleiro.Peca;
import tabuleiro.Posicao;

public abstract class PecasXadrez extends Peca {

    private Color color;
    private int numeroJogadas;

    public PecasXadrez(XadrezTabuleiro tabuleiro, Color color) {
        super(tabuleiro);
        this.color = color;
    }
    
    public PosicaoXadrez getPosicaoXadrez() {
		return PosicaoXadrez.daPosicao(posicao);
	}

    public Color getColor() {
        return color;
    }
    
    public int getNumeroJogadas() {
		return numeroJogadas;
	}

	public void aumentarNumeroJogadas() {
		numeroJogadas++;
	}

	public void diminuirNumeroJogadas() {
		numeroJogadas--;
	}

    
    protected boolean existePecaInimiga(Posicao posicao) {
		PecasXadrez p = (PecasXadrez)getTabuleiro().pecas(posicao);
		return p != null && p.getColor() != color;
	}
}
