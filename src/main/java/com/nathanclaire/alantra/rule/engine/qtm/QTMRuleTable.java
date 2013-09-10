/**
 * 
 */
package com.nathanclaire.alantra.rule.engine.qtm;

import java.util.List;

import com.nathanclaire.alantra.rule.engine.RuleChain;
import com.nathanclaire.alantra.rule.engine.RuleTable;

/**
 * @author Edward Banfa
 *
 */
public class QTMRuleTable implements RuleTable {
	
	private String code;
	private String name;
	private Boolean defaultRuleTable;
	private List<RuleChain> ruleChains;
	
	/**
	 * @param code
	 * @param name
	 * @param defaultRuleTable
	 * @param ruleChains
	 */
	public QTMRuleTable(String code, String name, Boolean defaultRuleTable,
			List<RuleChain> ruleChains) {
		this.code = code;
		this.name = name;
		this.defaultRuleTable = defaultRuleTable;
		this.ruleChains = ruleChains;
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
	 * @return the defaultRuleTable
	 */
	public Boolean isDefaultRuleTable() {
		return defaultRuleTable;
	}
	/**
	 * @param defaultRuleTable the defaultRuleTable to set
	 */
	public void setDefaultRuleTable(Boolean defaultRuleTable) {
		this.defaultRuleTable = defaultRuleTable;
	}
	/**
	 * @return the ruleChains
	 */
	public List<RuleChain> getRuleChains() {
		return ruleChains;
	}
	/**
	 * @param ruleChains the ruleChains to set
	 */
	public void setRuleChains(List<RuleChain> ruleChains) {
		this.ruleChains = ruleChains;
	}
}
