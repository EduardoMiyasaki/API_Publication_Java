package com.example.springboot.repositories;

import com.example.springboot.models.PublicationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PublicationRepository extends JpaRepository<PublicationModel , UUID> {
    // Repository é um mecanismo para interagir com o banco de dados
    // <> aqui dentro estou passando a tabela
}
