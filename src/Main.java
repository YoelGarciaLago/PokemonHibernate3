import Model.Adestrador;
import Services.AdestradorServices;
import Services.PokedexServices;
import Services.PokemonServices;

import java.sql.Date;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        PokedexServices pokedexServices = new PokedexServices();
        AdestradorServices adestradorServices = new AdestradorServices();
        PokemonServices pokemonServices = new PokemonServices();

//        pokedexServices.meterEntrada("pokemon1",1,"pokemon1");
//        pokedexServices.meterEntrada("pokemon2",2,"pokemon1");
//        pokedexServices.meterEntrada("pokemon3",3,"");
//        pokedexServices.meterEntrada("pokemon4",4,"");
//        pokedexServices.meterEntrada("pokemon5",5,"");
//        pokedexServices.meterEntrada("pokemo6",2,"pokemon1");
//        pokedexServices.meterEntrada("pokemon7",2,"pokemon1");
//        pokedexServices.meterEntrada("pokemon8",2,"pokemon1");
//        pokedexServices.meterEntrada("pokemon9",2,"pokemon1");
//        pokedexServices.meterEntrada("pokemon10",2,"pokemon1");
//
//        adestradorServices.meterAdestrador("uuu", Date.valueOf("2020-02-02"));
//        adestradorServices.meterAdestrador("aaaa", Date.valueOf("2020-02-03"));
//
//
//
//        pokemonServices.darPokemons();




        //pokedexServices.exportarPokedex();

        adestradorServices.exportarAdestradoresXML();
        List<Adestrador> lista = adestradorServices.importarAdestradoresXML();
        for(Adestrador a : lista){
            System.out.println(a);
        }
      //  pokedexServices.lecturaDAT();
    }
}