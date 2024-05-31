package com.stage.sprint3.repos;

import com.stage.sprint3.entities.Administration;
import com.stage.sprint3.entities.Entreprise;
import com.stage.sprint3.entities.Etudiant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdministrationRepository extends CrudRepository <Administration, Integer> {
    public Long countById(Integer id);

    @Query("SELECT a FROM Administration a where a.nom like %?1% or a.prenom like %?1%")
    public List<Administration> findAll(String keyword);

    @Query("SELECT e FROM Administration e where e.email like %?1% and e.password like %?2%")
    Administration findByEmailAndPassword(String email, String password);
}
