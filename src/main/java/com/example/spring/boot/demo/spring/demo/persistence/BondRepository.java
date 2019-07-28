package com.example.spring.boot.demo.spring.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.boot.demo.spring.demo.models.Bond;

public interface BondRepository extends JpaRepository<Bond, Long> {
}
