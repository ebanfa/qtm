/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.request;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * RowDataRequest 
 * @author Edward Banfa
 */
public class RowDataRequest extends BaseRequest {

    private Integer tableDataId;
    private String tableDataText;
    private String name;
    private String description;
    private char isHeaderFg;
    private char importStatusFg;
    private Integer id;
    private String code;

    public RowDataRequest() {
    }

    /**
	 * @param tableDataId
	 * @param name
	 * @param code
	 * @param isHeaderFg
	 */
	public RowDataRequest(Integer tableDataId, String name, String code,
			Character isHeaderFg, Character importStatusFg) {
		this.tableDataId = tableDataId;
		this.name = name;
		this.code = code;
		this.isHeaderFg = isHeaderFg;
		this.importStatusFg = importStatusFg;
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


