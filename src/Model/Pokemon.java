package Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pokemon")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome",length = 50,nullable = false)
    private String nome;

    @Column(name = "nacemento",nullable = false)
    private Date nacemento;

    @ManyToOne
    @JoinColumn(name = "pokedexentry",referencedColumnName = "id",nullable = true)
    private Pokedex pokedexentry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adestrador",referencedColumnName = "id",nullable = true)
    private Adestrador adestrador;

    public Pokemon() {
    }

    public Pokemon(long id, String nome, Date nacemento, Pokedex pokedexentry, Adestrador adestrador) {
        this.id = id;
        this.nome = nome;
        this.nacemento = nacemento;
        this.pokedexentry = pokedexentry;
        this.adestrador = adestrador;
    }



    public Pokemon(String nome, Date nacemento, Pokedex pokedexentry, Adestrador adestrador) {
        this.nome = nome;
        this.nacemento = nacemento;
        this.pokedexentry = pokedexentry;
        this.adestrador = adestrador;
    }

    public Pokemon(String nome, Date nacemento) {
        this.nome = nome;
        this.nacemento = nacemento;
    }

    public Pokemon(long id, String string, java.sql.Date nacemento, int i, int i1) {

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getNacemento() {
        return nacemento;
    }

    public void setNacemento(Date nacemento) {
        this.nacemento = nacemento;
    }

    public Pokedex getPokedexentry() {
        return pokedexentry;
    }

    public void setPokedexentry(Pokedex pokedexentry) {
        this.pokedexentry = pokedexentry;
    }

    public Adestrador getAdestrador() {
        return adestrador;
    }

    public void setAdestrador(Adestrador adestrador) {
        this.adestrador = adestrador;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", nacemento=" + nacemento +
                ", pokedexentry=" + pokedexentry +
                ", adestrador=" + adestrador +
                '}';
    }
}
