/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import steps.dev.myfinance7.common.model.portfolio.Portfolio;

/**
 *
 * @author stepin
 */

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long>{
    
}
