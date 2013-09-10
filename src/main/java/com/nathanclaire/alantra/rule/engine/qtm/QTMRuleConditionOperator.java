/**
 * 
 */
package com.nathanclaire.alantra.rule.engine.qtm;

import com.nathanclaire.alantra.rule.engine.RuleConditionOperator;

/**
 * @author Edward Banfa
 *
 */
public class QTMRuleConditionOperator implements RuleConditionOperator {

	private String code;
	private String name;
	private String symbol;
	
	/**
	 * @param code
	 * @param name
	 * @param symbol
	 */
	public QTMRuleConditionOperator(String code, String name, String symbol) {
		this.code = code;
		this.name = name;
		this.symbol = symbol;
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
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}
	/**
	 * @param symbol the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QTMRuleConditionOperator [name=" + name + ", symbol=" + symbol
				+ "]";
	}

}
