package com.example.spring.boot.demo.spring.demo.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.boot.demo.spring.demo.exceptions.BondException;
import com.example.spring.boot.demo.spring.demo.models.Bond;
import com.example.spring.boot.demo.spring.demo.persistence.BondRepository;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
public class BondController {

    @Autowired
    private ResourceAssembler<Bond, Resource<Bond>> bondAssembler;

    private final BondRepository bondRepository;

    public BondController(BondRepository bondRepository) {
	this.bondRepository = bondRepository;
    }

//    curl -v localhost:8080/bonds -u jimmy:pass
    @GetMapping("/bonds")
    public Resources<Resource<Bond>> getAll() {
	List<Resource<Bond>> bonds = bondRepository.findAll().stream().map(bondAssembler::toResource)
		.collect(Collectors.toList());
	return new Resources<Resource<Bond>>(bonds, linkTo(methodOn(BondController.class).getAll()).withSelfRel());
    }

//    curl -X POST localhost:8080/bonds -H 'Content-type:application/json' -d '{"bondName": "LVMH 19/23", "isin": "FR0013405347", "depth" : "560", "price" : "64.16"}' -u admin:pass
    @PostMapping("/bonds")
    public ResponseEntity<?> newBond(@RequestBody Bond newBond) throws URISyntaxException {
	Resource<Bond> bond = bondAssembler.toResource(bondRepository.save(newBond));
	return ResponseEntity.created(new URI(bond.getId().expand().getHref())).body(bond);
    }

//    curl -v localhost:8080/bonds/1 -u jimmy:pass
    @GetMapping("/bonds/{id}")
    public Resource<Bond> getBondById(@PathVariable Long id) {
	return bondAssembler.toResource(
		bondRepository.findById(id).orElseThrow(() -> new BondException("ID = " + id + " not found.")));
    }

//    curl -X DELETE localhost:8080/bonds/4 -u admin:pass
    @DeleteMapping("/bonds/{id}")
    public ResponseEntity<?> deleteBond(@PathVariable Long id) {
	bondRepository.deleteById(id);
	return ResponseEntity.noContent().build();
    }

//    CURL command similar to POST
    @PutMapping("/bonds/{id}")
    public ResponseEntity<?> replaceEmployee(@RequestBody Bond newBond, @PathVariable Long id) throws URISyntaxException {
	Bond updatedBond = bondRepository.findById(id).map(bond -> {
	    bond.setBondName(newBond.getBondName());
	    bond.setIsin(newBond.getIsin());
	    bond.setDepth(newBond.getDepth());
	    bond.setPrice(newBond.getPrice());
	    return bondRepository.save(bond);
	}).orElseGet(() -> {
	    newBond.setId(id);
	    return bondRepository.save(newBond);
	});
	Resource<Bond> resource = bondAssembler.toResource(updatedBond);
	return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
    }
}
