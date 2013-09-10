/**
 * 
 */
package com.nathanclaire.alantra.rule.engine.qtm;

import java.util.List;

import com.nathanclaire.alantra.rule.engine.Rule;
import com.nathanclaire.alantra.rule.engine.RuleChain;

/**
 * @author Edward Banfa
 *
 */
public class QTMRuleChain implements RuleChain {
	
	private String code;
	private String name;
	private Boolean defaultRuleChain;
	private List<Rule> rules;
	/**
	 * @param code
	 * @param name
	 * @param defaultRuleChain
	 * @param rules
	 */
	public QTMRuleChain(String code, String name, Boolean defaultRuleChain,
			List<Rule> rules) {
		this.code = code;
		this.name = name;
		this.defaultRuleChain = defaultRuleChain;
		this.rules = rules;
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
	 * @return the defaultRuleChain
	 */
	public Boolean isDefaultRuleChain() {
		return defaultRuleChain;
	}
	/**
	 * @param defaultRuleChain the defaultRuleChain to set
	 */
	public void setDefaultRuleChain(Boolean defaultRuleChain) {
		this.defaultRuleChain = defaultRuleChain;
	}
	/**
	 * @return the rules
	 */
	public List<Rule> getRules() {
		return rules;
	}
	/**
	 * @param rules the rules to set
	 */
	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.engine.RuleChain#addRule(com.nathanclaire.alantra.rule.engine.Rule)
	 */
	@Override
	public void addRule(Rule rule) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.engine.RuleChain#removeRule(java.lang.String)
	 */
	@Override
	public void removeRule(String ruleCode) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QTMRuleChain [code=" + code + ", name=" + name
				+ ", defaultRuleChain=" + defaultRuleChain + "]";
	}
}
