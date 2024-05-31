package com.stage.sprint3.repos;

import com.stage.sprint3.entities.Etudiant;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtudiantRepository extends CrudRepository<Etudiant, Integer> {
    public Long countById(Integer id);

    @Query("SELECT e FROM Etudiant e where e.nom like %?1% or e.prenom like %?1%")
    public List<Etudiant> findAll(String keyword);

    @Query("UPDATE Etudiant e set e.statusStage = ?2 where e.id = ?1")
    @Modifying
    public void updateStatus(Integer id, boolean statusStage);

    Etudiant findById(int id);


    @Query("SELECT e FROM Etudiant e WHERE e.email = :email AND e.password = :password")
    Etudiant findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    @Query("SELECT u FROM Etudiant u WHERE u.CV = :fileName")
    public List<Etudiant> findByFileName(@Param("fileName") String fileName);

    @Query("SELECT s.CV FROM Etudiant s WHERE s.id = :id")
    byte[] getCVById(@Param("id") Long id);

    @Query("SELECT e FROM Etudiant e WHERE e.email = :email")
    public Etudiant getEtudiantByEmail(@Param("email") String email);
}
