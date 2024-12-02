package Services;

import Config.HibernateConfig;
import Model.DTO.PokedexDTO;
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
    private ArrayList<PokedexDTO> pkArrayList = new ArrayList<>();

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
                actualizarXml(session.createSQLQuery("select * from pokedex order by id").list());
            }else
                System.out.println("No existe la entrada con ese id");
        } catch (Exception e) {
            System.out.println("Error al actualizar la entrada");
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


    public void serializarEntrada(long idPokedex){
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            Pokedex select = session.get(Pokedex.class,idPokedex);
            try {
                escrituraAlDat(select);
            } catch (Exception e) {
                System.out.println("Error al serializar --> " + e.getMessage());
            }
        }catch (Exception e){
            System.out.println("Error al serializar: " + e.getMessage());
        }
    }

    private static void escrituraAlDat(Pokedex select) throws IOException {
        FileOutputStream fos = new FileOutputStream("pokedex.dat",true);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(new PokedexDTO(select.getId(), select.getNome(), select.getPeso(), select.getMisc()));
        oos.flush();
        oos.close();
    }

    public void leerSerializado(){
        try{
            FileInputStream fis = new FileInputStream("pokedex.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            PokedexDTO pk;

            while((pk = (PokedexDTO) ois.readObject()) != null){
                System.out.println(pk);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error --> " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al leer --> " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void borrarTabla() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            NativeQuery query = session.createNativeQuery("Delete from pokedex *");
            query.executeUpdate();
            transaction.commit();
            actualizarXml(session.createSQLQuery("select * from pokedex order by id").list());
        }catch (Exception e){
            System.out.println("Error al borrar la tabla --> " + e.getMessage());
        }
    }
}
