/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.workeffort.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * WorkEffortRequest 
 * @author Edward Banfa
 */
public class WorkEffortRequest extends BaseRequest {

    private Integer workEffortType;
    private String name;
    private String description;
    private Date schedStartDt;
    private Date schedEndDt;
    private Integer estHours;
    private Integer totalHrAllowed;
    private BigDecimal totalAmountAllowed;
    private Integer id;
    private String code;

    public WorkEffortRequest() {
    }

    public Integer getWorkEffortType() {
        return this.workEffortType;
    }
    
    public void setWorkEffortType(Integer workEffortType) {
        this.workEffortType = workEffortType;
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

    public Date getSchedStartDt() {
        return this.schedStartDt;
    }
    
    public void setSchedStartDt(Date schedStartDt) {
        this.schedStartDt = schedStartDt;
    }

    public Date getSchedEndDt() {
        return this.schedEndDt;
    }
    
    public void setSchedEndDt(Date schedEndDt) {
        this.schedEndDt = schedEndDt;
    }

    public Integer getEstHours() {
        return this.estHours;
    }
    
    public void setEstHours(Integer estHours) {
        this.estHours = estHours;
    }

    public Integer getTotalHrAllowed() {
        return this.totalHrAllowed;
    }
    
    public void setTotalHrAllowed(Integer totalHrAllowed) {
        this.totalHrAllowed = totalHrAllowed;
    }

    public BigDecimal getTotalAmountAllowed() {
        return this.totalAmountAllowed;
    }
    
    public void setTotalAmountAllowed(BigDecimal totalAmountAllowed) {
        this.totalAmountAllowed = totalAmountAllowed;
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


