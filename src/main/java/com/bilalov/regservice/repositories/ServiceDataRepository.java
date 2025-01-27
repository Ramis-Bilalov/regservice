package com.bilalov.regservice.repositories;

import com.bilalov.regservice.entity.ServiceData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceDataRepository extends JpaRepository<ServiceData, Long> {

}