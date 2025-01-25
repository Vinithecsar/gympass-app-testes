package com.gympass;

import java.time.LocalDate;

import com.gympass.model.Academia;
import com.gympass.model.CheckIn;
import com.gympass.model.Usuario;
import com.gympass.usecases.AutenticarUseCase;
import com.gympass.usecases.BuscarAcademiasProximasUseCase;
import com.gympass.usecases.BuscarHistoricoCheckInsDoUsuarioUseCase;
import com.gympass.usecases.CadastrarUseCase;
import com.gympass.usecases.CheckInUseCase;
import com.gympass.usecases.CriarAcademiaUseCase;
import com.gympass.usecases.GetMetricasUsuarioUseCase;
import com.gympass.usecases.GetPerfilUsuarioUseCase;
import com.gympass.usecases.PesquisarAcademiasUseCase;
import com.gympass.usecases.ValidarCheckInUseCase;
import com.gympass.usecases.factories.UseCaseFactory;

public class Main {
    public static void main(String[] args) {
        CadastrarUseCase cadastrarUseCase = UseCaseFactory.makeCadastrarUseCase();
        AutenticarUseCase autenticarUseCase = UseCaseFactory.makeAutenticarUseCase();
        GetPerfilUsuarioUseCase getPerfilUsuarioUseCase = UseCaseFactory.makeGetPerfilUsuarioUseCase();

        CriarAcademiaUseCase criarAcademiaUseCase = UseCaseFactory.makeCriarAcademiaUseCase();
        BuscarAcademiasProximasUseCase buscarAcademiasProximasUseCase = UseCaseFactory
                .makeBuscarAcademiasProximasUseCase();
        PesquisarAcademiasUseCase pesquisarAcademiasUseCase = UseCaseFactory.makePesquisarAcademiasUseCase();

        CheckInUseCase checkInUseCase = UseCaseFactory.makeCheckInUseCase();
        ValidarCheckInUseCase validarCheckInUseCase = UseCaseFactory.makeValidarCheckInUseCase();
        BuscarHistoricoCheckInsDoUsuarioUseCase buscarHistoricoCheckInsDoUsuarioUseCase = UseCaseFactory
                .makeBuscarHistoricoCheckInsDoUsuarioUseCase();
        GetMetricasUsuarioUseCase getMetricasUsuarioUseCase = UseCaseFactory.makeGetMetricasUsuarioUseCase();

        Usuario usuario = new Usuario();
        Academia academia = new Academia();

        // Exemplo de cadastro, autenticação e visualização de dados de usuário
        try {
            System.out.println("Cadastrando João da Silva...");
            cadastrarUseCase.execute("João da Silva", "joaosilva@email.com", "123456");
            System.out.println("Usuário cadastrado com sucesso!");

            System.out.println("Autenticando João da Silva...");
            usuario = autenticarUseCase.execute("joaosilva@email.com", "123456");
            System.out.println("Usuário autenticado com sucesso!");

            System.out.println("Buscando perfil do usuário...");
            System.out.println("Dados: " + getPerfilUsuarioUseCase.execute(usuario.getId()));

            System.out.println("Autenticando João da Silva com senha incorreta...");
            autenticarUseCase.execute("joaosilva@email.com", "1234567");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println();

        // Exemplo de criação de academias, busca de academias próximas
        try {
            System.out.println("Cadastrando academias...");
            academia = criarAcademiaUseCase.execute("Academia do João", "Descricao Joao 1", "12345678", 0.0, 0.0);
            criarAcademiaUseCase.execute("Academia do Lucas", "Descricao Lucas 1", "12345678", 50.0, 50.0);
            System.out.println("Academias cadastradas com sucesso!");

            System.out.println("Buscando academias próximas a coordenada 0.0,0.0 ...");
            System.out.println("Dados das academias próximas: ");
            buscarAcademiasProximasUseCase.execute(0.0, 0.0).forEach(System.out::println);

            System.out.println("Buscando academias próximas a coordenada 50.0,50.0 ...");
            System.out.println("Dados das academias próximas: ");
            buscarAcademiasProximasUseCase.execute(50.0, 50.0).forEach(System.out::println);

            System.out.println("Pesquisando academias com nome 'João' ...");
            System.out.println("Dados das academias encontradas: ");
            pesquisarAcademiasUseCase.execute("João").forEach(System.out::println);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println();

        // Exemplo de CheckIn, validação,
        // busca de histórico de checkIns e métricas de usuário
        try {
            CheckIn checkIn;
            LocalDate horarioOutroDia = LocalDate.now().plusDays(5);

            System.out.println("Realizando checkIn na academia do João...");
            checkIn = checkInUseCase.execute(usuario.getId(), academia.getId(), 0.0, 0.0);
            System.out.println("CheckIn realizado com sucesso!");

            System.out.println("Validando checkIn na academia do João...");
            validarCheckInUseCase.execute(checkIn.getId());
            System.out.println("CheckIn validado com sucesso!");

            System.out.println("Buscando histórico de checkIns do usuário...");
            System.out.println("Histórico de checkIns: ");
            buscarHistoricoCheckInsDoUsuarioUseCase.execute(usuario.getId()).forEach(System.out::println);

            System.out.println("Buscando métricas do usuário...");
            System.out.println("Métricas: " + getMetricasUsuarioUseCase.execute(usuario.getId()));

            System.out.println("Realizando checkIn na academia do João novamente, em outro dia...");
            checkIn = checkInUseCase.execute(usuario.getId(), academia.getId(), 0.0, 0.0,
                    horarioOutroDia);
            System.out.println("CheckIn realizado com sucesso!");

            System.out.println("Validando checkIn na academia do João novamente...");
            validarCheckInUseCase.execute(checkIn.getId(), horarioOutroDia.atStartOfDay().plusMinutes(10));
            System.out.println("CheckIn validado com sucesso!");

            System.out.println("Buscando histórico de checkIns do usuário novamente...");
            System.out.println("Histórico de checkIns: ");
            buscarHistoricoCheckInsDoUsuarioUseCase.execute(usuario.getId()).forEach(System.out::println);

            System.out.println("Buscando métricas do usuário novamente...");
            System.out.println("Métricas: " + getMetricasUsuarioUseCase.execute(usuario.getId()));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}