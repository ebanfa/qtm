package com.nathanclaire.alantra.party.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.BaseService;
import com.nathanclaire.alantra.base.util.Messages;
import com.nathanclaire.alantra.party.model.PartyType;
import com.nathanclaire.alantra.party.service.PartyTypeService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class PartyTypeServiceImpl extends BaseService<PartyType> implements PartyTypeService 
{
	private static final String ORG_PARTY_TY_CD = Messages.getString("PartyType.Organization");
	private static final String INDV_PARTY_TY_CD = Messages.getString("PartyType.Individual");

	@Inject
	MultivaluedMap<String, String> queryParameters;
	/**
	 * @param entityClass
	 */
	public PartyTypeServiceImpl() {
		super(PartyType.class);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyTypeService#getOrganizationalPartyType()
	 */
	@Override
	public PartyType getOrganizationalPartyType() 
	{
		//return this.getEntityManager();
		this.getAll(queryParameters);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyTypeService#getIndividualPartyType()
	 */
	@Override
	public PartyType getIndividualPartyType() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
     * <p>
     *     Subclasses may choose to expand the set of supported query parameters (for adding more filtering
     *     criteria) by overriding this method.
     * </p>
     * @param queryParameters - the HTTP query parameters received by the endpoint
     * @param criteriaBuilder - @{link CriteriaBuilder} used by the invoker
     * @param root  @{link Root} used by the invoker
     * @return a list of {@link Predicate}s that will added as query parameters
     */
    protected Predicate[] extractPredicates(MultivaluedMap<String, String> queryParameters,
            CriteriaBuilder criteriaBuilder, Root<PartyType> root) 
    {
    	queryParameters.add(ORG_PARTY_TY_CD, INDV_PARTY_TY_CD);
    	System.out.println("queryParameters>>>>>>>>>>>>>" + queryParameters);
    	List<Predicate> predicates = new ArrayList<Predicate>() ;
        if (queryParameters.containsKey(CODE_CRITERIA)) {
             String code = queryParameters.getFirst(CODE_CRITERIA) + "%";
            //predicates.add(criteriaBuilder.equal(root.get(CODE_CRITERIA), code));
            predicates.add(criteriaBuilder.like(root.<String>get(CODE_CRITERIA), code));
        }
        return predicates.toArray(new Predicate[]{});
	}

}
