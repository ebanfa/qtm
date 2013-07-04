/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.customer.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.datasource.model.DataChannelCategory;

/**
 * CustomerNotificationChannel 
 * @author Edward Banfa
 */
@Entity
@Table(name="CUSTOMER_NOTIFICATION_CHANNEL"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CustomerNotificationChannel  extends BaseEntity implements java.io.Serializable {

	private DataChannelCategory dataChannelCategory;
	private Customer customer;

    public CustomerNotificationChannel() {
    }

    public CustomerNotificationChannel(DataChannelCategory dataChannelCategory, Customer customer, String code, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public CustomerNotificationChannel(DataChannelCategory dataChannelCategory, Customer customer, String code, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
		this.dataChannelCategory = dataChannelCategory;
		this.customer = customer;
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CHANNEL_CAT_ID", nullable=false)
    @JsonIgnore
    public DataChannelCategory getDataChannelCategory() 
    {
        return this.dataChannelCategory;
    }
    
    public void setDataChannelCategory(DataChannelCategory dataChannelCategory)
    {
        this.dataChannelCategory = dataChannelCategory;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CUST_ID", nullable=false)
    @JsonIgnore
    public Customer getCustomer() 
    {
        return this.customer;
    }
    
    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }


}


