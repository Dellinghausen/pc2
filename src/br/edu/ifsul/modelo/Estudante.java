package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author Tiago
 */
@Entity
@Table(name = "estudante")
public class Estudante extends Usuario implements Serializable {

    @Temporal(TemporalType.DATE)
    @NotNull(message = "O dataNascimento não pode ser nulo")
    @Column(name = "dataNascimento", nullable = false)
    private Calendar dataNascimento;
    @NotNull(message = "O telefone não pode ser nulo")
    @Column(name = "telefone", nullable = false)
    private Integer telefone;
    @NotNull(message = "O curso não pode ser nulo")
    @NotBlank(message = "O curso não pode ser em branco")
    @Length(max = 40, message = "O curso não pode ter mais que {max} caracteres")
    @Column(name = "curso", length = 40, nullable = false)
    private String curso;
    @NotNull(message = "O telefoneEmergencia não pode ser nulo")
    @Column(name = "telefoneEmergencia", nullable = false)
    private Integer telefoneEmergencia;
    @CPF
    @NotBlank(message = "O cpf não pode ser em branco")
    @Length(max = 14, message = "O cpf não pode ter mais que {max} caracteres")
    @Column(name = "cpf", length = 14, nullable = false)
    private String cpf;
    @NotNull(message = "A cidade deve ser informado")
    @ManyToOne
    @JoinColumn(name = "cidade", referencedColumnName = "id", nullable = false, foreignKey = @javax.persistence.ForeignKey(name="fk_estudante_cidade"))
    private Cidade cidade;
    @OneToMany(mappedBy = "acao_estudante", cascade = CascadeType.ALL, orphanRemoval = true, 
            fetch = FetchType.LAZY)
    private List<AcaoPosterior> acaoposterior = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "necessidades",
            joinColumns = 
            @JoinColumn(name = "estudante", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = 
            @JoinColumn(name = "necessidadeespecial", referencedColumnName = "id", nullable = false), 
            uniqueConstraints = {@UniqueConstraint(columnNames = {"estudante","necessidadeespecial"})}) 
    private List<NecessidadeEspecial> necessidade = new ArrayList<>();

    public Estudante() {
    }

    public Calendar getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Calendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getTelefone() {
        return telefone;
    }

    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Integer getTelefoneEmergencia() {
        return telefoneEmergencia;
    }

    public void setTelefoneEmergencia(Integer telefoneEmergencia) {
        this.telefoneEmergencia = telefoneEmergencia;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<AcaoPosterior> getAcaoposterior() {
        return acaoposterior;
    }

    public void setAcaoposterior(List<AcaoPosterior> acaoposterior) {
        this.acaoposterior = acaoposterior;
    }
}
