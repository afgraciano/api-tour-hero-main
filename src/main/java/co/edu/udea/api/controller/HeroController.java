package co.edu.udea.api.controller;

import co.edu.udea.api.model.Hero;
import co.edu.udea.api.service.HeroService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//nueva version con excepciones personalizadas:

@RestController
@RequestMapping("/heroes")
public class HeroController {

    private final HeroService heroService;

    // Constructor del controlador que recibe una instancia de HeroService
    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    // Endpoint para obtener un héroe por su ID
    @GetMapping("{id}")
    @ApiOperation(value = "Busca un héroe por su ID", response = Hero.class)
    public ResponseEntity<Hero> getHero(@PathVariable Integer id) {
        // Llama a la función del servicio para obtener un héroe por su ID
        Hero hero = heroService.getHero(id);
        return ResponseEntity.ok(hero);
    }

    // Endpoint para obtener la lista de héroes
    @GetMapping()
    @ApiOperation(value = "Lista de Héroes", response = Hero.class)
    public ResponseEntity<List<Hero>> getHeroes() {
        // Llama a la función del servicio para obtener la lista de héroes
        List<Hero> heroes = heroService.listaHeroe();
        return ResponseEntity.ok(heroes);
    }

    // Endpoint para borrar un héroe por su ID
    @DeleteMapping("{id}")
    @ApiOperation(value = "Borrar Héroe", response = Hero.class)
    public ResponseEntity<String> deleteHero(@PathVariable Integer id) {
        // Llama a la función del servicio para borrar un héroe por su ID
        heroService.borrarHeroe(id);
        return ResponseEntity.ok("Héroe eliminado exitosamente");
    }

    // Endpoint para actualizar un héroe
    @PutMapping
    @ApiOperation(value = "Actualizar Héroe", response = Hero.class)
    public ResponseEntity<String> updateHero(@RequestBody Hero hero) {
        // Llama a la función del servicio para actualizar un héroe
        heroService.actualizarHeroe(hero);
        return ResponseEntity.ok("Héroe actualizado exitosamente");
    }

    // Endpoint para buscar héroes por un término en el nombre
    @GetMapping("/")
    @ApiOperation(value = "Mostrar Búsqueda", response = Hero.class)
    public ResponseEntity<List<Hero>> searchHeroes(@RequestParam("name") String term) {
        // Llama a la función del servicio para buscar héroes por un término en nombre
        List<Hero> heroes = heroService.buscarHeroe(term);
        return ResponseEntity.ok(heroes);
    }

    // Endpoint para añadir un nuevo héroe
    @PostMapping
    @ApiOperation(value = "Añadir Héroe", response = Hero.class)
    public ResponseEntity<Hero> addHero(@RequestBody Hero hero) {
        // Llama a la función del servicio para añadir un nuevo héroe
        Hero addedHero = heroService.adicionarHeroe(hero);
        return ResponseEntity.ok(addedHero);
    }
}

/*
 * version anterior
 * 
 * @RestController
 * 
 * @RequestMapping("/heroes")
 * public class HeroController {
 * 
 * // Crear un registro de log para registrar eventos
 * private final Logger log = LoggerFactory.getLogger(HeroController.class);
 * 
 * // Instanciar el servicio de héroes
 * public HeroService heroService;
 * 
 * // Constructor que recibe el servicio de héroes
 * public HeroController(HeroService heroService) {
 * this.heroService = heroService;
 * }
 * 
 * // Obtiene un héroe por su ID
 * 
 * @GetMapping("{id}")
 * 
 * @ApiOperation(value = "Busca un heroe por su id", response = Hero.class)
 * 
 * @ApiResponses(value = {
 * 
 * @ApiResponse(code = 200, message = "Heroe encontrado"),
 * 
 * @ApiResponse(code = 400, message = "La petición es invalida"),
 * 
 * @ApiResponse(code = 500, message = "Error interno procesando la respuesta")
 * }) // hasta aqui
 * public ResponseEntity<Hero> getHero(@PathVariable Integer id) {
 * // Registrar el evento de búsqueda de un héroe por ID
 * log.info("Rest request buscar heroe por id: " + id);
 * return ResponseEntity.ok(heroService.getHero(id));
 * }
 * 
 * // Obtiene la lista de héroes
 * 
 * @GetMapping()
 * 
 * @ApiOperation(value = "Lista de Heroes", response = Hero.class)
 * 
 * @ApiResponses(value = {
 * 
 * @ApiResponse(code = 200, message = "Heroes Encontrados"),
 * 
 * @ApiResponse(code = 400, message = "La petición es invalida"),
 * 
 * @ApiResponse(code = 500, message = "Error interno procesando la respuesta")
 * })
 * // public List<Hero> getHeroes(){return heroService.listaHeroe(); }
 * public ResponseEntity<List<Hero>> getHeroes() {
 * // Registrar el evento de búsqueda de todos los héroes
 * log.info("Rest request buscar heroes");
 * return ResponseEntity.ok(heroService.listaHeroe());
 * }
 * 
 * // Borra un héroe por su ID
 * 
 * @DeleteMapping("{id}")
 * 
 * @ApiOperation(value = "Borrar Heroe", response = Hero.class)
 * 
 * @ApiResponses(value = {
 * 
 * @ApiResponse(code = 200, message = "Heroe Eliminado"),
 * 
 * @ApiResponse(code = 400, message = "La petición es invalida"),
 * 
 * @ApiResponse(code = 500, message = "Error interno procesando la respuesta")
 * })
 * public void deleteHero(@PathVariable Integer id) {
 * // Llama al servicio para borrar un héroe por su ID
 * this.heroService.borrarHeroe(id);
 * }
 * 
 * // Actualiza un héroe
 * 
 * @PutMapping
 * 
 * @ApiOperation(value = "Actualizar Heroe", response = Hero.class)
 * 
 * @ApiResponses(value = {
 * 
 * @ApiResponse(code = 200, message = "Heroe Actualizado"),
 * 
 * @ApiResponse(code = 400, message = "La petición es invalida"),
 * 
 * @ApiResponse(code = 500, message = "Error interno procesando la respuesta")
 * })
 * public void updateHero(@RequestBody Hero hero) {
 * // Llama al servicio para actualizar un héroe
 * this.heroService.actualizarHeroe(hero);
 * }
 * 
 * // Muestra la búsqueda de héroes por un término
 * 
 * @GetMapping("/")
 * 
 * @ApiOperation(value = "Mostrar  Busqueda", response = Hero.class)
 * 
 * @ApiResponses(value = {
 * 
 * @ApiResponse(code = 200, message = "Heroe Encontrado"),
 * 
 * @ApiResponse(code = 400, message = "La petición es invalida"),
 * 
 * @ApiResponse(code = 500, message = "Error interno procesando la respuesta")
 * })
 * public List<Hero> searchHeroes(@RequestParam("name") String term) {
 * // Imprime el término de búsqueda y llama al servicio para buscar héroes
 * System.out.println(term + "  esto es el termino");
 * return heroService.buscarHeroe(term);
 * }
 * 
 * // Añade un nuevo héroe
 * 
 * @PostMapping
 * 
 * @ApiOperation(value = "Adicionar Heroe", response = Hero.class)
 * 
 * @ApiResponses(value = {
 * 
 * @ApiResponse(code = 200, message = "Heroe Adicionado"),
 * 
 * @ApiResponse(code = 400, message = "La petición es invalida"),
 * 
 * @ApiResponse(code = 500, message = "Error interno procesando la respuesta")
 * })
 * public Hero addHero(@RequestBody Hero hero) {
 * // Llama al servicio para añadir un nuevo héroe y devuelve el héroe añadido
 * return this.heroService.adicionarHeroe(hero);
 * }
 * 
 * }
 */
