package com.desmond.recycle_backend.repository;

import com.desmond.recycle_backend.models.Need;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NeedRepository extends JpaRepository<Need, Long> {
}
