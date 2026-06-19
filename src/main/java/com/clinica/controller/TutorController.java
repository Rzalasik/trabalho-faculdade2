package com.clinica.controller;

import com.clinica.model.Tutor;
import com.clinica.service.TutorService;

import java.sql.SQLException;
import java.util.List;

public class TutorController {
    private final TutorService service = new TutorService();

    public Tutor cadastrar(String nome, String endereco, String telefone) {
        try {
            Tutor tutor = service.cadastrar(nome, endereco, telefone);
            System.out.println("[Tutor] Cadastrado com sucesso: " + tutor);
            return tutor;
        } catch (IllegalArgumentException | SQLException e) {
            System.out.println("[Tutor] Erro ao cadastrar: " + e.getMessage());
            return null;
        }
    }

    public void listarTodos() {
        try {
            List<Tutor> tutores = service.listarTodos();
            System.out.println("[Tutor] Total de tutores: " + tutores.size());
            tutores.forEach(t -> System.out.println("  " + t));
        } catch (SQLException e) {
            System.out.println("[Tutor] Erro ao listar: " + e.getMessage());
        }
    }

    public void listarAnimaisDeTutor(int tutorId) {
        try {
            com.clinica.service.AnimalService animalService = new com.clinica.service.AnimalService();
            List<com.clinica.model.Animal> animais = animalService.listarPorTutor(tutorId);
            System.out.println("[Tutor] Animais do tutor id=" + tutorId + ": " + animais.size());
            animais.forEach(a -> System.out.println("  " + a));
        } catch (SQLException e) {
            System.out.println("[Tutor] Erro ao listar animais: " + e.getMessage());
        }
    }
}
