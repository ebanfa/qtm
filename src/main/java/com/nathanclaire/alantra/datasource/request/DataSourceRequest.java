/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * DataSourceRequest 
 * @author Edward Banfa
 */
public class DataSourceRequest extends BaseRequest {

    private Integer dataSourceStructureId;
    private String dataSourceStructureText;
    private Integer dataSourceTypeId;
    private String dataSourceTypeText;
    private String name;
    private String description;
    private String dsUrl;
    private String dsDb;
    private String dsTblNm;
    private String usrNm;
    private String password;
    private Integer id;
    private String code;

    public DataSourceRequest() {
    }

    public Integer getDataSourceStructureId() {
        return this.dataSourceStructureId;
    }
    
    public void setDataSourceStructureId(Integer dataSourceStructureId) {
        this.dataSourceStructureId = dataSourceStructureId;
    }

    public String getDataSourceStructureText() {
        return this.dataSourceStructureText;
    }
    
    public void setDataSourceStructureText(String dataSourceStructureText) {
        this.dataSourceStructureText = dataSourceStructureText;
    }

    public Integer getDataSourceTypeId() {
        return this.dataSourceTypeId;
    }
    
    public void setDataSourceTypeId(Integer dataSourceTypeId) {
        this.dataSourceTypeId = dataSourceTypeId;
    }

    public String getDataSourceTypeText() {
        return this.dataSourceTypeText;
    }
    
    public void setDataSourceTypeText(String dataSourceTypeText) {
        this.dataSourceTypeText = dataSourceTypeText;
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

    public String getDsUrl() {
        return this.dsUrl;
    }
    
    public void setDsUrl(String dsUrl) {
        this.dsUrl = dsUrl;
    }

    public String getDsDb() {
        return this.dsDb;
    }
    
    public void setDsDb(String dsDb) {
        this.dsDb = dsDb;
    }

    public String getDsTblNm() {
        return this.dsTblNm;
    }
    
    public void setDsTblNm(String dsTblNm) {
        this.dsTblNm = dsTblNm;
    }

    public String getUsrNm() {
        return this.usrNm;
    }
    
    public void setUsrNm(String usrNm) {
        this.usrNm = usrNm;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
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


