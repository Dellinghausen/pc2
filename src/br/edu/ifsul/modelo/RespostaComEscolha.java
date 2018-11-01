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
@Table(name = "respostacomescolha")
public class RespostaComEscolha implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_respostacomescolha", sequenceName = "seq_respostacomescolha_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_respostacomescolha", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotNull(message = "A pergunta deve ser informado")
    @ManyToOne
    @JoinColumn(name = "pergunta_id", referencedColumnName = "id", nullable = false, foreignKey = @javax.persistence.ForeignKey(name = "fk_pergunta_id"))
    private Pergunta pergunta;
    @NotNull(message = "Os dadosresposta deve ser informado")
    @ManyToOne
    @JoinColumn(name = "dadosresposta_id", referencedColumnName = "id", nullable = false, foreignKey = @javax.persistence.ForeignKey(name = "fk_dadosresposta_id"))
    private DadosResposta dadosresposta;
    @NotNull(message = "A resposta deve ser informado")
    @ManyToOne
    @JoinColumn(name = "opcaoresposta_id", referencedColumnName = "id", nullable = false, foreignKey = @javax.persistence.ForeignKey(name = "fk_opcaoresposta_id"))
    private OpcaoResposta opcaoresposta;

    public RespostaComEscolha() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final RespostaComEscolha other = (RespostaComEscolha) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public OpcaoResposta getOpcaoresposta() {
        return opcaoresposta;
    }

    public void setOpcaoresposta(OpcaoResposta opcaoresposta) {
        this.opcaoresposta = opcaoresposta;
    }
}
