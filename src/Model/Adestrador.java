package Model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.sql.Date;

@Entity
@Table(name = "adestrador")
public class Adestrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "nacemento")
    private Date nacemento;

    // Constructor por defecto
    public Adestrador() {
    }

    // Constructor con parámetros
    public Adestrador(Long id, String nome, Date nacemento) {
        this.id = id;
        this.nome = nome;
        this.nacemento = nacemento;
    }

    // Getter para 'nome'
    public String getNome() {
        return nome;
    }

    // Setter para 'nome'
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter para 'nacemento' - Aquí usamos la anotación @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlElement(name = "nacemento")  // JAXB mapeará el campo 'nacemento' con este nombre en el XML
    public Date getNacemento() {
        return nacemento;
    }

    // Setter para 'nacemento'
    public void setNacemento(Date nacemento) {
        this.nacemento = nacemento;
    }

    // Getter para 'id'
    public Long getId() {
        return id;
    }

    // Setter para 'id'
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Adestrador{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", nacemento=" + nacemento +
                '}';
    }
}
