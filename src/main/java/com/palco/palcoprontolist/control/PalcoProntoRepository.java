package com.palco.palcoprontolist.control;

import com.palco.palcoprontolist.entity.PalcoPronto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PalcoProntoRepository extends JpaRepository<PalcoPronto,Long> {

}
