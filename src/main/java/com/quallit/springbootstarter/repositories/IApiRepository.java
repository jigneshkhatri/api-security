/**
 * 
 */
package com.quallit.springbootstarter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quallit.springbootstarter.entities.Api;

/**
 * @author JIGS
 *
 */
@Repository
public interface IApiRepository extends JpaRepository<Api, Long> {

}
