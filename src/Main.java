import Model.Pokedex;
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
//        pokemonServices.meterPokemon("pokemon1",Date.valueOf("2021-03-03"),1,1);
//        pokemonServices.meterPokemon("pokemon2",Date.valueOf("2021-03-04"),2,1);
//        pokemonServices.meterPokemon("pokemon3",Date.valueOf("2021-03-05"),3,1);
//        pokemonServices.meterPokemon("pokemon4",Date.valueOf("2021-03-06"),4,1);
//        pokemonServices.meterPokemon("pokemon5",Date.valueOf("2021-03-07"),5,1);
//        pokemonServices.meterPokemon("pokemon6",Date.valueOf("2021-03-08"),6,1);
//
//
//        pokemonServices.meterPokemon("pokemon7",Date.valueOf("2021-03-03"),7,2);
//        pokemonServices.meterPokemon("pokemon8",Date.valueOf("2021-03-03"),8,2);
//        pokemonServices.meterPokemon("pokemon9",Date.valueOf("2021-03-03"),9,2);
//        pokemonServices.meterPokemon("pokemon10",Date.valueOf("2021-03-03"),10,2);
//        pokemonServices.meterPokemon("pokemon11",2,Date.valueOf("2021-03-03"));
//        pokemonServices.meterPokemon("pokemon12",2,Date.valueOf("2021-03-03"));


        //pokemonServices.darPokemons();
        //pokemonServices.mostrarPK();
//        pokedexServices.listaPokemon();
//        adestradorServices.listarAdestradores();


        //Me da error al leer el objeto serializado
        /*
       pokedexServices.serializarEntrada(1);
       pokedexServices.serializarEntrada(2);
        pokedexServices.leerSerializado();
*/
        //pokedexServices.escrituraDAT();
        //pokedexServices.exportarPokedex();

        //adestradorServices.exportarAdestradoresXML();
        //adestradorServices.importarAdestradoresXML();
      //  pokedexServices.lecturaDAT();
    }
}