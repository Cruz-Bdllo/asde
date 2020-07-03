package com.asde.app.apirest;

import com.asde.app.domain.Representant;
import com.asde.app.service.representant.IRepresentantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/representant")
public class RepresentantRest {
    @Autowired
    private IRepresentantService repreService;

    @GetMapping("/getById/{idRepre}")
    public Representant getRepresentant(@PathVariable Integer idRepre){
        Representant repre = repreService.getRepresentantById(idRepre);
        repre.setRfc((repre.getRfc()==null)?"Sin RFC":repre.getRfc());
        if (repre != null )
            repre.setClient(null);
        return (repre == null) ? null : repre;
    }

    @GetMapping("/existRfc/{rfc}")
    public ResponseEntity<?> existRfcRepresentant(@PathVariable String rfc) {
        boolean response = repreService.existRepresentantByRFC(rfc);
        return (response) ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/saveRepresentant")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveRepresentant(Representant representant) {

        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/updateRepresentant")
    public ResponseEntity<?> updateRepresentatn(@Valid Representant representant, Errors errors) {
        if (errors.hasErrors()) {
            List<String> errorsParse = new ArrayList<>();
            for(ObjectError error : errors.getAllErrors()) {
                errorsParse.add(error.getDefaultMessage());
            }
            return new ResponseEntity<>(errorsParse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        representant.setClient(repreService.getRepresentantById(representant.getIdRepresentant()).getClient());
        repreService.saveRepresentant(representant);
        return new ResponseEntity<>("Exito", HttpStatus.OK);
    }

}
