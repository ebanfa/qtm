/**
 *  Alantra.
 */
package com.nathanclaire.alantra.transaction.model;

import java.util.Date;

import javax.persistence.Column;
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
import com.nathanclaire.alantra.notification.model.NotificationType;
import com.nathanclaire.alantra.transaction.model.TxnNotificationStatus;
import com.nathanclaire.alantra.transaction.model.ServiceTransaction;

/**
 * TxnAwaitingNotification 
 * @author Edward Banfa
 */
@Entity
@Table(name="TXN_AWAITING_NOTIFICATION"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class TxnAwaitingNotification  extends BaseEntity implements java.io.Serializable {

	private String channelTypeCode;
	private NotificationType notificationType;
	private ServiceTransaction serviceTransaction;
	private TxnNotificationStatus txnNotificationStatus;

    public TxnAwaitingNotification() {
    }

    public TxnAwaitingNotification(Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public TxnAwaitingNotification(TxnNotificationStatus txnNotificationStatus, ServiceTransaction serviceTransaction, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.txnNotificationStatus = txnNotificationStatus;
        this.serviceTransaction = serviceTransaction;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.versionNo = versionNo;
        this.rowTs = rowTs;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
        this.lastModifiedDt = lastModifiedDt;
        this.lastModifiedUsr = lastModifiedUsr;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="STATUS_ID")
    @JsonIgnore
    public TxnNotificationStatus getTxnNotificationStatus() 
    {
        return this.txnNotificationStatus;
    }
    
    public void setTxnNotificationStatus(TxnNotificationStatus txnNotificationStatus)
    {
        this.txnNotificationStatus = txnNotificationStatus;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TRANSACTION_ID")
    @JsonIgnore
    public ServiceTransaction getServiceTransaction() 
    {
        return this.serviceTransaction;
    }
    
    public void setServiceTransaction(ServiceTransaction serviceTransaction)
    {
        this.serviceTransaction = serviceTransaction;
    }

	/**
	 * @return the channelTypeCode
	 */ 
    @Column(name="CHANNEL_TY_CD" , nullable=false, length=35)
	public String getChannelTypeCode() {
		return channelTypeCode;
	}

	/**
	 * @param channelTypeCode the channelTypeCode to set
	 */
	public void setChannelTypeCode(String channelTypeCode) {
		this.channelTypeCode = channelTypeCode;
	}

	/**
	 * @return the notificationType
	 */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="NOTIFICATION_TY_ID")
    @JsonIgnore
	public NotificationType getNotificationType() {
		return notificationType;
	}

	/**
	 * @param notificationType the notificationType to set
	 */
	public void setNotificationType(NotificationType notificationType) {
		this.notificationType = notificationType;
	}
    

}
