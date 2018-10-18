package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Tiago
 */
@Entity
@Table(name = "necessidadeespecial")
public class NecessidadeEspecial implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_necessidadeespecial", sequenceName = "seq_necessidadeespecial_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_necessidadeespecial", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode ser em branco")
    @Length(max = 40, message = "O nome não pode ter mais que {max} caracteres")
    @Column(name = "nome", length = 40, nullable = false)
    private String nome;
    @NotNull(message = "A descricao não pode ser nulo")
    @NotBlank(message = "A descricao não pode ser em branco")
    @Length(max = 200, message = "A descricao não pode ter mais que {max} caracteres")
    @Column(name = "descricao", length = 200, nullable = false)
    private String descricao;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "necessidades",
            joinColumns = 
            @JoinColumn(name = "necessidadeespecial", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = 
            @JoinColumn(name = "estudante", referencedColumnName = "id", nullable = false), 
            uniqueConstraints = {@UniqueConstraint(columnNames = {"estudante","necessidadeespecial"})}) 
    private List<Estudante> necessitam = new ArrayList<>();

    public NecessidadeEspecial() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NecessidadeEspecial other = (NecessidadeEspecial) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public List<Estudante> getNecessitam() {
        return necessitam;
    }

    public void setNecessitam(List<Estudante> necessitam) {
        this.necessitam = necessitam;
    }
}
