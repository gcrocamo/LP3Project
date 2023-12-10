package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.XadrezTabuleiro;
import tabuleiro.Peca;
import tabuleiro.Posicao;
import xadrez.pecas.Rook;
import xadrez.pecas.King;

public class PartidaXadrez {

        private XadrezTabuleiro tabuleiro;
        private int turno;
        private Color jogadorAtual;
        private boolean xeque;
        private boolean xequeMate;
        private List<Peca> pecasNoTabuleiro = new ArrayList<>();
    	private List<Peca> pecasCapturadas = new ArrayList<>();

        public PartidaXadrez() {
                tabuleiro = new XadrezTabuleiro(8, 8);
                turno = 1;
                jogadorAtual = Color.WHITE;
                posicaoInicial();
        }
        
        public int getTurno() {
        	return turno;
        }
        
        public Color getJogadorAtual() {
        	return jogadorAtual;
        }
        
        public boolean getXeque() {
    		return xeque;
    	}

        public boolean getXequeMate() {
    		return xequeMate;
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

        public boolean[][] movimentosPossiveis(PosicaoXadrez origemPosicao) {
    		Posicao posicao = origemPosicao.paraPosicao();
    		validarPosicaoOrigem(posicao);
    		return tabuleiro.pecas(posicao).movimentosPossiveis();
    	}
        
        public PecasXadrez realizarMovimentoXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoAlvo) {
            Posicao origem = posicaoOrigem.paraPosicao();
            Posicao alvo = posicaoAlvo.paraPosicao();
            validarPosicaoOrigem(origem);
            validarPosicaoAlvo(origem, alvo);
            PecasXadrez pecaCapturada = fazerMovimento(origem, alvo);
            if (testeXeque(jogadorAtual)) {
    			desfazerMovimento(origem, alvo, pecaCapturada);
    			throw new ExcecaoXadrez("Nao pode colocar-se em xeque");
    		}
    		xeque = (testeXeque(oponente(jogadorAtual))) ? true : false;
    		if (testeXequeMate(oponente(jogadorAtual))) {
    			xequeMate = true;
    		}
    		else {
    			proximoTurno();
    		}
            return pecaCapturada;
        }

        private PecasXadrez fazerMovimento(Posicao origem, Posicao alvo) {
            PecasXadrez peca = (PecasXadrez)tabuleiro.removerPeca(origem);
            PecasXadrez pecaCapturada = (PecasXadrez)tabuleiro.removerPeca(alvo);
            tabuleiro.inserirPeca(peca, alvo);
            if (pecaCapturada != null) {
    			pecasNoTabuleiro.remove(pecaCapturada);
    			pecasCapturadas.add(pecaCapturada);
    		}
            return pecaCapturada;
        }
        
        private void desfazerMovimento(Posicao source, Posicao alvo, Peca pecaCapturada) {
    		Peca p = tabuleiro.removerPeca(alvo);
    		tabuleiro.inserirPeca(p, source);

    		if (pecaCapturada != null) {
    			tabuleiro.inserirPeca(pecaCapturada, alvo);
    			pecasCapturadas.remove(pecaCapturada);
    			pecasNoTabuleiro.add(pecaCapturada);
    		}
    	}
        
        private void validarPosicaoOrigem(Posicao posicao) {
            if (!tabuleiro.existePeca(posicao)) {
                throw new ExcecaoXadrez("Nao ha peça na posicao de origem");
            }
            if (jogadorAtual != ((PecasXadrez)tabuleiro.pecas(posicao)).getColor()) {
    			throw new ExcecaoXadrez("A peca escolhida nao e sua");
    		}
            if (!tabuleiro.pecas(posicao).existeMovimentoPossivel()) {
    			throw new ExcecaoXadrez("Nao existem movimentos possiveis para esta peca");
    		}
        }
        
        private void validarPosicaoAlvo(Posicao origem, Posicao alvo) {
        	if (!tabuleiro.pecas(origem).movimentoPossivel(alvo)) {
                throw new ExcecaoXadrez("A peca escolhida não pode se mover para a posicao de alvo");
            }
        }
        
        private void proximoTurno() {
    		turno++;
    		jogadorAtual = (jogadorAtual == Color.WHITE) ? Color.BLACK : Color.WHITE;
    	}
        
        private Color oponente(Color color) {
    		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    	}

    	private PecasXadrez king(Color color) {
    		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecasXadrez)x).getColor() == color).collect(Collectors.toList());
    		for (Peca p : list) {
    			if (p instanceof King) {
    				return (PecasXadrez)p;
    			}
    		}
    		throw new IllegalStateException("Nao existe um rei " + color + " no tabuleiro");
    	}

    	private boolean testeXeque(Color color) {
    		Posicao kingPosicao = king(color).getPosicaoXadrez().paraPosicao();
    		List<Peca> oponentePecas = pecasNoTabuleiro.stream().filter(x -> ((PecasXadrez)x).getColor() == oponente(color)).collect(Collectors.toList());
    		for (Peca p : oponentePecas) {
    			boolean[][] mat = p.movimentosPossiveis();
    			if (mat[kingPosicao.getLinha()][kingPosicao.getColuna()]) {
    				return true;
    			}
    		}
    		return false;
    	}
    	
        
        private void inserirNovaPeca(char coluna, int linha, PecasXadrez peca) {
        	tabuleiro.inserirPeca(peca, new PosicaoXadrez(coluna, linha).paraPosicao());
        	pecasNoTabuleiro.add(peca);
        }

        private boolean testeXequeMate(Color color) {
    		if (!testeXeque(color)) {
    			return false;
    		}
    		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecasXadrez)x).getColor() == color).collect(Collectors.toList());
    		for (Peca p : list) {
    			boolean[][] mat = p.movimentosPossiveis();
    			for (int i=0; i<tabuleiro.getLinhas(); i++) {
    				for (int j=0; j<tabuleiro.getColunas(); j++) {
    					if (mat[i][j]) {
    						Posicao origem = ((PecasXadrez)p).getPosicaoXadrez().paraPosicao();
    						Posicao alvo = new Posicao(i, j);
    						Peca pecaCapturada = fazerMovimento(origem, alvo);
    						boolean testeXeque = testeXeque(color);
    						desfazerMovimento(origem, alvo, pecaCapturada);
    						if (!testeXeque) {
    							return false;
    						}
    					}
    				}
    			}
    		}
    		return true;
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