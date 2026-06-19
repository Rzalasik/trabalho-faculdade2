package com.clinica;

import com.clinica.controller.AnimalController;
import com.clinica.controller.ConsultaController;
import com.clinica.controller.TutorController;
import com.clinica.model.Animal;
import com.clinica.model.Tutor;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        TutorController tutorController = new TutorController();
        AnimalController animalController = new AnimalController();
        ConsultaController consultaController = new ConsultaController();

        System.out.println("=== CLÍNICA VETERINÁRIA ===\n");

        // 1. Criar tutor
        System.out.println("--- Cadastrando tutor ---");
        Tutor tutor = tutorController.cadastrar("Carlos Souza", "Rua das Flores, 100", "11999990000");

        if (tutor == null) {
            System.out.println("Não foi possível continuar sem um tutor cadastrado.");
            return;
        }

        // 2. Criar animal vinculado ao tutor
        System.out.println("\n--- Cadastrando animal ---");
        Animal animal = animalController.cadastrar("Rex", "Cão", "Labrador", tutor.getId());

        if (animal == null) {
            System.out.println("Não foi possível continuar sem um animal cadastrado.");
            return;
        }

        // 3. Criar consulta para o animal
        System.out.println("\n--- Registrando consulta ---");
        consultaController.registrar(animal.getId(), LocalDate.now(), "Check-up anual", new BigDecimal("150.00"));

        // 4. Listar consultas do animal
        System.out.println("\n--- Consultas do animal ---");
        consultaController.listarPorAnimal(animal.getId());

        // 5. Listar animais do tutor
        System.out.println("\n--- Animais do tutor ---");
        animalController.listarPorTutor(tutor.getId());

        // 6. Tentar criar consulta com valor negativo (deve falhar com mensagem de erro)
        System.out.println("\n--- Tentativa de consulta com valor negativo (deve falhar) ---");
        consultaController.registrar(animal.getId(), LocalDate.now(), "Consulta inválida", new BigDecimal("-50.00"));

        System.out.println("\n=== FIM DO FLUXO ===");
    }
}
