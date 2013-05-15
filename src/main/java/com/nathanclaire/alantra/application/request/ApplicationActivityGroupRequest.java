/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.application.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * ApplicationActivityGroupRequest 
 * @author Edward Banfa
 */
public class ApplicationActivityGroupRequest extends BaseRequest {

    private Integer id;
    private Integer applicationActivityGroup;
    private Integer applicationActivityGroupType;
    private Integer applicationModule;
    private String code;
    private String name;
    private String description;
    private String grpUrl;
    private int grpSeq;
    private String displayNm;
    private String displayImg;
    private char displayFg;
    private char isParent;

    public ApplicationActivityGroupRequest() {
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApplicationActivityGroup() {
        return this.applicationActivityGroup;
    }
    
    public void setApplicationActivityGroup(Integer applicationActivityGroup) {
        this.applicationActivityGroup = applicationActivityGroup;
    }

    public Integer getApplicationActivityGroupType() {
        return this.applicationActivityGroupType;
    }
    
    public void setApplicationActivityGroupType(Integer applicationActivityGroupType) {
        this.applicationActivityGroupType = applicationActivityGroupType;
    }

    public Integer getApplicationModule() {
        return this.applicationModule;
    }
    
    public void setApplicationModule(Integer applicationModule) {
        this.applicationModule = applicationModule;
    }

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
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


