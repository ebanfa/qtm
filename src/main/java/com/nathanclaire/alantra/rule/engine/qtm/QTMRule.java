/**
 * 
 */
package com.nathanclaire.alantra.rule.engine.qtm;

import java.util.List;

import com.nathanclaire.alantra.rule.engine.Rule;
import com.nathanclaire.alantra.rule.engine.RuleAction;
import com.nathanclaire.alantra.rule.engine.RuleCondition;

/**
 * @author Edward Banfa
 *
 */
public class QTMRule implements Rule {

	private String code;
	private String name;
	private String conditionMode;
	private RuleAction ruleAction;
	private Boolean active;
	private List<RuleCondition> ruleConditions;
	
	/**
	 * @param code
	 * @param name
	 * @param conditionMode
	 * @param ruleAction
	 * @param ruleConditions
	 */
	public QTMRule(String code, String name, String conditionMode,
			RuleAction ruleAction, List<RuleCondition> ruleConditions) {
		this.code = code;
		this.name = name;
		this.conditionMode = conditionMode;
		this.ruleAction = ruleAction;
		this.ruleConditions = ruleConditions;
	}
	/**
	 * @param code
	 * @param name
	 * @param conditionMode
	 * @param ruleAction
	 * @param active
	 * @param ruleConditions
	 */
	public QTMRule(String code, String name, String conditionMode,
			RuleAction ruleAction, Boolean active,
			List<RuleCondition> ruleConditions) {
		this.code = code;
		this.name = name;
		this.conditionMode = conditionMode;
		this.ruleAction = ruleAction;
		this.active = active;
		this.ruleConditions = ruleConditions;
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
	 * @return the conditionMode
	 */
	public String getConditionMode() {
		return conditionMode;
	}
	/**
	 * @param conditionMode the conditionMode to set
	 */
	public void setConditionMode(String conditionMode) {
		this.conditionMode = conditionMode;
	}
	/**
	 * @return the ruleAction
	 */
	public RuleAction getRuleAction() {
		return ruleAction;
	}
	/**
	 * @param ruleAction the ruleAction to set
	 */
	public void setRuleAction(RuleAction ruleAction) {
		this.ruleAction = ruleAction;
	}
	/**
	 * @return the ruleConditions
	 */
	public List<RuleCondition> getRuleConditions() {
		return ruleConditions;
	}
	/**
	 * @param ruleConditions the ruleConditions to set
	 */
	public void setRuleConditions(List<RuleCondition> ruleConditions) {
		this.ruleConditions = ruleConditions;
	}
	/**
	 * @return the active
	 */
	public Boolean isActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QTMRule [code=" + code + ", name=" + name + ", conditionMode="
				+ conditionMode + ", ruleAction=" + ruleAction + ", active="
				+ active + "]";
	}
}
