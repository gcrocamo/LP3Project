package aplicacao;

import xadrez.PartidaXadrez;

public class TestePartidaXadrez {

	public static void main(String[] args) {

		PartidaXadrez partidaXadrez = new PartidaXadrez();
        UI.imprimirTabuleiro(partidaXadrez.getPecas());
	}
}
