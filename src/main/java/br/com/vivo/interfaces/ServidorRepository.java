package br.com.vivo.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.vivo.model.Servidor;

@Repository
public interface ServidorRepository extends CrudRepository<Servidor, Integer> {

	public List<Servidor> findAll();
	public Servidor findById(int id);
	
	@Modifying
	@Query("UPDATE Servidor s SET s.status = :status, s.tempoMedioPing = :tempo WHERE s.id = :id")
	public void updateStatus(@Param("id") int id, @Param("status") char status, @Param("tempo") long tempo);
	
	@Query("SELECT s FROM Servidor s WHERE s.ip = :ip")
	public Servidor findByIp(@Param("ip") String ip);
}
