package com.example.swimmerservice.repository;

import com.example.swimmerservice.model.Swimmer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SwimmerRepository extends MongoRepository<Swimmer, String> {
    List<Swimmer> findBySwimmerCodeIn(List<String> swimmerCode);
}
