/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.application.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * ApplicationEntityResponse 
 * @author Edward Banfa
 */
public class ApplicationEntityResponse extends BaseResponse {

    private Integer applicationModuleId;
    private String description;
    private String displayNm;
    private String displayNmPlural;
    private char hasTable;
    private String dbName;

    public ApplicationEntityResponse() {
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


