package com.bilalov.regservice.repositories;

import com.bilalov.regservice.entity.DeletedRecords;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeletedRecordsRepository extends JpaRepository<DeletedRecords, Long> {
}
