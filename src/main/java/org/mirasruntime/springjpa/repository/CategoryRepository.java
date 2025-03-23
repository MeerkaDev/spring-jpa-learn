package org.mirasruntime.springjpa.repository;

import org.mirasruntime.springjpa.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
