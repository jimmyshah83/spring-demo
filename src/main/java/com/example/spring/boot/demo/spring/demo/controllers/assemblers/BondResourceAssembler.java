package com.example.spring.boot.demo.spring.demo.controllers.assemblers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.example.spring.boot.demo.spring.demo.controllers.BondController;
import com.example.spring.boot.demo.spring.demo.models.Bond;

@Component
public class BondResourceAssembler implements ResourceAssembler<Bond, Resource<Bond>> {

    @Override
    public Resource<Bond> toResource(Bond bond) {
	return new Resource<Bond>(bond, linkTo(methodOn(BondController.class).getBondById(bond.getId())).withSelfRel(),
		linkTo(methodOn(BondController.class).getAll()).withRel("bonds"));
    }

}
