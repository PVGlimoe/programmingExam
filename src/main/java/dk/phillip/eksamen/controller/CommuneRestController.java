package dk.phillip.eksamen.controller;

import dk.phillip.eksamen.model.Commune;
import dk.phillip.eksamen.repository.CommuneRepository;
import dk.phillip.eksamen.repository.ParishRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CommuneRestController {

    private CommuneRepository communeRepository;

    public CommuneRestController(CommuneRepository communeRepository){
        this.communeRepository = communeRepository;
    }

    @GetMapping("/commune")
    public ResponseEntity<List<Commune>> findAll(){
        //findAll recipes and return
        List<Commune> communeList = new ArrayList<>();
        for (Commune commune:communeRepository.findAll()){
            communeList.add(commune);
        }
        return ResponseEntity.status(HttpStatus.OK).body(communeList);
    }

    // HTTP Get by ID
    @GetMapping("/commune/{id}")
    public ResponseEntity<Optional<Commune>> findById(@PathVariable Long id)
    {
        Optional<Commune> optionalCommune = communeRepository.findById(id);
        if (optionalCommune.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(optionalCommune);
        }
        else{
            //Not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(optionalCommune);
        }
    }

    // HTTP Post, ie. create
    @CrossOrigin(origins = "*", exposedHeaders = "Location")
    @PostMapping(value = "/commune", consumes = "application/json")
    public ResponseEntity<Commune> create(@RequestBody Commune commune) {
        Commune newCommune = communeRepository.save(commune);
        //insert location in response header
        return ResponseEntity.ok(newCommune);
    }

    // HTTP PUT, ie. update
    @PutMapping("/commune/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Commune c) {
        //get recipeById
        Optional<Commune> optionalCommune = communeRepository.findById(id);
        if (!optionalCommune.isPresent()) {
            //Commune id findes ikke
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'message' : 'commune " + id + " is not found'");
        }

        //uses the pathvariable long Id and sets the commune id to that.
        c.setId(id);
        communeRepository.save(c);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("{ 'Message' : 'updated' }");
    }

    //HTTP DELETE
    @DeleteMapping("/commune/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        Optional<Commune> optionalCommune = communeRepository.findById(id);
        if (!optionalCommune.isPresent()) {
            //id findes ikke
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'msg' : 'commune " + id + " not found'}");
        }
        communeRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("{ 'msg' : 'deleted' }");
    }

}
