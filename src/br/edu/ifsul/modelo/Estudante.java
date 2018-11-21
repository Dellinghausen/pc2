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
    @NotBlank(message = "O telefone não pode ser em branco")
    @Length(max = 15, message = "O telefone não pode ter mais que {max} caracteres")
    @Column(name = "telefone", nullable = false)
    private String telefone;
    @NotNull(message = "O curso não pode ser nulo")
    @NotBlank(message = "O curso não pode ser em branco")
    @Length(max = 40, message = "O curso não pode ter mais que {max} caracteres")
    @Column(name = "curso", length = 40, nullable = false)
    private String curso;
    @NotNull(message = "O telefone de emergencia não pode ser nulo")
    @NotBlank(message = "O telefone de emergencia não pode ser em branco")
    @Length(max = 15, message = "O telefone de emergencia não pode ter mais que {max} caracteres")
    @Column(name = "telefoneEmergencia", nullable = false)
    private String telefoneEmergencia;
//    @CPF
    @NotBlank(message = "O cpf não pode ser em branco")
    @Length(max = 14, message = "O cpf não pode ter mais que {max} caracteres")
    @Column(name = "cpf", length = 14, nullable = false)
    private String cpf;
    @NotNull(message = "A cidade deve ser informado")
    @ManyToOne
    @JoinColumn(name = "cidade", referencedColumnName = "id", nullable = false, foreignKey = @javax.persistence.ForeignKey(name = "fk_estudante_cidade"))
    private Cidade cidade;
    @OneToMany(mappedBy = "estudante", cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<AcaoPosterior> acaoposterior = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "necessidades",
            joinColumns
            = @JoinColumn(name = "estudante", referencedColumnName = "id", nullable = false),
            inverseJoinColumns
            = @JoinColumn(name = "necessidadeespecial", referencedColumnName = "id", nullable = false),
            uniqueConstraints = {
                @UniqueConstraint(columnNames = {"estudante", "necessidadeespecial"})})
    private List<NecessidadeEspecial> necessidade = new ArrayList<>();


    public Estudante() {
    }

    public void adicionarAcao(AcaoPosterior obj) {
        obj.setEstudante(this);
        this.acaoposterior.add(obj);
    }

    public void removerAcao(int index) {
        this.acaoposterior.remove(index);
    }

    public Calendar getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Calendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
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

    public List<NecessidadeEspecial> getNecessidade() {
        return necessidade;
    }

    public void setNecessidade(List<NecessidadeEspecial> necessidade) {
        this.necessidade = necessidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefoneEmergencia() {
        return telefoneEmergencia;
    }

    public void setTelefoneEmergencia(String telefoneEmergencia) {
        this.telefoneEmergencia = telefoneEmergencia;
    }
}
