package tabuleiro;

public abstract class Peca {

    protected Posicao posicao;
    private XadrezTabuleiro tabuleiro;

    public Peca(XadrezTabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    protected XadrezTabuleiro getTabuleiro() {
        return tabuleiro;
    }
    
    public abstract boolean[][] movimentosPossiveis();

    public boolean movimentoPossivel(Posicao posicao) {
        return movimentosPossiveis()[posicao.getLinha()][posicao.getColuna()];
    }

    public boolean existeMovimentoPossivel() {
        boolean[][] mat = movimentosPossiveis();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    
    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }
 
}
