package com.ust.item.mdm.item.repo;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.ust.item.mdm.dto.Metadata;

/**
 * 
 * selectively exposing methods
 * 
 */
@Repository
public interface MetadataRepository extends org.springframework.data.repository.CrudRepository<Metadata, String> {

	public Collection<Metadata> getMetadataBySpecification(String specification);

	public Metadata getMetadataById(String id);

	public Iterable<Metadata> findAll();
}
