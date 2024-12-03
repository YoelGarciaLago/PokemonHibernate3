package Services;

import Config.HibernateConfig;
import Model.Adestrador;
import Model.Pokedex;
import Model.Pokemon;
import Resources.EscribirXML;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PokemonServices {

    public void meterPokemon(String name, Date nacemento, long idPokedex, long idAdestrador){
        try(Session session = HibernateConfig.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Adestrador insertAdestrador = session.get(Adestrador.class,idAdestrador);
            Pokedex insertPokemon = session.get(Pokedex.class, idPokedex);
            Pokemon pokemonNuevo = new Pokemon();
            pokemonNuevo.setNome(name);
            pokemonNuevo.setNacemento(nacemento);
            pokemonNuevo.setPokedexentry(insertPokemon);
            pokemonNuevo.setAdestrador(insertAdestrador);
            session.save(pokemonNuevo);
            transaction.commit();
            //EscribirXML.escribirPokedexXML(session.createSQLQuery("select * from pokedex order by id").list());
        } catch (Exception e) {
            System.out.println("Error al crear al pokemon --> " + e.getMessage());
        }
    }

    public void meterPokemon(String name, Date nacemento, long idPokedex){
        try(Session session = HibernateConfig.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Pokedex insertPokemon = session.get(Pokedex.class, idPokedex);
            Pokemon pokemonNuevo = new Pokemon();
            pokemonNuevo.setNome(name);
            pokemonNuevo.setNacemento(nacemento);
            pokemonNuevo.setPokedexentry(insertPokemon);
            session.save(pokemonNuevo);
            transaction.commit();
            //EscribirXML.escribirPokedexXML(session.createSQLQuery("select * from pokedex order by id").list());
        } catch (Exception e) {
            System.out.println("Error al crear al pokemon --> " + e.getMessage());
        }
    }

    public void meterPokemon(String name,long idAdestrador,Date nacemento){
        try(Session session = HibernateConfig.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Adestrador insertAdestrador = session.get(Adestrador.class,idAdestrador);
            Pokemon pokemonNuevo = new Pokemon();
            pokemonNuevo.setNome(name);
            pokemonNuevo.setNacemento(nacemento);
            pokemonNuevo.setAdestrador(insertAdestrador);
            session.save(pokemonNuevo);
            transaction.commit();
            //EscribirXML.escribirPokedexXML(session.createSQLQuery("select * from pokedex order by id").list());
        } catch (Exception e) {
            System.out.println("Error al crear al pokemon --> " + e.getMessage());
        }
    }

    public void meterPokemon(String name,Date nacemento){
        try(Session session = HibernateConfig.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Pokemon pokemonNuevo = new Pokemon();
            pokemonNuevo.setNome(name);
            pokemonNuevo.setNacemento(nacemento);
            session.save(pokemonNuevo);
            transaction.commit();
            //EscribirXML.escribirPokedexXML(session.createSQLQuery("select * from pokedex order by id").list());
        } catch (Exception e) {
            System.out.println("Error al crear al pokemon --> " + e.getMessage());
        }
    }

    public void darPokemons(){

        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        PokedexServices pokedexServices = new PokedexServices();
        AdestradorServices adestradorServices = new AdestradorServices();
        List<Pokemon>lista = new ArrayList<>();
        Pokemon pokemon1 = new Pokemon("pikachu", Date.valueOf(LocalDate.of(1990, 1, 20)), pokedexServices.buscarPokemon(1), adestradorServices.buscarAdestrador(1));
        Pokemon pokemon2 = new Pokemon("gengar", Date.valueOf(LocalDate.of(1990, 1, 21)), pokedexServices.buscarPokemon(1), adestradorServices.buscarAdestrador(1));
        Pokemon pokemon3 = new Pokemon("Feraligatr", Date.valueOf(LocalDate.of(1990, 1, 22)), pokedexServices.buscarPokemon(1), adestradorServices.buscarAdestrador(1));
        Pokemon pokemon4 = new Pokemon("tinkaton", Date.valueOf(LocalDate.of(1990, 1, 23)), pokedexServices.buscarPokemon(1), adestradorServices.buscarAdestrador(1));
        Pokemon pokemon5 = new Pokemon("steelix", Date.valueOf(LocalDate.of(1990, 1, 24)), pokedexServices.buscarPokemon(1), adestradorServices.buscarAdestrador(1));
        Pokemon pokemon6 = new Pokemon("houndoom", Date.valueOf(LocalDate.of(1990, 1, 25)), pokedexServices.buscarPokemon(1), adestradorServices.buscarAdestrador(1));
        Pokemon pokemon7 = new Pokemon("sneasler", Date.valueOf(LocalDate.of(1990, 1, 26)), pokedexServices.buscarPokemon(2), adestradorServices.buscarAdestrador(2));
        Pokemon pokemon8 = new Pokemon("aerodactyl", Date.valueOf(LocalDate.of(1990, 1, 27)), pokedexServices.buscarPokemon(2), adestradorServices.buscarAdestrador(2));
        Pokemon pokemon9 = new Pokemon("charizard", Date.valueOf(LocalDate.of(1990, 1, 28)), pokedexServices.buscarPokemon(2), adestradorServices.buscarAdestrador(2));
        Pokemon pokemon10 = new Pokemon("goldengho", Date.valueOf(LocalDate.of(1990, 2, 20)), pokedexServices.buscarPokemon(2), adestradorServices.buscarAdestrador(2));
        Pokemon pokemon11 = new Pokemon("dragonite", Date.valueOf(LocalDate.of(1990, 4, 20)), pokedexServices.buscarPokemon(2), adestradorServices.buscarAdestrador(2));
        Pokemon pokemon12 = new Pokemon("gyarados", Date.valueOf(LocalDate.of(1991, 1, 20)), null, adestradorServices.buscarAdestrador(2));
        lista.add(pokemon1);
        lista.add(pokemon2);
        lista.add(pokemon3);
        lista.add(pokemon4);
        lista.add(pokemon5);
        lista.add(pokemon6);
        lista.add(pokemon7);
        lista.add(pokemon8);
        lista.add(pokemon9);
        lista.add(pokemon10);
        lista.add(pokemon11);
        lista.add(pokemon12);

        for(Pokemon pk : lista){
            session.save(pk);
        }
        transaction.commit();
        System.out.println("Pokemons guardados");
    }

    public void mostrarPK(){
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Pokemon> pokemonList = session.createQuery("from Pokemon", Pokemon.class).getResultList();
            transaction.commit();
            for(Pokemon pk: pokemonList){
                System.out.println(pk);
            }
        }
    }

}
