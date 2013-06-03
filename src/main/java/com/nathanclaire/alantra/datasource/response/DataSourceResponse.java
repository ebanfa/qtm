/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * DataSourceResponse 
 * @author Edward Banfa
 */
public class DataSourceResponse extends BaseResponse {

    private Integer dataSourceStructureId;
    private String dataSourceStructureText;
    private Integer dataSourceTypeId;
    private String dataSourceTypeText;
    private String dsUrl;
    private String dsDb;
    private String dsTblNm;
    private String usrNm;
    private String password;

    public DataSourceResponse() {
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


}


