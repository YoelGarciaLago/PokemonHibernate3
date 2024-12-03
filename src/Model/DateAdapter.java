package Model;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateAdapter extends XmlAdapter<String, Date> {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Ajusta el formato según sea necesario

    @Override
    public Date unmarshal(String v) throws Exception {
        if (v != null && !v.isEmpty()) {
            return new Date(dateFormat.parse(v).getTime()); // Convierte el String a java.sql.Date
        }
        return null; // Si el valor es nulo o vacío, devuelve null
    }

    @Override
    public String marshal(Date v) throws Exception {
        return v != null ? dateFormat.format(v) : null; // Convierte java.sql.Date a String
    }
}
