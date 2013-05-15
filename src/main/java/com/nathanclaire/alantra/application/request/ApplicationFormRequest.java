/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.application.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * ApplicationFormRequest 
 * @author Edward Banfa
 */
public class ApplicationFormRequest extends BaseRequest {

    private Integer id;
    private Integer applicationFormType;
    private Integer applicationModule;
    private Integer applicationEntity;
    private String code;
    private String name;
    private String description;
    private String displayNm;
    private String displayNmPlural;
    private char hasTable;
    private String dbName;

    public ApplicationFormRequest() {
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApplicationFormType() {
        return this.applicationFormType;
    }
    
    public void setApplicationFormType(Integer applicationFormType) {
        this.applicationFormType = applicationFormType;
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


