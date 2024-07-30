package br.com.alura.codechella.domain.entities.usuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class UsuarioTest {
    @Test
    public void naoDeveCadastrarUsuarioComCpfNoFormatoInvalido() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Usuario("123.56.789-00", "Fulano", LocalDate.parse("1990-01-01"), "haqueline@email.com"));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Usuario("", "Fulano", LocalDate.parse("1990-01-01"), "haqueline@email.com"));
    }

    @Test
    public void naoDeveCadastrarUsuarioComNomeInvalido() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Usuario("123.456.789-00", null, LocalDate.parse("1990-01-01"), "email@email.com"));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Usuario("123.456.789-00", "", LocalDate.parse("1990-01-01"), "email@email.com"));

    }

    @Test
    public void naoDeveCadastrarUsuarioComDataDeNascimentoInvalido() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Usuario("123.456.789-00", "Jose", LocalDate.parse("2025-01-01"), "email@email.com"));
    }

    @Test
    public void deveCriarUsuarioUsandoFabricaDeUsuario() {
        FabricaDeUsuario fabrica = new FabricaDeUsuario();
        Usuario usuario = fabrica.comNomeCpfNascimento("Jose", "123.456.789-00", LocalDate.parse("1990-01-01"));
        Assertions.assertEquals("Jose", usuario.getNome());
        Assertions.assertEquals("123.456.789-00", usuario.getCpf());
        Assertions.assertEquals(LocalDate.parse("1990-01-01"), usuario.getNascimento());

        usuario = fabrica.incluiEndereco("12345-678", 123, "Casa");
        Assertions.assertEquals("12345-678", usuario.getEndereco().getCep());
        Assertions.assertEquals(123, usuario.getEndereco().getNumero());
        Assertions.assertEquals("Casa", usuario.getEndereco().getComplemento());
    }
}
