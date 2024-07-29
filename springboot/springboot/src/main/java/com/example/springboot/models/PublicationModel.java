package com.example.springboot.models;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_PUBLICATIONS")
public class PublicationModel extends RepresentationModel<PublicationModel> implements Serializable {
    // RepresentationModel facilita a adição de hateos ou seja aqueles links que retornam o status do cliente

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idPublicacao;
    private String tituloPublicacao;
    private String descricao;

    public UUID getIdPublicacao(){
        return this.idPublicacao;
    }

    public void setIdPublicacao(UUID idPublicacao){
        this.idPublicacao = idPublicacao;
    }

    public String getTituloPublicacao() {
        return tituloPublicacao;
    }

    public void setTituloPublicacao(String tituloPublicacao) {
        this.tituloPublicacao = tituloPublicacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
