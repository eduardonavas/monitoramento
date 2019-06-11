package br.com.vivo.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.vivo.model.Servidor;

@Repository
public interface ServidorRepository extends CrudRepository<Servidor, Integer> {

	public List<Servidor> findAll();
	public Servidor findById(int id);
	
	@Query("SELECT s FROM Servidor s WHERE s.ip = :ip")
	public Servidor findByIp(@Param("ip") String ip);
}
