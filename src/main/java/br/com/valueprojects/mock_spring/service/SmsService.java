package br.com.valueprojects.mock_spring.service;

public interface SmsService {
    void enviarSms(String mensagem, String numero);
}
