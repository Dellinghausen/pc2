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
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Tiago
 */
@Entity
@Table(name = "questionario")
public class Questionario implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_questionario", sequenceName = "seq_questionario_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_questionario", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode ser em branco")
    @Length(max = 40, message = "O nome não pode ter mais que {max} caracteres")
    @Column(name = "nome", length = 40, nullable = false)
    private String nome;
    @NotNull(message = "O usuario deve ser informado")
    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "id", nullable = false, foreignKey = @javax.persistence.ForeignKey(name = "fk_questionario_usuario"))
    private Usuario usuario;    
    @OneToMany(mappedBy = "questionario", cascade = CascadeType.ALL, orphanRemoval = true, 
            fetch = FetchType.LAZY)
    private List<Pergunta> pergunta = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "questionarioaluno",
            joinColumns = 
            @JoinColumn(name = "questionarios", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = 
            @JoinColumn(name = "estudante", referencedColumnName = "id", nullable = false), 
            uniqueConstraints = {@UniqueConstraint(columnNames = {"estudante","questionarios"})}) 
    private List<Estudante> respondem = new ArrayList<>();

    public Questionario() {
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.id);
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
        final Questionario other = (Questionario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public List<Pergunta> getPergunta() {
        return pergunta;
    }

    public void setPergunta(List<Pergunta> pergunta) {
        this.pergunta = pergunta;
    }

    public List<Estudante> getRespondem() {
        return respondem;
    }

    public void setRespondem(List<Estudante> respondem) {
        this.respondem = respondem;
    }
}
