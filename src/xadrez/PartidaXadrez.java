package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.XadrezTabuleiro;
import tabuleiro.Peca;
import tabuleiro.Posicao;
import xadrez.pecas.Rook;
import xadrez.pecas.King;
import xadrez.pecas.Bishop;
import xadrez.pecas.Pawn;
import xadrez.pecas.Knight;
import xadrez.pecas.Queen;

public class PartidaXadrez {

        private XadrezTabuleiro tabuleiro;
        private int turno;
        private Color jogadorAtual;
        private boolean xeque;
        private boolean xequeMate;
        private PecasXadrez promovido;
        private PecasXadrez enPassantSuscetivel;
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
        
        public PecasXadrez getEnPassantSuscetivel() {
    		return enPassantSuscetivel;
    	}
        
        public PecasXadrez getPromovido() {
        	return promovido;
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
    		PecasXadrez pecaMovida = (PecasXadrez)tabuleiro.pecas(alvo);
    		
    		// promocao
    		promovido = null;
    		if (pecaMovida instanceof Pawn) {
    			if ((pecaMovida.getColor() == Color.WHITE && alvo.getLinha() == 0) || (pecaMovida.getColor() == Color.BLACK && alvo.getLinha() == 7)) {
    				promovido = (PecasXadrez)tabuleiro.pecas(alvo);
    				promovido = substituirPecaPromovida("Q");
    			}
    		}
    		
            xeque = (testeXeque(oponente(jogadorAtual))) ? true : false;
    		if (testeXequeMate(oponente(jogadorAtual))) {
    			xequeMate = true;
    		}
    		else {
    			proximoTurno();
    		}
    		// en passant
    		if (pecaMovida instanceof Pawn && (alvo.getLinha() == origem.getLinha() - 2 || alvo.getLinha() == origem.getLinha() + 2)) {
    			enPassantSuscetivel = pecaMovida;
    		}
    		else {
    			enPassantSuscetivel = null;
    		}
            return pecaCapturada;
        }
        
        public PecasXadrez substituirPecaPromovida(String letra) {
    		if (promovido == null) {
    			throw new IllegalStateException("Nao tem peca para ser promovida");
    		}
    		if (!letra.equals("B") && !letra.equals("N") && !letra.equals("R") && !letra.equals("Q") && !letra.equals("b") && !letra.equals("n") && !letra.equals("r") && !letra.equals("q") && !letra.equals("r") && !letra.equals("K") ) {
    			return promovido;
    		}

    		Posicao pos = promovido.getPosicaoXadrez().paraPosicao();
    		Peca peca = tabuleiro.removerPeca(pos);
    		pecasNoTabuleiro.remove(peca);

    		PecasXadrez novaPeca = novaPeca(letra, promovido.getColor());
    		tabuleiro.inserirPeca(novaPeca, pos);
    		pecasNoTabuleiro.add(novaPeca);	

    		return novaPeca;
    	}

    	private PecasXadrez novaPeca(String letra, Color color) {
    		if (letra.equals("b")) return new Bishop(tabuleiro, color);
    		if (letra.equals("n")) return new Knight(tabuleiro, color);
    		if (letra.equals("q")) return new Queen(tabuleiro, color);
    		if (letra.equals("B")) return new Bishop(tabuleiro, color);
    		if (letra.equals("N")) return new Knight(tabuleiro, color);
    		if (letra.equals("Q")) return new Queen(tabuleiro, color);
    		if (letra.equals("r")) return new Rook(tabuleiro, color);
    		if (letra.equals("K")) return new Knight(tabuleiro, color);
    		return new Rook(tabuleiro, color);
    	}

        private PecasXadrez fazerMovimento(Posicao origem, Posicao alvo) {
            PecasXadrez peca = (PecasXadrez)tabuleiro.removerPeca(origem);
            peca.aumentarNumeroJogadas();
            PecasXadrez pecaCapturada = (PecasXadrez)tabuleiro.removerPeca(alvo);
            tabuleiro.inserirPeca(peca, alvo);
            if (pecaCapturada != null) {
    			pecasNoTabuleiro.remove(pecaCapturada);
    			pecasCapturadas.add(pecaCapturada);
    		}
             // roque lado do rei
            if (peca instanceof King && alvo.getColuna() == origem.getColuna() + 2) {
                Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
                Posicao alvoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
                PecasXadrez torre = (PecasXadrez)tabuleiro.removerPeca(origemT);
                tabuleiro.inserirPeca(torre, alvoT);
                torre.aumentarNumeroJogadas();
            }

             // roque lado da rainha
            if (peca instanceof King && alvo.getColuna() == origem.getColuna() - 2) {
                Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
                Posicao alvoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
                PecasXadrez torre = (PecasXadrez)tabuleiro.removerPeca(origemT);
                tabuleiro.inserirPeca(torre, alvoT);
                torre.aumentarNumeroJogadas();
            }
            
            // en passant
    		if (peca instanceof Pawn) {
    			if (origem.getColuna() != alvo.getColuna() && pecaCapturada == null) {
    				Posicao pawnPosicao;
    				if (peca.getColor() == Color.WHITE) {
    					pawnPosicao = new Posicao(alvo.getLinha() + 1, alvo.getColuna());
    				}
    				else {
    					pawnPosicao = new Posicao(alvo.getLinha() - 1, alvo.getColuna());
    				}
    				pecaCapturada = (PecasXadrez)tabuleiro.removerPeca(pawnPosicao);
    				pecasCapturadas.add(pecaCapturada);
    				pecasNoTabuleiro.remove(pecaCapturada);
    			}
    		}
            
            return pecaCapturada;
        }
        
        private void desfazerMovimento(Posicao origem, Posicao alvo, Peca pecaCapturada) {
    		PecasXadrez peca =(PecasXadrez)tabuleiro.removerPeca(alvo);
    		peca.diminuirNumeroJogadas();
    		tabuleiro.inserirPeca(peca, origem);

    		if (pecaCapturada != null) {
    			tabuleiro.inserirPeca(pecaCapturada, alvo);
    			pecasCapturadas.remove(pecaCapturada);
    			pecasNoTabuleiro.add(pecaCapturada);
    		}
    		//  roque lado do rei
    		if (peca instanceof King && alvo.getColuna() == origem.getColuna() + 2) {
    		    Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
    		    Posicao alvoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
    		    PecasXadrez torre = (PecasXadrez)tabuleiro.removerPeca(alvoT);
    		    tabuleiro.inserirPeca(torre, origemT);
    		    torre.diminuirNumeroJogadas();
    		}

    		//  roque lado da rainha
    		if (peca instanceof King && alvo.getColuna() == origem.getColuna() - 2) {
    		    Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
    		    Posicao alvoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
    		    PecasXadrez torre = (PecasXadrez)tabuleiro.removerPeca(alvoT);
    		    tabuleiro.inserirPeca(torre, origemT);
    		    torre.diminuirNumeroJogadas();
    		}
    		
    		// en passant
    		if (peca instanceof Pawn) {
    			if (origem.getColuna() != alvo.getColuna() && pecaCapturada == enPassantSuscetivel) {
    				PecasXadrez pawn = (PecasXadrez)tabuleiro.removerPeca(alvo);
    				Posicao pawnPosicao;
    				if (peca.getColor() == Color.WHITE) {
    					pawnPosicao = new Posicao(3, alvo.getColuna());
    				}
    				else {
    					pawnPosicao = new Posicao(4, alvo.getColuna());
    				}
    				tabuleiro.inserirPeca(pawn, pawnPosicao);
    			}
    		}
    	}
        
        private void validarPosicaoOrigem(Posicao posicao) {
            if (!tabuleiro.existePeca(posicao)) {
                throw new ExcecaoXadrez("Nao ha peca na posicao de origem");
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
        	inserirNovaPeca('a', 1, new Rook(tabuleiro, Color.WHITE));
            inserirNovaPeca('b', 1, new Knight(tabuleiro, Color.WHITE));
            inserirNovaPeca('c', 1, new Bishop(tabuleiro, Color.WHITE));
            inserirNovaPeca('d', 1, new Queen(tabuleiro, Color.WHITE));
            inserirNovaPeca('e', 1, new King(tabuleiro, Color.WHITE, this));
            inserirNovaPeca('f', 1, new Bishop(tabuleiro, Color.WHITE));
            inserirNovaPeca('g', 1, new Knight(tabuleiro, Color.WHITE));
            inserirNovaPeca('h', 1, new Rook(tabuleiro, Color.WHITE));
            inserirNovaPeca('a', 2, new Pawn(tabuleiro, Color.WHITE, this));
            inserirNovaPeca('b', 2, new Pawn(tabuleiro, Color.WHITE, this));
            inserirNovaPeca('c', 2, new Pawn(tabuleiro, Color.WHITE, this));
            inserirNovaPeca('d', 2, new Pawn(tabuleiro, Color.WHITE, this));
            inserirNovaPeca('e', 2, new Pawn(tabuleiro, Color.WHITE, this));
            inserirNovaPeca('f', 2, new Pawn(tabuleiro, Color.WHITE, this));
            inserirNovaPeca('g', 2, new Pawn(tabuleiro, Color.WHITE, this));
            inserirNovaPeca('h', 2, new Pawn(tabuleiro, Color.WHITE, this));
            
            inserirNovaPeca('a', 8, new Rook(tabuleiro, Color.BLACK));
            inserirNovaPeca('b', 8, new Knight(tabuleiro, Color.BLACK));
            inserirNovaPeca('c', 8, new Bishop(tabuleiro, Color.BLACK));
            inserirNovaPeca('d', 8, new Queen(tabuleiro, Color.BLACK));
            inserirNovaPeca('e', 8, new King(tabuleiro, Color.BLACK, this));
            inserirNovaPeca('f', 8, new Bishop(tabuleiro, Color.BLACK));
            inserirNovaPeca('g', 8, new Knight(tabuleiro, Color.BLACK));
            inserirNovaPeca('h', 8, new Rook(tabuleiro, Color.BLACK));
            inserirNovaPeca('a', 7, new Pawn(tabuleiro, Color.BLACK, this));
            inserirNovaPeca('b', 7, new Pawn(tabuleiro, Color.BLACK, this));
            inserirNovaPeca('c', 7, new Pawn(tabuleiro, Color.BLACK, this));
            inserirNovaPeca('d', 7, new Pawn(tabuleiro, Color.BLACK, this));
            inserirNovaPeca('e', 7, new Pawn(tabuleiro, Color.BLACK, this));
            inserirNovaPeca('f', 7, new Pawn(tabuleiro, Color.BLACK, this));
            inserirNovaPeca('g', 7, new Pawn(tabuleiro, Color.BLACK, this));
            inserirNovaPeca('h', 7, new Pawn(tabuleiro, Color.BLACK, this));
        }
}