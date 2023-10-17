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

    // Registro de log para registrar eventos
    private final Logger log = LoggerFactory.getLogger(HeroService.class);

    // Referencia al repositorio de héroes para acceder a la base de datos
    private HeroRepository heroRepository;

    // private final Messages messages;

    // Constructor del servicio que recibe una instancia de HeroRepository
    public HeroService(HeroRepository heroRepository)// public HeroService(HeroRepository heroRepository, Messages
                                                     // messages)

    {
        this.heroRepository = heroRepository;
        // this.messages = messages;// Comentado: Dependencia de Messages
    }

    // Obtiene un héroe por su ID
    public Hero getHero(Integer id) {
        Optional<Hero> optionalHero = heroRepository.findById(id);
        if (!optionalHero.isPresent()) {
            log.info("No se encuentra un heroe con ID: " + id);
            throw new ExcepcionesNegocio("El heroe no existe");// TODO Throw exception
        }
        return optionalHero.get();
    }

    //Obtiene la lista de héroes
    /* public List<Hero> getHeroes(){return heroRepository.findAll();} */
    public List<Hero> listaHeroe() {
        return heroRepository.findAll();
    }

    // Agrega la funcionalidad para borrar, actualizar, buscar y añadir héroes

    // Borra un héroe por su ID
    public void borrarHeroe(Integer id) {
        if (!heroRepository.existsById(id)) {
            throw new ExcepcionesNegocio("El héroe no existe");
        }
        heroRepository.deleteById(id);
    }

    // Actualiza la información de un héroe
    public void actualizarHeroe(Hero hero) {
        heroRepository.findById(hero.getId()).ifPresent(hero1 -> {
            hero1.setName(hero.getName());
            heroRepository.save(hero1);
        });
    }

    // Busca héroes por un término en el nombre
    public List<Hero> buscarHeroe(String term) {
        return heroRepository.findByNameContaining(term);
    }

    // Añade un nuevo héroe a la base de datos
    public Hero adicionarHeroe(Hero hero) {
        return heroRepository.save(hero);
    }

}
