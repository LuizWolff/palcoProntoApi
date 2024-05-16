package com.palco.palcoprontoespacolist.control;

import com.palco.palcoprontoespacolist.entity.Espaco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspacoRepository extends JpaRepository<Espaco,Long> {

}
