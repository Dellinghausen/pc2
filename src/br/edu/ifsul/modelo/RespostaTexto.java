package br.edu.ifsul.modelo;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Tiago
 */
@Entity
@Table(name = "respostatexto")
public class RespostaTexto implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_respostatexto", sequenceName = "seq_respostatexto_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_respostatexto", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotNull(message = "A resposta não pode ser nulo")
    @NotBlank(message = "A resposta não pode ser em branco")
    @Length(max = 40, message = "A resposta não pode ter mais que {max} caracteres")
    @Column(name = "resposta", length = 40, nullable = false)
    private String resposta;
    @NotNull(message = "A pergunta deve ser informado")
    @ManyToOne
    @JoinColumn(name = "pergunta_id", referencedColumnName = "id", nullable = false, foreignKey = @javax.persistence.ForeignKey(name = "fk_pergunta_id"))
    private Pergunta pergunta;
    @NotNull(message = "Os dadosresposta deve ser informado")
    @ManyToOne
    @JoinColumn(name = "dadosresposta_id", referencedColumnName = "id", nullable = false, foreignKey = @javax.persistence.ForeignKey(name = "fk_dadosresposta_id"))
    private DadosResposta dadosresposta;

    public RespostaTexto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public Pergunta getPergunta() {
        return pergunta;
    }

    public void setPergunta(Pergunta pergunta) {
        this.pergunta = pergunta;
    }

    public DadosResposta getDadosresposta() {
        return dadosresposta;
    }

    public void setDadosresposta(DadosResposta dadosresposta) {
        this.dadosresposta = dadosresposta;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final RespostaTexto other = (RespostaTexto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
