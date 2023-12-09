package xadrez;

import tabuleiro.XadrezTabuleiro;
import tabuleiro.Peca;
import tabuleiro.Posicao;
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

        public PecasXadrez realizarMovimentoXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
            Posicao origem = posicaoOrigem.toPosicao();
            Posicao destino = posicaoDestino.toPosicao();
            validarPosicaoOrigem(origem);
            PecasXadrez pecaCapturada = fazerMovimento(origem, destino);
            return pecaCapturada;
        }

        private PecasXadrez fazerMovimento(Posicao origem, Posicao destino) {
            PecasXadrez peca = (PecasXadrez)tabuleiro.removerPeca(origem);
            PecasXadrez pecaCapturada = (PecasXadrez)tabuleiro.removerPeca(destino);
            tabuleiro.inserirPeca(peca, destino);
            return pecaCapturada;
        }
        
        private void validarPosicaoOrigem(Posicao posicao) {
            if (!tabuleiro.existePeca(posicao)) {
                throw new ExcecaoXadrez("Não há peça na posição de origem");
            }
        }

        private void inserirNovaPeca(char coluna, int linha, PecasXadrez peca) {
        	tabuleiro.inserirPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
        }

        private void posicaoInicial() {
        	inserirNovaPeca('c', 2, new Rook(tabuleiro, Color.WHITE));
        	inserirNovaPeca('d', 2, new Rook(tabuleiro, Color.WHITE));
        	inserirNovaPeca('e', 2, new Rook(tabuleiro, Color.WHITE));
        	inserirNovaPeca('e', 1, new Rook(tabuleiro, Color.WHITE));
        	inserirNovaPeca('d', 1, new King(tabuleiro, Color.WHITE));
        	inserirNovaPeca('c', 7, new Rook(tabuleiro, Color.BLACK));
        	inserirNovaPeca('c', 8, new Rook(tabuleiro, Color.BLACK));
        	inserirNovaPeca('d', 7, new Rook(tabuleiro, Color.BLACK));
        	inserirNovaPeca('e', 7, new Rook(tabuleiro, Color.BLACK));
        	inserirNovaPeca('e', 8, new Rook(tabuleiro, Color.BLACK));
        	inserirNovaPeca('d', 8, new King(tabuleiro, Color.BLACK));
        }
}