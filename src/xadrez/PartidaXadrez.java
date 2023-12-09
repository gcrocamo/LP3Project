package xadrez;

import tabuleiro.XadrezTabuleiro;
import xadrez.pecas.Rook;
import xadrez.pecas.King;

public class PartidaXadrez {

        private XadrezTabuleiro tabuleiro;

        public PartidaXadrez() {
                tabuleiro = new XadrezTabuleiro(8, 8);
                posicaoInicial();
        }

        public PecasXadrez[][] getPecas(){
                PecasXadrez[][] mat = new PecasXadrez[tabuleiro.getLinhas()][tabuleiro.getLinhas()];
                for (int i=0; i<tabuleiro.getLinhas(); i++) {
                        for(int j=0; j<tabuleiro.getColunas(); j++) {
                                mat[i][j] = (PecasXadrez) tabuleiro.pecas(i, j);
                        }
                }
                return mat;
        }

  private void inserirNovaPeca(char coluna, int linha, PecasXadrez peca) {
    tabuleiro.inserirPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
  }

  private void posicaoInicial() {
                inserirNovaPeca('e', 8, new Rook(tabuleiro, Color.WHITE));
                inserirNovaPeca('e', 8, new King(tabuleiro, Color.BLACK));
                inserirNovaPeca('d', 4, new King(tabuleiro, Color.WHITE));
        }
}