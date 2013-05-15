/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.application.response;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * ApplicationActivityResponse 
 * @author Edward Banfa
 */
public class ApplicationActivityResponse extends BaseResponse {

    private Integer applicationActivityGroupId;
    private Integer applicationModuleId;
    private Integer applicationEntityId;
    private Integer applicationFormId;
    private Integer applicationActivityTypeId;
    private String activityUrl;
    private int activitySeq;
    private String displayNm;
    private String displayImg;
    private String operationCd;

    public ApplicationActivityResponse() {
    }

    public Integer getApplicationActivityGroupId() {
        return this.applicationActivityGroupId;
    }
    
    public void setApplicationActivityGroupId(Integer applicationActivityGroupId) {
        this.applicationActivityGroupId = applicationActivityGroupId;
    }

    public Integer getApplicationModuleId() {
        return this.applicationModuleId;
    }
    
    public void setApplicationModuleId(Integer applicationModuleId) {
        this.applicationModuleId = applicationModuleId;
    }

    public Integer getApplicationEntityId() {
        return this.applicationEntityId;
    }
    
    public void setApplicationEntityId(Integer applicationEntityId) {
        this.applicationEntityId = applicationEntityId;
    }

    public Integer getApplicationFormId() {
        return this.applicationFormId;
    }
    
    public void setApplicationFormId(Integer applicationFormId) {
        this.applicationFormId = applicationFormId;
    }

    public Integer getApplicationActivityTypeId() {
        return this.applicationActivityTypeId;
    }
    
    public void setApplicationActivityTypeId(Integer applicationActivityTypeId) {
        this.applicationActivityTypeId = applicationActivityTypeId;
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


