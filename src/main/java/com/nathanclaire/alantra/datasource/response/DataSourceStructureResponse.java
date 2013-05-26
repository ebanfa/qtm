/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * DataSourceStructureResponse 
 * @author Edward Banfa
 */
public class DataSourceStructureResponse extends BaseResponse {

    private Character dsStructDelimeter;
    private String targetEntityCd;

    public DataSourceStructureResponse() {
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


