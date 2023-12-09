package tabuleiro;

public class XadrezTabuleiro {

    private int linhas, colunas;
    private Peca[][] pecas;

    public XadrezTabuleiro(int linhas, int colunas) {
        if (linhas < 1 || colunas < 1) {
            throw new ExcecaoTabuleiro("O tabuleiro deve ter pelo menos 1 linha e 1 coluna");
        }
        this.linhas = linhas;
        this.colunas = colunas;
        pecas = new Peca[linhas][colunas];
    }

    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }

    public Peca pecas(int linha, int coluna) {
        if (!posicaoExiste(linha, coluna)) {
            throw new ExcecaoTabuleiro("A posição não está no tabuleiro");
        }
        return pecas[linha][coluna];
    }

    public Peca pecas(Posicao posicao) {
        if (!posicaoExiste(posicao)) {
            throw new ExcecaoTabuleiro("A posição não está no tabuleiro");
        }
        return pecas[posicao.getLinha()][posicao.getColuna()];
    }

    public void inserirPeca(Peca peca, Posicao posicao) {
        if (existePeca(posicao)) {
            throw new ExcecaoTabuleiro("Já existe uma peça nesta posição: " + posicao);
        }
        pecas[posicao.getLinha()][posicao.getColuna()] = peca;
        peca.setPosicao(posicao);
    }

    private boolean posicaoExiste(int linha, int coluna) {
        return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
    }

    public boolean posicaoExiste(Posicao posicao) {
        return posicaoExiste(posicao.getLinha(), posicao.getColuna());
    }

    public Peca removerPeca(Posicao posicao) {
        if (!posicaoExiste(posicao)) {
            throw new ExcecaoTabuleiro("Posição não está no tabuleiro");
        }
        if (pecas(posicao) == null) {
            return null;
        }
        Peca aux = pecas(posicao);
        aux.setPosicao(null);
        pecas[posicao.getLinha()][posicao.getColuna()] = null;
        return aux;
    }

    
    public boolean existePeca(Posicao posicao) {
        if (!posicaoExiste(posicao)) {
            throw new ExcecaoTabuleiro("A posição não está no tabuleiro");
        }
        return pecas(posicao) != null;
    }
}
