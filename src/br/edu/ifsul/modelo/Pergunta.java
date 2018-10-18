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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Tiago
 */
@Entity
@Table(name = "pergunta")
public class Pergunta implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_pergunta", sequenceName = "seq_pergunta_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_pergunta", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotNull(message = "O pergunta não pode ser nulo")
    @NotBlank(message = "O pergunta não pode ser em branco")
    @Length(max = 40, message = "O pergunta não pode ter mais que {max} caracteres")
    @Column(name = "pergunta", length = 40, nullable = false)
    private String pergunta;
    @Min(value = 1, message = "A quantidade de escolhas não pode ser menor do que 1.")
    @NotNull(message = "A quantidade de escolha deve ser informada")
    @Column(name = "quantidade_escolhas", nullable = false)
    private Integer quantidade_escolhas;
    @NotNull(message = "A categoria deve ser informado")
    @ManyToOne
    @JoinColumn(name = "categoria", referencedColumnName = "id", nullable = false, foreignKey = @javax.persistence.ForeignKey(name = "fk_pergunta_categoria"))
    private Categoria categoria;
    @NotNull(message = "O questionario deve ser informado")
    @ManyToOne
    @JoinColumn(name = "questionario_id", referencedColumnName = "id", nullable = false, foreignKey = @javax.persistence.ForeignKey(name = "fk_questionario_id"))
    private Questionario questionario;
    @OneToMany(mappedBy = "pergunta", cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<OpcaoResposta> opcaoResposta = new ArrayList<>();

    public Pergunta() {
    }

    public void adicionarOpcaoResposta(OpcaoResposta obj) {
        obj.setPergunta(this);
        this.opcaoResposta.add(obj);
    }

    public void removerOpcaoResposta(int index) {
        this.opcaoResposta.remove(index);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public Integer getQuantidade_escolhas() {
        return quantidade_escolhas;
    }

    public void setQuantidade_escolhas(Integer quantidade_escolhas) {
        this.quantidade_escolhas = quantidade_escolhas;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
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
        final Pergunta other = (Pergunta) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public Questionario getQuestionario() {
        return questionario;
    }

    public void setQuestionario(Questionario questionario) {
        this.questionario = questionario;
    }

    public List<OpcaoResposta> getPossibilidadeResposta() {
        return opcaoResposta;
    }

    public void setPossibilidadeResposta(List<OpcaoResposta> opcaoResposta) {
        this.opcaoResposta = opcaoResposta;
    }
}
