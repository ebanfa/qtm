/**
 * 
 */
package com.nathanclaire.alantra.rule.engine.qtm;

import com.nathanclaire.alantra.rule.engine.RuleConditionParameter;

/**
 * @author Edward Banfa
 *
 */
public class QTMRuleConditionParameter implements RuleConditionParameter {

	private String code;
	private String name;
	private String value;
	private String parameterType;
	
	/**
	 * @param code
	 * @param name
	 * @param value
	 */
	public QTMRuleConditionParameter(String code, String name, String value) {
		this.code = code;
		this.name = name;
		this.value = value;
	}
	/**
	 * @param code
	 * @param name
	 * @param value
	 * @param parameterType
	 */
	public QTMRuleConditionParameter(String code, String name, String value,
			String parameterType) {
		this.code = code;
		this.name = name;
		this.value = value;
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
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
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
		return "QTMRuleConditionParameter [name=" + name + ", value=" + value
				+ ", parameterType=" + parameterType + "]";
	}
}
