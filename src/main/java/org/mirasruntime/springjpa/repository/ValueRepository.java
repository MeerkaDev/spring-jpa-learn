package org.mirasruntime.springjpa.repository;

import org.mirasruntime.springjpa.model.Value;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValueRepository extends JpaRepository<Value, Long> {
}
