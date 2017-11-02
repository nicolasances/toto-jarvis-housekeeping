package com.imatz.toto.housekeeping.jarvis.executors;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.imatz.toto.jarvis.base.alert.JARVISAEIExecutor;
import com.imatz.toto.jarvis.base.alert.JARVISActionsExecutionResult;
import com.imatz.toto.jarvis.base.alert.model.JARVISActionExecutionInstruction;
import com.imatz.toto.util.rest.RESTCall;

@Service("housekeeping.action-executor.pin-cleaning-day")
@Scope("prototype")
public class PinCleaningDayExecutor implements JARVISAEIExecutor {

	private static final Logger logger_ = LogManager.getLogger();

	@Value("${housekeeping.ms.url.post-day}")
	private String msHousekeepingURL_;

	@Override
	public JARVISActionsExecutionResult execute(JARVISActionExecutionInstruction inst) {

		logger_.info("Received an AEI for alert \"" + inst.getJarvisAlertCode() + "\" (" + inst.getJarvisAlertID() + ").");

		// TODO Capire come tracciare gli alert action name e gli alert code

		if (inst.getJarvisAlertActionName().equals("SI")) pinCleaningDay(inst);
		
		JARVISActionsExecutionResult executionResult = new JARVISActionsExecutionResult();
		executionResult.addResolvedAlert(inst.getJarvisAlertID());

		return executionResult;

	}

	/**
	 * Sends a request for pinning the current day on the housekeeping days
	 * 
	 * @param inst
	 *            the instruction based on which this HTTP call is made
	 */
	private void pinCleaningDay(JARVISActionExecutionInstruction inst) {

		logger_.info("Sending an HTTP request for pinning today as a cleaning day, as a reaction to AEI of alert " + inst.getJarvisAlertID());

		String data = "{\"date\": \"" + new SimpleDateFormat("dd/MM/yyyy").format(new Date()) + "\"}";
		
		RESTCall call = new RESTCall(msHousekeepingURL_);
		
		call.post(data);
		
	}

}
