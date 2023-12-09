package tabuleiro;

public class Peca {

    protected Posicao posicao;
    private XadrezTabuleiro tabuleiro;

    public Peca(XadrezTabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    protected XadrezTabuleiro getTabuleiro() {
        return tabuleiro;
    }
    
    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }
 
}
