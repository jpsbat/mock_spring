package br.com.valueprojects.mock_spring;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.valueprojects.mock_spring.builder.CriadorDeJogo;
import br.com.valueprojects.mock_spring.model.FinalizaJogo;
import br.com.valueprojects.mock_spring.model.Jogo;
import br.com.valueprojects.mock_spring.service.SmsService;
import br.com.valueprojects.mock_spring.service.VencedorService;
import infra.JogoDao;

public class FinalizaJogoTest {

    @Test
    public void deveFinalizarJogosDaSemanaAnteriorEEnviarSms() {

        Calendar antiga = Calendar.getInstance();
        antiga.set(1999, 1, 20);

        Jogo jogo1 = new CriadorDeJogo().para("Cata moedas")
            .naData(antiga).constroi();
        Jogo jogo2 = new CriadorDeJogo().para("Derruba barreiras")
            .naData(antiga).constroi();

        List<Jogo> jogosAnteriores = Arrays.asList(jogo1, jogo2);

        JogoDao daoFalso = mock(JogoDao.class);
        when(daoFalso.emAndamento()).thenReturn(jogosAnteriores);

        SmsService smsMock = mock(SmsService.class);
        VencedorService vencedorMock = mock(VencedorService.class);

        FinalizaJogo finalizador = new FinalizaJogo(daoFalso, vencedorMock);
        finalizador.finaliza();

        verify(vencedorMock, times(2)).salvarVencedor(any(Jogo.class));
        verify(smsMock, never()).enviarSms(anyString(), anyString());
    }

    @Test
    public void deveVerificarSeMetodoAtualizaFoiInvocado() {

        Calendar antiga = Calendar.getInstance();
        antiga.set(1999, 1, 20);

        Jogo jogo1 = new CriadorDeJogo().para("Cata moedas").naData(antiga).constroi();
        Jogo jogo2 = new CriadorDeJogo().para("Derruba barreiras").naData(antiga).constroi();

        List<Jogo> jogosAnteriores = Arrays.asList(jogo1, jogo2);

        JogoDao daoFalso = mock(JogoDao.class);
        when(daoFalso.emAndamento()).thenReturn(jogosAnteriores);

        VencedorService vencedorMock = mock(VencedorService.class);

        FinalizaJogo finalizador = new FinalizaJogo(daoFalso, vencedorMock);
        finalizador.finaliza();

        verify(daoFalso, times(2)).atualiza(any(Jogo.class));
    }
}
