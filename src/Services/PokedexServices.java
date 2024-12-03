package Services;

import Config.HibernateConfig;
import Model.Pokedex;
import Resources.EscribirXML;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class PokedexServices {

    public void meterEntrada(String nome, int peso, String misc){
        try(Session session = HibernateConfig.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Pokedex pokemonNuevo = new Pokedex();
            pokemonNuevo.setNome(nome);
            pokemonNuevo.setPeso(peso);
            pokemonNuevo.setMisc(misc);
            session.save(pokemonNuevo);
            transaction.commit();
            EscribirXML.escribirPokedexXML(session.createSQLQuery("select * from pokedex order by id").list());
        } catch (Exception e) {
            System.out.println("Error al crear al pokemon --> " + e.getMessage());
        }
    }

    public void borrarPokemon(Long id){
        try(Session session = HibernateConfig.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Pokedex pokedex = session.get(Pokedex.class,id);
            if (pokedex != null){
                session.delete(pokedex);
                transaction.commit();
            }else{
                System.out.println("No existe ese id la pokedex");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar la entrada --> " + e.getMessage());
        }
    }

    public Pokedex buscarPokemon(long id){
        Session session = HibernateConfig.getSessionFactory().openSession();
        return session.get(Pokedex.class,id);
    }

    public void listaPokemon(){
        try(Session session = HibernateConfig.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            String command = "Select * from pokedex order by id";
            SQLQuery query = session.createSQLQuery(command);
            List<Object[]> lista = crearLista(query);
            System.out.println("Lista:----------------------------------------------------");
            for (Object[] o : lista){
                Pokedex pokedex1 = new Pokedex(Long.parseLong(o[0].toString()),o[1].toString(),Double.parseDouble(o[2].toString()),o[3].toString());
                System.out.println("|" + pokedex1 + "|");
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
        EscribirXML.escribirPokedexXML(lista);
        return lista;
    }

    public void actualizarPokemonName (Long id, String name){
        try(Session session = HibernateConfig.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Pokedex pokedex = session.get(Pokedex.class,id);
            if(pokedex != null){
                pokedex.setNome(name);
                session.update(pokedex);
                transaction.commit();
                actualizarXml(session.createSQLQuery("select * from pokedex order by id").list());
            }else
                System.out.println("No existe la entrada con ese id");
        } catch (Exception e) {
            System.out.println("Error al actualizar la entrada");
        }
    }

    private static void actualizarXml(List session) throws IOException, XMLStreamException {
        List<Object[]> lista = session;
        EscribirXML.escribirPokedexXML(lista);
    }

    public void actualizarPokemonMisc (Long id, String misc){
        try(Session session = HibernateConfig.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Pokedex pokedex = session.get(Pokedex.class,id);
            if(pokedex != null){
                pokedex.setMisc(misc);
                session.update(pokedex);
                transaction.commit();
                //actualizarXml(session.createSQLQuery("select * from pokedex order by id").list());
            }else
                System.out.println("No existe la entrada con ese id");
        } catch (Exception e) {
            System.out.println("Error al actualizar la entrada " + e.getMessage());
        }
    }
    public void actualizarPokemonPeso (Long id, double peso){
        try(Session session = HibernateConfig.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Pokedex pokedex = session.get(Pokedex.class,id);
            if(pokedex != null){
                pokedex.setPeso(peso);
                session.update(pokedex);
                transaction.commit();
                actualizarXml(session.createSQLQuery("select * from pokedex order by id").list());
            }else
                System.out.println("No existe la entrada con ese id");
        } catch (Exception e) {
            System.out.println("Error al actualizar la entrada");
        }
    }


//public void escrituraDAT(){
//        List<Pokedex> lista = new ArrayList<>();
//        lista.add(buscarPokemon(13L));
//        lista.add(buscarPokemon(14L));
//
//    try {
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("poke.dat"));
//        for(int i = 0; i < 2; i++){
//            oos.writeObject(lista.get(i));
//        }
//        oos.close();
//        System.out.println("Entradas serializadas");
//
//    } catch (IOException e) {
//        throw new RuntimeException(e);
//        }
//
//    }
//
//    public void lecturaDAT(){
//        try {
//            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("poke.dat"));
//            Pokedex pokedex = (Pokedex) ois.readObject();
//            while (pokedex != null){
//                pkArrayList.add(pokedex);
//                pokedex = (Pokedex) ois.readObject();
//            }
//        } catch (IOException | ClassNotFoundException e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//    }

    public void exportarPokedex() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            List<Pokedex> pokedexList = session.createQuery("FROM Pokedex", Pokedex.class)
                    .setMaxResults(2)
                    .list();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("pokedex.dat"))) {
                oos.writeObject(pokedexList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Pokedex> importarPokedex() {
        List<Pokedex> pokedexList = List.of();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("pokedex.dat"))) {
             pokedexList = (List<Pokedex>) ois.readObject();
            try (Session session = HibernateConfig.getSessionFactory().openSession()) {
                Transaction tx = session.beginTransaction();
                for (Pokedex pokedex : pokedexList) {
                    session.update(pokedex);
                }
                tx.commit();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return pokedexList;
    }

    public void borrarTabla() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            NativeQuery query = session.createNativeQuery("Delete from pokedex *");
            query.executeUpdate();
            transaction.commit();
           // actualizarXml(session.createSQLQuery("select * from pokedex order by id").list());
        }catch (Exception e){
            System.out.println("Error al borrar la tabla --> " + e.getMessage());
        }
    }
}
