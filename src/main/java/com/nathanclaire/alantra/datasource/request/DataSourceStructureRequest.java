/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * DataSourceStructureRequest 
 * @author Edward Banfa
 */
public class DataSourceStructureRequest extends BaseRequest {

    private Integer id;
    private String code;
    private String name;
    private String description;
    private Character dsStructDelimeter;
    private String targetEntityCd;

    public DataSourceStructureRequest() {
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

    public Character getDsStructDelimeter() {
        return this.dsStructDelimeter;
    }
    
    public void setDsStructDelimeter(Character dsStructDelimeter) {
        this.dsStructDelimeter = dsStructDelimeter;
    }

    public String getTargetEntityCd() {
        return this.targetEntityCd;
    }
    
    public void setTargetEntityCd(String targetEntityCd) {
        this.targetEntityCd = targetEntityCd;
    }


}


