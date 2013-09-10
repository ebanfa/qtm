/**
 * 
 */
package com.nathanclaire.alantra.rule.engine.qtm;

import java.util.List;

import com.nathanclaire.alantra.rule.engine.RuleSpace;
import com.nathanclaire.alantra.rule.engine.RuleTable;

/**
 * @author Edward Banfa
 *
 */
public class QTMRuleSpace implements RuleSpace {
	
	private String code;
	private String name;
	private Boolean defaultRuleSpace;
	private List<RuleTable> ruleTables;
	
	
	/**
	 * @param code
	 * @param name
	 * @param defaultRuleSpace
	 * @param ruleTables
	 */
	public QTMRuleSpace(String code, String name, Boolean defaultRuleSpace,
			List<RuleTable> ruleTables) {
		this.code = code;
		this.name = name;
		this.defaultRuleSpace = defaultRuleSpace;
		this.ruleTables = ruleTables;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the isDefaultRuleSpace
	 */
	public Boolean isDefaultRuleSpace() {
		return defaultRuleSpace;
	}
	/**
	 * @param isDefaultRuleSpace the isDefaultRuleSpace to set
	 */
	public void setDefaultRuleSpace(Boolean defaultRuleSpace) {
		this.defaultRuleSpace = defaultRuleSpace;
	}
	/**
	 * @return the ruleTables
	 */
	public List<RuleTable> getRuleTables() {
		return ruleTables;
	}
	/**
	 * @param ruleTables the ruleTables to set
	 */
	public void setRuleTables(List<RuleTable> ruleTables) {
		this.ruleTables = ruleTables;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String objectString = "\nRules Space: " + name;
		objectString = objectString.concat("\nCode: " + code);
		objectString = objectString.concat("\nIs Default: " + defaultRuleSpace);
		return objectString;
	}


}
