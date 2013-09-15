/**
 * 
 */
package com.nathanclaire.alantra.rule.engine;

import java.util.List;

/**
 * @author Edward Banfa
 *
 */
public interface Rule {
	
	public String getName();
	
	public String getProcessCategoryCode();
	
	public String getCode();
	
	public String getConditionMode();
	
	public RuleAction getRuleAction();
	
	public List<RuleCondition> getRuleConditions();
	
	public Boolean isActive();
	
	public void setActive(Boolean value);

}
