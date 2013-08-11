/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.response;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * CellDataResponse 
 * @author Edward Banfa
 */
public class CellDataResponse extends BaseResponse {

    private Integer rowDataId;
    private String rowDataText;
    private Integer dataFieldId;
    private String dataFieldText;
    private String cellData;
    private char importStatusFg;

    public CellDataResponse() {
    }

    public Integer getRowDataId() {
        return this.rowDataId;
    }
    
    public void setRowDataId(Integer rowDataId) {
        this.rowDataId = rowDataId;
    }

    public String getRowDataText() {
        return this.rowDataText;
    }
    
    public void setRowDataText(String rowDataText) {
        this.rowDataText = rowDataText;
    }

    public Integer getDataFieldId() {
        return this.dataFieldId;
    }
    
    public void setDataFieldId(Integer dataFieldId) {
        this.dataFieldId = dataFieldId;
    }

    public String getDataFieldText() {
        return this.dataFieldText;
    }
    
    public void setDataFieldText(String dataFieldText) {
        this.dataFieldText = dataFieldText;
    }

    public Object getCellData() {
        return this.cellData;
    }
    
    public void setCellData(String cellData) {
        this.cellData = cellData;
    }

    public char getImportStatusFg() {
        return this.importStatusFg;
    }
    
    public void setImportStatusFg(char importStatusFg) {
        this.importStatusFg = importStatusFg;
    }


}


