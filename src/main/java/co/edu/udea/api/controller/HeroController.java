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

@RestController
@RequestMapping("/heroes")
public class HeroController {

    private final Logger log = LoggerFactory.getLogger(HeroController.class);

    public HeroService heroService;

    public HeroController(HeroService heroService){
        this.heroService = heroService;
    }


    @GetMapping("{id}")
    @ApiOperation(value = "Busca un heroe por su id",  response = Hero.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroe encontrado"),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno procesando la respuesta")})//hasta aqui
    public ResponseEntity<Hero> getHero(@PathVariable Integer id){
        log.info("Rest request buscar heroe por id: "+ id);
        return ResponseEntity.ok(heroService.getHero(id));
    }

    @GetMapping()
    @ApiOperation(value = "Lista de Heroes",  response = Hero.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroe encontrado"),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno procesando la respuesta")})
   /* public List<Hero> getHeroes(){return heroService.listaHeroe(); }*/
    public ResponseEntity<List<Hero>> getHeroes(){ log.info("Rest request buscar heroes");
        return ResponseEntity.ok(heroService.listaHeroe());
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Borrar Heroe",  response = Hero.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroe Eliminado"),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno procesando la respuesta")})
    public void deleteHero(@PathVariable Integer id){
        this.heroService.borrarHeroe(id);
    }

    @PutMapping
    @ApiOperation(value = "Actualizar Heroe",  response = Hero.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroe Actualizado"),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno procesando la respuesta")})
    public void updateHero(@RequestBody Hero hero){
        this.heroService.actualizarHeroe(hero);
    }



    


}
