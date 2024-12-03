package Model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "adestradores")
public class Wrapper {

    private List<Adestrador> list ;

    @XmlElement(name = "adestrador")
    public List<Adestrador> getAdestradores(){
        return list;
    }

    public void setAdestradores(List<Adestrador> list){
        this.list = list;
    }
}
