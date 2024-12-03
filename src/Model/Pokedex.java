package Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pokedex")
public class Pokedex implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "peso", nullable = false, length = 10)
    private double peso;

    @Column(name = "misc", nullable = false)
    private String misc;


    public Pokedex() {
    }

    public Pokedex(Long id, String nome, double peso, String misc) {
        this.id = id;
        this.nome = nome;
        this.peso = peso;
        this.misc = misc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getMisc() {
        return misc;
    }

    public void setMisc(String misc) {
        this.misc = misc;
    }

    @Override
    public String toString() {
        return "Pokedex{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", peso=" + peso +
                ", misc='" + misc + '\'' +
                '}';
    }
}
