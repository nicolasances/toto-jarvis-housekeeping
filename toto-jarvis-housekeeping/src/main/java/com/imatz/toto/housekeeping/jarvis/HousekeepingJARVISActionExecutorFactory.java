package com.imatz.toto.housekeeping.jarvis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.imatz.toto.jarvis.base.alert.JARVISAEIExecutor;
import com.imatz.toto.jarvis.base.alert.JARVISAEIExecutorFactory;
import com.imatz.toto.jarvis.base.alert.model.JARVISActionExecutionInstruction;

@Service
@Scope("singleton")
public class HousekeepingJARVISActionExecutorFactory implements JARVISAEIExecutorFactory {
	
	@Autowired
	@Qualifier("housekeeping.action-executor.pin-cleaning-day")
	private JARVISAEIExecutor pinCleaningDayAE_; 

	/**
	 * Returns an action executor for the specific provided AEI
	 * 
	 * @param inst
	 *            the AEI
	 * @return the action executor that is able to execute the provided AEI
	 */
	public JARVISAEIExecutor getActionExecutor(JARVISActionExecutionInstruction inst) {
		
		// TODO Capire come tracciare gli Alert Code
		
		if (inst.getJarvisAlertCode().equals("cleaning-day-not-pinned")) return pinCleaningDayAE_;
		
		return null;
	}

}
