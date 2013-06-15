/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * DataStructureResponse 
 * @author Edward Banfa
 */
public class DataStructureResponse extends BaseResponse {

    private Character dsStructDelimeter;
    private Character skipFirstFg;
    private Character strictFg;
    private String targetPriEntityCd;
    private String targetSecEntityCd;

    public DataStructureResponse() {
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


}


