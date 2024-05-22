package com.palco.palcoprontoespacolist.control;

import com.palco.palcoprontoespacolist.entity.Eventos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventosRepository extends JpaRepository<Eventos, Long> {
}
