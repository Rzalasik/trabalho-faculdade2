package com.clinica.service;

import com.clinica.model.Animal;
import com.clinica.repository.AnimalRepository;
import com.clinica.repository.TutorRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AnimalService {
    private final AnimalRepository repository = new AnimalRepository();
    private final TutorRepository tutorRepository = new TutorRepository();

    public Animal cadastrar(String nome, String especie, String raca, int tutorId) throws SQLException {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do animal é obrigatório.");
        }
        if (especie == null || especie.isBlank()) {
            throw new IllegalArgumentException("Espécie do animal é obrigatória.");
        }
        tutorRepository.findById(tutorId).orElseThrow(() ->
                new IllegalArgumentException("Tutor com id " + tutorId + " não encontrado."));
        Animal animal = new Animal(nome, especie, raca, tutorId);
        return repository.save(animal);
    }

    public Optional<Animal> buscarPorId(int id) throws SQLException {
        return repository.findById(id);
    }

    public List<Animal> listarTodos() throws SQLException {
        return repository.findAll();
    }

    public List<Animal> listarPorTutor(int tutorId) throws SQLException {
        return repository.findByTutorId(tutorId);
    }

    public void atualizar(Animal animal) throws SQLException {
        repository.update(animal);
    }

    public void excluir(int id) throws SQLException {
        repository.delete(id);
    }
}
