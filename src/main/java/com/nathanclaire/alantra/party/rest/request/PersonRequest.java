/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.party.rest.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;

/**
 * PersonRequest 
 * @author Edward Banfa
 */
public class PersonRequest extends BaseRequest {

    private Integer id;
    private Integer party;
    private String code;
    private String currentFNm;
    private String currentLNm;
    private String currentMNm;
    private String currentSuffix;
    private String currentPtitle;
    private String currentNNm;
    private String motherMaidenNm;
    private String gender;
    private String maritalSt;
    private String ssNo;
    private String currentPpNo;
    private Date currentPpExpDt;
    private BigDecimal weight;
    private BigDecimal height;
    private Date birthDt;
    private Integer totYrWorkExp;
    private String remarks;

    public PersonRequest() {
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParty() {
        return this.party;
    }
    
    public void setParty(Integer party) {
        this.party = party;
    }

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    public String getCurrentFNm() {
        return this.currentFNm;
    }
    
    public void setCurrentFNm(String currentFNm) {
        this.currentFNm = currentFNm;
    }

    public String getCurrentLNm() {
        return this.currentLNm;
    }
    
    public void setCurrentLNm(String currentLNm) {
        this.currentLNm = currentLNm;
    }

    public String getCurrentMNm() {
        return this.currentMNm;
    }
    
    public void setCurrentMNm(String currentMNm) {
        this.currentMNm = currentMNm;
    }

    public String getCurrentSuffix() {
        return this.currentSuffix;
    }
    
    public void setCurrentSuffix(String currentSuffix) {
        this.currentSuffix = currentSuffix;
    }

    public String getCurrentPtitle() {
        return this.currentPtitle;
    }
    
    public void setCurrentPtitle(String currentPtitle) {
        this.currentPtitle = currentPtitle;
    }

    public String getCurrentNNm() {
        return this.currentNNm;
    }
    
    public void setCurrentNNm(String currentNNm) {
        this.currentNNm = currentNNm;
    }

    public String getMotherMaidenNm() {
        return this.motherMaidenNm;
    }
    
    public void setMotherMaidenNm(String motherMaidenNm) {
        this.motherMaidenNm = motherMaidenNm;
    }

    public String getGender() {
        return this.gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalSt() {
        return this.maritalSt;
    }
    
    public void setMaritalSt(String maritalSt) {
        this.maritalSt = maritalSt;
    }

    public String getSsNo() {
        return this.ssNo;
    }
    
    public void setSsNo(String ssNo) {
        this.ssNo = ssNo;
    }

    public String getCurrentPpNo() {
        return this.currentPpNo;
    }
    
    public void setCurrentPpNo(String currentPpNo) {
        this.currentPpNo = currentPpNo;
    }

    public Date getCurrentPpExpDt() {
        return this.currentPpExpDt;
    }
    
    public void setCurrentPpExpDt(Date currentPpExpDt) {
        this.currentPpExpDt = currentPpExpDt;
    }

    public BigDecimal getWeight() {
        return this.weight;
    }
    
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getHeight() {
        return this.height;
    }
    
    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public Date getBirthDt() {
        return this.birthDt;
    }
    
    public void setBirthDt(Date birthDt) {
        this.birthDt = birthDt;
    }

    public Integer getTotYrWorkExp() {
        return this.totYrWorkExp;
    }
    
    public void setTotYrWorkExp(Integer totYrWorkExp) {
        this.totYrWorkExp = totYrWorkExp;
    }

    public String getRemarks() {
        return this.remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


}


