package br.com.valueprojects.mock_spring.dao;

import br.com.valueprojects.mock_spring.model.Jogo;

public class VencedorDaoFalso implements VencedorDao {

    @Override
    public void salvarVencedor(Jogo jogo) {
        System.out.println("Salvando vencedor do jogo: " + jogo.getDescricao());
    }
}
