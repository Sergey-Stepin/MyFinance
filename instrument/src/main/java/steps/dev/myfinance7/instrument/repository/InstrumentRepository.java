/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.instrument.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import steps.dev.myfinance7.common.model.instrument.Instrument;

/**
 *
 * @author stepin
 */

public interface InstrumentRepository extends JpaRepository<Instrument, Long>{
    
}
