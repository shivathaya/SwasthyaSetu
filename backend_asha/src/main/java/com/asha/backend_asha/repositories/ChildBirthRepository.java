package com.asha.backend_asha.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.asha.backend_asha.models.ChildBirth;

public interface ChildBirthRepository extends JpaRepository<ChildBirth, Long> {

	List<ChildBirth> findByUserId(Long id);
}
