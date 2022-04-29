package com.databasepi.databasepi.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.databasepi.databasepi.models.Empresa;

public interface EmpresaRepository extends CrudRepository<Empresa, Long> {
	
	Empresa findById(long id);
	Empresa findByNome(String nome);
	
	// Query para a busca
	@Query(value = "select u from Empresa u where u.nome like %?1%")
	List<Empresa> findByNomesEmpresa(String nome);

}
