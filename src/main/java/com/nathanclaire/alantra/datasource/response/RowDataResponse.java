/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * RowDataResponse 
 * @author Edward Banfa
 */
public class RowDataResponse extends BaseResponse {

    private Integer tableDataId;
    private String tableDataText;
    private char isHeaderFg;
    private char importStatusFg;

    public RowDataResponse() {
    }

    public Integer getTableDataId() {
        return this.tableDataId;
    }
    
    public void setTableDataId(Integer tableDataId) {
        this.tableDataId = tableDataId;
    }

    public String getTableDataText() {
        return this.tableDataText;
    }
    
    public void setTableDataText(String tableDataText) {
        this.tableDataText = tableDataText;
    }

    public char getIsHeaderFg() {
        return this.isHeaderFg;
    }
    
    public void setIsHeaderFg(char isHeaderFg) {
        this.isHeaderFg = isHeaderFg;
    }

    public char getImportStatusFg() {
        return this.importStatusFg;
    }
    
    public void setImportStatusFg(char importStatusFg) {
        this.importStatusFg = importStatusFg;
    }


}


