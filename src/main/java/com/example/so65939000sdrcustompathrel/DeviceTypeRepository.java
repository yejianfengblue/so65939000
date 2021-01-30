package com.example.so65939000sdrcustompathrel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "types", collectionResourceRel = "types")
public interface DeviceTypeRepository extends JpaRepository<DeviceType, Long> {
}
