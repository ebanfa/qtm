/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.application.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * ApplicationActivityRequest 
 * @author Edward Banfa
 */
public class ApplicationActivityRequest extends BaseRequest {

    private Integer applicationActivityGroup;
    private Integer applicationModule;
    private Integer applicationEntity;
    private Integer applicationForm;
    private Integer applicationActivityType;
    private String description;
    private String activityUrl;
    private int activitySeq;
    private String displayNm;
    private String displayImg;
    private String operationCd;

    public ApplicationActivityRequest() {
    }

    public Integer getApplicationActivityGroup() {
        return this.applicationActivityGroup;
    }
    
    public void setApplicationActivityGroup(Integer applicationActivityGroup) {
        this.applicationActivityGroup = applicationActivityGroup;
    }

    public Integer getApplicationModule() {
        return this.applicationModule;
    }
    
    public void setApplicationModule(Integer applicationModule) {
        this.applicationModule = applicationModule;
    }

    public Integer getApplicationEntity() {
        return this.applicationEntity;
    }
    
    public void setApplicationEntity(Integer applicationEntity) {
        this.applicationEntity = applicationEntity;
    }

    public Integer getApplicationForm() {
        return this.applicationForm;
    }
    
    public void setApplicationForm(Integer applicationForm) {
        this.applicationForm = applicationForm;
    }

    public Integer getApplicationActivityType() {
        return this.applicationActivityType;
    }
    
    public void setApplicationActivityType(Integer applicationActivityType) {
        this.applicationActivityType = applicationActivityType;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getActivityUrl() {
        return this.activityUrl;
    }
    
    public void setActivityUrl(String activityUrl) {
        this.activityUrl = activityUrl;
    }

    public int getActivitySeq() {
        return this.activitySeq;
    }
    
    public void setActivitySeq(int activitySeq) {
        this.activitySeq = activitySeq;
    }

    public String getDisplayNm() {
        return this.displayNm;
    }
    
    public void setDisplayNm(String displayNm) {
        this.displayNm = displayNm;
    }

    public String getDisplayImg() {
        return this.displayImg;
    }
    
    public void setDisplayImg(String displayImg) {
        this.displayImg = displayImg;
    }

    public String getOperationCd() {
        return this.operationCd;
    }
    
    public void setOperationCd(String operationCd) {
        this.operationCd = operationCd;
    }


}


