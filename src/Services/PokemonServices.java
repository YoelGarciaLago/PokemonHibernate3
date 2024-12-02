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
import java.sql.Date;
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

    public void listaPokemon(){
        try(Session session = HibernateConfig.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            String command = "Select * from pokedex order by id";
            SQLQuery query = session.createSQLQuery(command);
            List<Object[]> lista = crearLista(query);
            System.out.println("Lista:----------------------------------------------------");
            for (Object[] o : lista){
               // Pokemon pokemon = new Pokemon(Long.parseLong(o[0].toString()),o[1].toString(), Date.valueOf(o[2].toString()),Integer.parseInt(o[3].toString()),Integer.parseInt(o[4].toString()));
               // System.out.println("|" + pokemon + "|");
            }
            System.out.println("----------------------------------------------------");
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Object[]> crearLista(SQLQuery query) throws IOException, XMLStreamException {
        List <Object[]> lista = query.list();
        //EscribirXML.escribirPokedexXML(lista);
        return lista;
    }


}
