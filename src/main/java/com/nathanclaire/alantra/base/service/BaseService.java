/**
 * 
 */
package com.nathanclaire.alantra.base.service;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * @author Edward Banfa 
 *
 */
public class BaseService {
	@Inject
	EntityManager entityManager;

}
