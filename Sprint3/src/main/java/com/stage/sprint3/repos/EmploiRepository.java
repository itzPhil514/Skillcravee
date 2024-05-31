package com.stage.sprint3.repos;

import com.stage.sprint3.entities.Emploi;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmploiRepository extends CrudRepository <Emploi, Integer> {

    @Query("SELECT e FROM Emploi e where e.titre like %?1%")
    public List<Emploi> findAll(String keyword);

    @Query("SELECT e FROM Emploi e ORDER BY e.titre ASC")
    public List<Emploi> findByOrderByTitreAsc();
    @Query("SELECT e FROM Emploi e ORDER BY e.titre DESC")
    public List<Emploi> findByOrderByTitreDesc();
}
