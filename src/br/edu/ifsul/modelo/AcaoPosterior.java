package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Tiago
 */
@Entity
@Table(name = "acaoposterior")
public class AcaoPosterior implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_acaoposterior", sequenceName = "seq_acaoposterior_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_acaoposterior", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotNull(message = "O titulo não pode ser nulo")
    @NotBlank(message = "O titulo não pode ser em branco")
    @Length(max = 40, message = "O titulo não pode ter mais que {max} caracteres")
    @Column(name = "titulo", length = 40, nullable = false)
    private String titulo;
    @NotNull(message = "A descricao não pode ser nulo")
    @NotBlank(message = "A descricao não pode ser em branco")
    @Length(max = 200, message = "A descricao não pode ter mais que {max} caracteres")
    @Column(name = "descricao", length = 200, nullable = false)
    private String descricao;
    @Temporal(TemporalType.DATE)
    @NotNull(message = "A data não pode ser nulo")
    @Column(name = "dataacao", nullable = false)
    private Calendar dataacao;
    @NotNull(message = "O estudante deve ser informada")
    @ManyToOne
    @JoinColumn(name = "estudante_id", referencedColumnName = "id", nullable = false, foreignKey = @javax.persistence.ForeignKey(name="fk_acaoposterios_estudante"))
    private Estudante estudante;

    public AcaoPosterior() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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
        hash = 73 * hash + Objects.hashCode(this.id);
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
        final AcaoPosterior other = (AcaoPosterior) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public Calendar getDataacao() {
        return dataacao;
    }

    public void setDataacao(Calendar dataacao) {
        this.dataacao = dataacao;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }
}
