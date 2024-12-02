package Services;

import Config.HibernateConfig;
import Model.Adestrador;
import Resources.EscribirXML;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.sql.Date;
import java.util.List;


public class AdestradorServices {

    public void meterAdestrador(String nome, Date dataNacemento){
        try(Session session = HibernateConfig.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Adestrador adestrador = new Adestrador();
            adestrador.setNome(nome);
            adestrador.setNacemento(dataNacemento);
            session.save(adestrador);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Erro ao inserir na taboa --> " + e.getMessage());
        }
    }

    public void listarAdestradores(){
        try (Session session = HibernateConfig.getSessionFactory().openSession()){
            SQLQuery query = session.createSQLQuery("select * from adestrador order by id");
            List<Object[]> lista = crearLista(query);
            System.out.println("Lista:----------------------------------------------------");
            for(Object[]o : lista){
                Adestrador adestrador = new Adestrador(Long.parseLong(o[0].toString()),o[1].toString(),Date.valueOf(o[2].toString()));
                System.out.println("|" + adestrador + "|");
            }
            System.out.println("----------------------------------------------------");
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void modificarAdestradorNome(Long id, String name){
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Adestrador adestrador = session.get(Adestrador.class,id);
            if(adestrador != null){
                adestrador.setNome(name);
                session.update(adestrador);
                transaction.commit();
                actualizarTabla(session);
            }else
                System.out.println("Non existe un adestrador con ese id");

        } catch (Exception e) {
            System.out.println("Erro ao modificar o campo");
        }
    }

    public void modificarAdestradorDataNacemento(Long id, Date data){
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Adestrador adestrador = session.get(Adestrador.class,id);
            if(adestrador != null){
                adestrador.setNacemento(data);
                session.update(adestrador);
                transaction.commit();
                actualizarTabla(session);
            }else
                System.out.println("Non existe un adestrador con ese id");

        } catch (Exception e) {
            System.out.println("Erro ao modificar o campo");
        }
    }

    public void borrarTabla() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            NativeQuery query = session.createNativeQuery("Delete from adestrador *");
            query.executeUpdate();
            transaction.commit();
            actualizarXml(session.createSQLQuery("select * from pokedex order by id").list());
        }catch (Exception e){
            System.out.println("Error al borrar la tabla --> " + e.getMessage());
        }
    }
    private static List<Object[]> crearLista(SQLQuery query) throws IOException, XMLStreamException {
        List<Object[]> lista = query.list();

        EscribirXML.escribirAdestradoresXML(lista);
        return lista;
    }

    private static void actualizarTabla(Session session) throws IOException, XMLStreamException {
        List<Object[]> lista = session.createSQLQuery("select * from adestrador order by id").list();
        EscribirXML.escribirAdestradoresXML(lista);
    }

    private static void actualizarXml(List session) throws IOException, XMLStreamException {
        List<Object[]> lista = session;
        EscribirXML.escribirAdestradoresXML(lista);
    }
}
