/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * CellDataRequest 
 * @author Edward Banfa
 */
public class CellDataRequest extends BaseRequest {
	
	private Integer rowDataId;
    private String rowDataText;
    private Integer dataFieldId;
    private String dataFieldText;
    private String name;
    private String description;
    private String cellData;
    private char importStatusFg;
    private Integer id;
    private String code;

    public CellDataRequest() {
    }

    /**
	 * @param rowDataId
	 * @param dataFieldId
	 * @param name
	 * @param code
	 * @param importStatusFg
	 * @param cellData
	 */
	public CellDataRequest(Integer rowDataId, Integer dataFieldId, String name,
			String code, char importStatusFg, String cellData) {
		this.rowDataId = rowDataId;
		this.dataFieldId = dataFieldId;
		this.name = name;
		this.code = code;
		this.importStatusFg = importStatusFg;
		this.cellData = cellData;
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


