/**
 * 
 */
package com.nathanclaire.alantra.rule.engine.qtm;

import java.util.List;

import com.nathanclaire.alantra.rule.engine.RuleCondition;
import com.nathanclaire.alantra.rule.engine.RuleConditionAttribute;
import com.nathanclaire.alantra.rule.engine.RuleConditionOperator;
import com.nathanclaire.alantra.rule.engine.RuleConditionParameter;

/**
 * @author Edward Banfa
 *
 */
public class QTMCondition implements RuleCondition {
	
	private String code;
	private String name;
	private RuleConditionOperator ruleConditionOperator;
	private RuleConditionAttribute ruleConditionAttribute;
	private RuleConditionParameter ruleConditionParameter;
	private List<RuleConditionOperator> applicableOperators;
	
	/**
	 * @param code
	 * @param name
	 * @param ruleConditionOperator
	 * @param ruleConditionAttribute
	 * @param ruleConditionParameter
	 */
	public QTMCondition(String code, String name,
			RuleConditionOperator ruleConditionOperator,
			RuleConditionAttribute ruleConditionAttribute,
			RuleConditionParameter ruleConditionParameter) {
		this.code = code;
		this.name = name;
		this.ruleConditionOperator = ruleConditionOperator;
		this.ruleConditionAttribute = ruleConditionAttribute;
		this.ruleConditionParameter = ruleConditionParameter;
	}
	
	/**
	 * @param code
	 * @param name
	 * @param ruleConditionOperator
	 * @param ruleConditionAttribute
	 * @param ruleConditionParameter
	 * @param applicableOperators
	 */
	public QTMCondition(String code, String name,
			RuleConditionOperator ruleConditionOperator,
			RuleConditionAttribute ruleConditionAttribute,
			RuleConditionParameter ruleConditionParameter,
			List<RuleConditionOperator> applicableOperators) {
		this.code = code;
		this.name = name;
		this.ruleConditionOperator = ruleConditionOperator;
		this.ruleConditionAttribute = ruleConditionAttribute;
		this.ruleConditionParameter = ruleConditionParameter;
		this.applicableOperators = applicableOperators;
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
	 * @return the ruleConditionOperator
	 */
	public RuleConditionOperator getRuleConditionOperator() {
		return ruleConditionOperator;
	}
	/**
	 * @param ruleConditionOperator the ruleConditionOperator to set
	 */
	public void setRuleConditionOperator(RuleConditionOperator ruleConditionOperator) {
		this.ruleConditionOperator = ruleConditionOperator;
	}
	/**
	 * @return the ruleConditionAttribute
	 */
	public RuleConditionAttribute getRuleConditionAttribute() {
		return ruleConditionAttribute;
	}
	/**
	 * @param ruleConditionAttribute the ruleConditionAttribute to set
	 */
	public void setRuleConditionAttribute(
			RuleConditionAttribute ruleConditionAttribute) {
		this.ruleConditionAttribute = ruleConditionAttribute;
	}
	/**
	 * @return the ruleConditionParameter
	 */
	public RuleConditionParameter getRuleConditionParameter() {
		return ruleConditionParameter;
	}
	/**
	 * @param ruleConditionParameter the ruleConditionParameter to set
	 */
	public void setRuleConditionParameter(
			RuleConditionParameter ruleConditionParameter) {
		this.ruleConditionParameter = ruleConditionParameter;
	}
	/**
	 * @return the applicableOperators
	 */
	public List<RuleConditionOperator> getApplicableOperators() {
		return applicableOperators;
	}
	/**
	 * @param applicableOperators the applicableOperators to set
	 */
	public void setApplicableOperators(
			List<RuleConditionOperator> applicableOperators) {
		this.applicableOperators = applicableOperators;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QTMCondition [name=" + name + ", ruleConditionOperator="
				+ ruleConditionOperator + ", ruleConditionAttribute="
				+ ruleConditionAttribute + ", ruleConditionParameter="
				+ ruleConditionParameter + "]";
	}
	

}
