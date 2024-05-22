package com.palco.palcoprontoespacolist.control;

import com.palco.palcoprontoespacolist.entity.Ingresso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngressoRepository extends JpaRepository<Ingresso, Long> {
}
