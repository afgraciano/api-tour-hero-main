package co.edu.udea.api.service;

import co.edu.udea.api.excepciones.ExcepcionesNegocio;
import co.edu.udea.api.model.Hero;
import co.edu.udea.api.repository.HeroRepository;
import co.edu.udea.api.util.i18n.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeroService {

    private final Logger log = LoggerFactory.getLogger(HeroService.class);

    private HeroRepository heroRepository;

    //private final Messages messages;

    public HeroService(HeroRepository heroRepository)//public HeroService(HeroRepository heroRepository, Messages messages)
    {
        this.heroRepository = heroRepository;
       // this.messages = messages;
    }
    public Hero getHero(Integer id){
        Optional<Hero> optionalHero = heroRepository.findById(id);
        if(!optionalHero.isPresent()){
            log.info("No se encuentra un heroe con ID: "+id);
            throw new ExcepcionesNegocio("El heroe no existe");//TODO Throw exception
        }
        return optionalHero.get();
    }

    /*public List<Hero> getHeroes(){return heroRepository.findAll();}*/
    public List<Hero> listaHeroe() {
        return heroRepository.findAll();
    }

    //agrego borrar, actualizar, buscar, adicionar heroe
    
    public void borrarHeroe(Integer id) {
        if (!heroRepository.existsById(id)) {
            throw new ExcepcionesNegocio("El héroe no existe");
        }
        heroRepository.deleteById(id);
    }




}
