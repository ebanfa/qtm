/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.application.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * ApplicationFormResponse 
 * @author Edward Banfa
 */
public class ApplicationFormResponse extends BaseResponse {

    private Integer applicationFormTypeId;
    private Integer applicationModuleId;
    private Integer applicationEntityId;
    private String description;
    private String displayNm;
    private String displayNmPlural;
    private char hasTable;
    private String dbName;

    public ApplicationFormResponse() {
    }

    public Integer getApplicationFormTypeId() {
        return this.applicationFormTypeId;
    }
    
    public void setApplicationFormTypeId(Integer applicationFormTypeId) {
        this.applicationFormTypeId = applicationFormTypeId;
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

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayNm() {
        return this.displayNm;
    }
    
    public void setDisplayNm(String displayNm) {
        this.displayNm = displayNm;
    }

    public String getDisplayNmPlural() {
        return this.displayNmPlural;
    }
    
    public void setDisplayNmPlural(String displayNmPlural) {
        this.displayNmPlural = displayNmPlural;
    }

    public char getHasTable() {
        return this.hasTable;
    }
    
    public void setHasTable(char hasTable) {
        this.hasTable = hasTable;
    }

    public String getDbName() {
        return this.dbName;
    }
    
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }


}


