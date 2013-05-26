/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.application.request;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * ApplicationActivityRequest 
 * @author Edward Banfa
 */
public class ApplicationActivityRequest extends BaseRequest {

    private Integer applicationActivityGroupId;
    private Integer applicationModuleId;
    private Integer applicationEntityId;
    private Integer applicationFormId;
    private Integer applicationActivityTypeId;
    private String name;
    private String description;
    private int activitySeq;
    private String displayNm;
    private String displayImg;
    private String operationCd;
    private Integer id;
    private String code;

    public ApplicationActivityRequest() {
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

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
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

