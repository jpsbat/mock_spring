package br.com.valueprojects.mock_spring.service;

import br.com.valueprojects.mock_spring.model.Jogo;
import br.com.valueprojects.mock_spring.model.Resultado;

public class VencedorServiceFalso implements VencedorService {

    private final SmsService smsService;

    public VencedorServiceFalso(SmsService smsService) {
        this.smsService = smsService;
    }

    @Override
    public void salvarVencedor(Jogo jogo) {
        Resultado vencedor = jogo.getResultados().stream()
                                 .max((r1, r2) -> Double.compare(r1.getMetrica(), r2.getMetrica()))
                                 .orElseThrow(() -> new RuntimeException("Jogo sem resultados"));

        System.out.println("Salvando vencedor " + vencedor.getParticipante().getNome());

        smsService.enviarSms("Parabéns " + vencedor.getParticipante().getNome() + "! Você venceu o jogo " + jogo.getDescricao(), "12345");
    }
}
