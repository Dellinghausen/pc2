package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_usuario", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode ser em branco")
    @Length(max = 40, message = "O nome não pode ter mais que {max} caracteres")
    @Column(name = "nome", length = 40, nullable = false)
    private String nome;
    @NotNull(message = "O login não pode ser nulo")
    @NotBlank(message = "O login não pode ser em branco")
    @Length(max = 40, message = "O login não pode ter mais que {max} caracteres")
    @Column(name = "login", length = 40, nullable = false)
    private String login;
    @NotNull(message = "O senha não pode ser nulo")
    @NotBlank(message = "O senha não pode ser em branco")
    @Length(max = 40, message = "O senha não pode ter mais que {max} caracteres")
    @Column(name = "senha", length = 40, nullable = false)
    private String senha;
    @NotNull(message = "O email não pode ser nulo")
    @NotBlank(message = "O email não pode ser em branco")
    @Length(max = 40, message = "O email não pode ter mais que {max} caracteres")
    @Column(name = "email", length = 40, nullable = false)
    private String email;
    @NotNull(message = "O adm não pode ser nulo")
    @Column(name = "adm", nullable = false)
    private Boolean adm;

    public Usuario() {
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAdm() {
        return adm;
    }

    public void setAdm(Boolean adm) {
        this.adm = adm;
    }
}
