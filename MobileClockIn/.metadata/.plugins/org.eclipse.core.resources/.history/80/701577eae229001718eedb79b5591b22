package ua.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ua.server.model.Category;

import java.util.List;

@Repository(value = "CategoryRepository")
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("FROM CATEGORY where CATEGORY_NAME like %?1% ")
    List<Category> findByName(String name);
}
