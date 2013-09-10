/**
 * 
 */
package com.nathanclaire.alantra.rule.engine.qtm;

import com.nathanclaire.alantra.rule.engine.RuleConditionAttribute;

/**
 * @author Edward Banfa
 *
 */
public class QTMRuleConditionAttribute implements RuleConditionAttribute {

	private String code;
	private String name;
	private String parameterType;
	
	/**
	 * @param code
	 * @param name
	 */
	public QTMRuleConditionAttribute(String code, String name) {
		this.code = code;
		this.name = name;
	}
	/**
	 * @param code
	 * @param name
	 * @param parameterType
	 */
	public QTMRuleConditionAttribute(String code, String name,
			String parameterType) {
		this.code = code;
		this.name = name;
		this.parameterType = parameterType;
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
	 * @return the parameterType
	 */
	public String getParameterType() {
		return parameterType;
	}
	/**
	 * @param parameterType the parameterType to set
	 */
	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QTMRuleConditionAttribute [name=" + name + ", parameterType="
				+ parameterType + "]";
	}
}
