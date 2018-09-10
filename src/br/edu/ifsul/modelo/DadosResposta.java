package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Tiago
 */
@Entity
@Table(name = "dadosresposta")
public class DadosResposta implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_dadosresposta", sequenceName = "seq_dadosresposta_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_dadosresposta", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotNull(message = "O estudante deve ser informado")
    @ManyToOne
    @JoinColumn(name = "estudante_id", referencedColumnName = "id", nullable = false, foreignKey = @javax.persistence.ForeignKey(name = "fk_estudante_id"))
    private Estudante estudante;
    @NotNull(message = "O questionario deve ser informado")
    @ManyToOne
    @JoinColumn(name = "questionario_id", referencedColumnName = "id", nullable = false, foreignKey = @javax.persistence.ForeignKey(name = "fk_questionario_id"))
    private Questionario questionario;

    public DadosResposta() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    public Questionario getQuestionario() {
        return questionario;
    }

    public void setQuestionario(Questionario questionario) {
        this.questionario = questionario;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.id);
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
        final DadosResposta other = (DadosResposta) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
