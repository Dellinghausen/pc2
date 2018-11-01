package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "opcaoresposta")
public class OpcaoResposta implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_opcaoresposta", sequenceName = "seq_opcaoresposta_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_opcaoresposta", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotNull(message = "A opcao não pode ser nulo")
    @NotBlank(message = "A opcao não pode ser em branco")
    @Length(max = 40, message = "A opcao não pode ter mais que {max} caracteres")
    @Column(name = "opcao", length = 40, nullable = false)
    private String opcao;
    @NotNull(message = "A pergunta deve ser informada")
    @ManyToOne
    @JoinColumn(name = "pergunta_id", referencedColumnName = "id", nullable = false, foreignKey = @javax.persistence.ForeignKey(name = "fk_opcaoResposta_pergunta"))
    private Pergunta pergunta;
    @OneToMany(mappedBy = "opcaoresposta", cascade = CascadeType.ALL, orphanRemoval = true,
    fetch = FetchType.LAZY)
    private List<RespostaComEscolha> respostaEscolha = new ArrayList<>();

    public OpcaoResposta() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpcao() {
        return opcao;
    }

    public void setOpcao(String opcao) {
        this.opcao = opcao;
    }

    public Pergunta getPergunta() {
        return pergunta;
    }

    public void setPergunta(Pergunta pergunta) {
        this.pergunta = pergunta;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final OpcaoResposta other = (OpcaoResposta) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public List<RespostaComEscolha> getRespostaEscolha() {
        return respostaEscolha;
    }

    public void setRespostaEscolha(List<RespostaComEscolha> respostaEscolha) {
        this.respostaEscolha = respostaEscolha;
    }
}
