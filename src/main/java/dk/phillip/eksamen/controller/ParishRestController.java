package dk.phillip.eksamen.controller;

import dk.phillip.eksamen.model.Commune;
import dk.phillip.eksamen.model.Parish;
import dk.phillip.eksamen.repository.CommuneRepository;
import dk.phillip.eksamen.repository.ParishRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ParishRestController {
    private ParishRepository parishRepository;

    public ParishRestController(ParishRepository parishRepository){
        this.parishRepository = parishRepository;
    }

    @GetMapping("/parish")
    public ResponseEntity<List<Parish>> findAll(){
        //findAll recipes and return
        List<Parish> parishList = new ArrayList<>();
        for (Parish parish:parishRepository.findAll()){
            parishList.add(parish);
        }
        return ResponseEntity.status(HttpStatus.OK).body(parishList);
    }

    // HTTP Get by ID
    @GetMapping("/parish/{id}")
    public ResponseEntity<Optional<Parish>> findById(@PathVariable Long id)
    {
        Optional<Parish> optionalParish = parishRepository.findById(id);
        if (optionalParish.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(optionalParish);
        }
        else{
            //Not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(optionalParish);
        }
    }

    // HTTP Post, ie. create
    @CrossOrigin(origins = "*", exposedHeaders = "Location")
    @PostMapping(value = "/parish", consumes = "application/json")
    public ResponseEntity<Parish> create(@RequestBody Parish parish) {
        Parish newParish = parishRepository.save(parish);
        //insert location in response header
        return ResponseEntity.ok(newParish);
    }

    // HTTP PUT, ie. update
    @PutMapping("/parish/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Parish p) {

        Optional<Parish> optionalParish = parishRepository.findById(id);
        if (!optionalParish.isPresent()) {
            //Parish id findes ikke
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'message' : 'parish " + id + " is not found'");
        }

        //uses the pathvariable long Id and sets the commune id to that.
        p.setId(id);
        parishRepository.save(p);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("{ 'Message' : 'updated' }");
    }

    //HTTP DELETE
    @DeleteMapping("/parish/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        Optional<Parish> optionalParish = parishRepository.findById(id);
        if (!optionalParish.isPresent()) {
            //id findes ikke
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'msg' : 'parish " + id + " not found'}");
        }
        parishRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("{ 'msg' : 'deleted' }");
    }

}
