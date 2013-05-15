/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.application.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * ApplicationActivityGroupResponse 
 * @author Edward Banfa
 */
public class ApplicationActivityGroupResponse extends BaseResponse {

    private Integer applicationActivityGroupId;
    private Integer applicationActivityGroupTypeId;
    private Integer applicationModuleId;
    private String description;
    private String grpUrl;
    private int grpSeq;
    private String displayNm;
    private String displayImg;
    private char displayFg;
    private char isParent;

    public ApplicationActivityGroupResponse() {
    }

    public Integer getApplicationActivityGroupId() {
        return this.applicationActivityGroupId;
    }
    
    public void setApplicationActivityGroupId(Integer applicationActivityGroupId) {
        this.applicationActivityGroupId = applicationActivityGroupId;
    }

    public Integer getApplicationActivityGroupTypeId() {
        return this.applicationActivityGroupTypeId;
    }
    
    public void setApplicationActivityGroupTypeId(Integer applicationActivityGroupTypeId) {
        this.applicationActivityGroupTypeId = applicationActivityGroupTypeId;
    }

    public Integer getApplicationModuleId() {
        return this.applicationModuleId;
    }
    
    public void setApplicationModuleId(Integer applicationModuleId) {
        this.applicationModuleId = applicationModuleId;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getGrpUrl() {
        return this.grpUrl;
    }
    
    public void setGrpUrl(String grpUrl) {
        this.grpUrl = grpUrl;
    }

    public int getGrpSeq() {
        return this.grpSeq;
    }
    
    public void setGrpSeq(int grpSeq) {
        this.grpSeq = grpSeq;
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

    public char getDisplayFg() {
        return this.displayFg;
    }
    
    public void setDisplayFg(char displayFg) {
        this.displayFg = displayFg;
    }

    public char getIsParent() {
        return this.isParent;
    }
    
    public void setIsParent(char isParent) {
        this.isParent = isParent;
    }


}


