/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.customer.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * AccountRequest 
 * @author Edward Banfa
 */
public class AccountRequest extends BaseRequest {

    private Integer currencyId;
    private String currencyText;
    private Integer accountTypeId;
    private String accountTypeText;
    private String name;
    private String accountNo;
    private char isJointFg;
    private String description;
    private Integer id;
    private String code;

    public AccountRequest() {
    }

    /**
	 * @param accountTypeId
	 * @param currencyId
	 * @param name
	 * @param accountNo
	 * @param isJointFg
	 */
	public AccountRequest(Integer accountTypeId, Integer currencyId,
			String name, String accountNo, char isJointFg) {
		super();
		this.accountTypeId = accountTypeId;
		this.currencyId = currencyId;
		this.name = name;
		this.accountNo = accountNo;
		this.isJointFg = isJointFg;
	}

	public Integer getCurrencyId() {
        return this.currencyId;
    }
    
    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrencyText() {
        return this.currencyText;
    }
    
    public void setCurrencyText(String currencyText) {
        this.currencyText = currencyText;
    }

    public Integer getAccountTypeId() {
        return this.accountTypeId;
    }
    
    public void setAccountTypeId(Integer accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public String getAccountTypeText() {
        return this.accountTypeText;
    }
    
    public void setAccountTypeText(String accountTypeText) {
        this.accountTypeText = accountTypeText;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNo() {
        return this.accountNo;
    }
    
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public char getIsJointFg() {
        return this.isJointFg;
    }
    
    public void setIsJointFg(char isJointFg) {
        this.isJointFg = isJointFg;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }


}


