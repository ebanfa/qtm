/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.base.model.BaseEntity;

/**
 * RowData 
 * @author Edward Banfa
 */
@Entity
@Table(name="ROW_DATA"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class RowData  extends BaseEntity implements java.io.Serializable {

	private TableData tableData;
    private String name;
    private String description;
    private char isHeaderFg;
    private char importStatusFg;
	private Set<CellData> cellDatas = new HashSet<CellData>(0);

    public RowData() {
    }

    public RowData(TableData tableData, String code, String name, char isHeaderFg, char importStatusFg, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.name = name;
		this.isHeaderFg = isHeaderFg;
		this.importStatusFg = importStatusFg;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public RowData(TableData tableData, String code, String name, String description, char isHeaderFg, char importStatusFg, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr, Set<CellData> cellDatas ) 
    {
		this.tableData = tableData;
		this.code = code;
		this.name = name;
		this.description = description;
		this.isHeaderFg = isHeaderFg;
		this.importStatusFg = importStatusFg;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
		this.cellDatas = cellDatas;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TABLE_DATA_ID", nullable=false)
    @JsonIgnore
    public TableData getTableData() 
    {
        return this.tableData;
    }
    
    public void setTableData(TableData tableData)
    {
        this.tableData = tableData;
    }
		
    @Column(name="NAME" , nullable=false, length=75)
    public String getName() 
    {
        return this.name;
    }
    
    public void setName(String name) 
    {
        this.name = name;
    }
		
    @Column(name="DESCRIPTION" , unique=true, length=150)
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }
		
    @Column(name="IS_HEADER_FG" , nullable=false, length=1)
    public char getIsHeaderFg() 
    {
        return this.isHeaderFg;
    }
    
    public void setIsHeaderFg(char isHeaderFg) 
    {
        this.isHeaderFg = isHeaderFg;
    }
		
    @Column(name="IMPORT_STATUS_FG" , nullable=false, length=1)
    public char getImportStatusFg() 
    {
        return this.importStatusFg;
    }
    
    public void setImportStatusFg(char importStatusFg) 
    {
        this.importStatusFg = importStatusFg;
    }
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="rowData")
    @JsonIgnore
    public Set<CellData> getCellDatas() 
    {
        return this.cellDatas;
    }
    
    public void setCellDatas(Set<CellData> cellDatas) 
    {
        this.cellDatas = cellDatas;
    }			


}


