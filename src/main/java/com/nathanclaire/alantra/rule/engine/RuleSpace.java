/**
 * 
 */
package com.nathanclaire.alantra.rule.engine;

import java.util.List;

/**
 * @author Edward Banfa
 *
 */
public interface RuleSpace {
	
	public String getCode();
	
	public String getName();
	
	public Boolean isDefaultRuleSpace();
	
	public void setDefaultRuleSpace(Boolean value);
	
	public List<RuleTable> getRuleTables();

}
