package br.com.valueprojects.mock_spring.service;

public class SmsServiceFalso implements SmsService {

    @Override
    public void enviarSms(String mensagem, String telefone) {
        System.out.println("Enviando SMS para " + telefone + ": " + mensagem);
    }
}
