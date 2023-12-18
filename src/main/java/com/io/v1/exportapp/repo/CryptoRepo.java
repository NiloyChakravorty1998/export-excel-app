package com.io.v1.exportapp.repo;

import com.io.v1.exportapp.model.CryptoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoRepo extends JpaRepository<CryptoEntity, Integer> {

}
