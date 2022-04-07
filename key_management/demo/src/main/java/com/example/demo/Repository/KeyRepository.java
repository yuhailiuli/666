package com.example.demo.Repository;

import com.example.demo.Model.Key;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeyRepository extends JpaRepository<Key, Long> {
    Key getById(Long id);
}
