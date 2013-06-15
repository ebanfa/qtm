/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.process.mail;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseTimerService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.channel.service.process.TransactionDataInputServiceImpl;
import com.nathanclaire.alantra.messaging.util.EmailLite;

/**
 * @author Edward Banfa 
 *
 */
@Startup
@Singleton
public class EmailServiceSchedulerImpl extends BaseTimerService implements EmailServiceScheduler {

	
	@Inject
	EmailService emailService;
	
	private Logger logger = LoggerFactory.getLogger(TransactionDataInputServiceImpl.class);
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.EmailServiceScheduler#start()
	 */
	@Override
	@PostConstruct
	@Schedule(minute="*/2", hour="*")
	public void start() {
		/*try {
			logger.info("Checking for email messages.........");
			List<EmailLite> messages = emailService.getMessages();
			logger.info("Downloaded {} messages", messages.size());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}*/
	}

}
