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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
    @Length(max = 200, message = "O pergunta não pode ter mais que {max} caracteres")
    @Column(name = "pergunta", length = 200, nullable = false)
    private String pergunta;
//    @Min(value = 1, message = "A quantidade de escolhas não pode ser menor do que 1.")
//    @NotNull(message = "A quantidade de escolha deve ser informada")
    @Column(name = "quantidade_escolhas", nullable = true)
    private Integer quantidade_escolhas;
//    @NotNull(message = "A categoria deve ser informado")
    @ManyToOne
    @JoinColumn(name = "categoria", referencedColumnName = "id", nullable = true, foreignKey = @javax.persistence.ForeignKey(name = "fk_pergunta_categoria"))
    private Categoria categoria;
    @NotNull(message = "Campo tipo não pode ser nulo")
    @Column(name = "tipo", nullable = false)   
    private Boolean tipo;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "perguntas",
            joinColumns = 
            @JoinColumn(name = "perguntas", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = 
            @JoinColumn(name = "questionarios", referencedColumnName = "id", nullable = false), 
            uniqueConstraints = {@UniqueConstraint(columnNames = {"questionarios", "perguntas"})})
    private List<Questionario> questionario = new ArrayList<>();
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

    public Boolean getTipo() {
        return tipo;
    }

    public void setTipo(Boolean tipo) {
        this.tipo = tipo;
    }

    public List<OpcaoResposta> getOpcaoResposta() {
        return opcaoResposta;
    }

    public void setOpcaoResposta(List<OpcaoResposta> opcaoResposta) {
        this.opcaoResposta = opcaoResposta;
    }

    public List<Questionario> getQuestionario() {
        return questionario;
    }

    public void setQuestionario(List<Questionario> questionario) {
        this.questionario = questionario;
    }
}
