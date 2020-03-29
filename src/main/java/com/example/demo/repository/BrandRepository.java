package com.example.demo.repository;

import com.example.demo.entity.Brand;
import com.example.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.Date;
@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
//    @Query(value = "SELECT * FROM brand u WHERE u.name = :name AND u.createdDate = date", nativeQuery = true)

    public Brand findByName(String name);
    public Page<Brand> findByNameContaining( String name , Pageable pageable);
    public Page<Brand> findByCreatedDateContaining(Date createdDate , Pageable pageable);
}
