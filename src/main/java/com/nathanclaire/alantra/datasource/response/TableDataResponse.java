/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * TableDataResponse 
 * @author Edward Banfa
 */
public class TableDataResponse extends BaseResponse {

    private Integer dataInputJobId;
    private String dataInputJobText;
    private int primEntity;
    private int primRecRead;
    private int primRecCreated;
    private int primRecRejected;
    private Integer secEntity;
    private Integer secRecRead;
    private Integer secRecCreated;
    private Integer secRecRejected;
    private int totRecRead;
    private int totRecCreated;
    private int totRecRejected;
    private char importStatusFg;

    public TableDataResponse() {
    }

    public Integer getDataInputJobId() {
        return this.dataInputJobId;
    }
    
    public void setDataInputJobId(Integer dataInputJobId) {
        this.dataInputJobId = dataInputJobId;
    }

    public String getDataInputJobText() {
        return this.dataInputJobText;
    }
    
    public void setDataInputJobText(String dataInputJobText) {
        this.dataInputJobText = dataInputJobText;
    }

    public int getPrimEntity() {
        return this.primEntity;
    }
    
    public void setPrimEntity(int primEntity) {
        this.primEntity = primEntity;
    }

    public int getPrimRecRead() {
        return this.primRecRead;
    }
    
    public void setPrimRecRead(int primRecRead) {
        this.primRecRead = primRecRead;
    }

    public int getPrimRecCreated() {
        return this.primRecCreated;
    }
    
    public void setPrimRecCreated(int primRecCreated) {
        this.primRecCreated = primRecCreated;
    }

    public int getPrimRecRejected() {
        return this.primRecRejected;
    }
    
    public void setPrimRecRejected(int primRecRejected) {
        this.primRecRejected = primRecRejected;
    }

    public Integer getSecEntity() {
        return this.secEntity;
    }
    
    public void setSecEntity(Integer secEntity) {
        this.secEntity = secEntity;
    }

    public Integer getSecRecRead() {
        return this.secRecRead;
    }
    
    public void setSecRecRead(Integer secRecRead) {
        this.secRecRead = secRecRead;
    }

    public Integer getSecRecCreated() {
        return this.secRecCreated;
    }
    
    public void setSecRecCreated(Integer secRecCreated) {
        this.secRecCreated = secRecCreated;
    }

    public Integer getSecRecRejected() {
        return this.secRecRejected;
    }
    
    public void setSecRecRejected(Integer secRecRejected) {
        this.secRecRejected = secRecRejected;
    }

    public int getTotRecRead() {
        return this.totRecRead;
    }
    
    public void setTotRecRead(int totRecRead) {
        this.totRecRead = totRecRead;
    }

    public int getTotRecCreated() {
        return this.totRecCreated;
    }
    
    public void setTotRecCreated(int totRecCreated) {
        this.totRecCreated = totRecCreated;
    }

    public int getTotRecRejected() {
        return this.totRecRejected;
    }
    
    public void setTotRecRejected(int totRecRejected) {
        this.totRecRejected = totRecRejected;
    }

    public char getImportStatusFg() {
        return this.importStatusFg;
    }
    
    public void setImportStatusFg(char importStatusFg) {
        this.importStatusFg = importStatusFg;
    }


}


