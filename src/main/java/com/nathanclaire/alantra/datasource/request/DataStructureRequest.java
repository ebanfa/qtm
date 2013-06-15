/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * DataStructureRequest 
 * @author Edward Banfa
 */
public class DataStructureRequest extends BaseRequest {

    private String name;
    private String description;
    private Character dsStructDelimeter;
    private Character skipFirstFg;
    private Character strictFg;
    private String targetPriEntityCd;
    private String targetSecEntityCd;
    private Integer id;
    private String code;

    public DataStructureRequest() {
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

    public Character getSkipFirstFg() {
        return this.skipFirstFg;
    }
    
    public void setSkipFirstFg(Character skipFirstFg) {
        this.skipFirstFg = skipFirstFg;
    }

    public Character getStrictFg() {
        return this.strictFg;
    }
    
    public void setStrictFg(Character strictFg) {
        this.strictFg = strictFg;
    }

    public String getTargetPriEntityCd() {
        return this.targetPriEntityCd;
    }
    
    public void setTargetPriEntityCd(String targetPriEntityCd) {
        this.targetPriEntityCd = targetPriEntityCd;
    }

    public String getTargetSecEntityCd() {
        return this.targetSecEntityCd;
    }
    
    public void setTargetSecEntityCd(String targetSecEntityCd) {
        this.targetSecEntityCd = targetSecEntityCd;
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


