/**
 * 
 */
package com.multi.enterprise.commons.bootstrap;

import org.springframework.context.ApplicationContext;

/**
 * @author Robot
 *
 */
public interface ApplicationBootstrapExecutor extends Comparable<ApplicationBootstrapExecutor> {

	public void execute(final ApplicationContext applicationContext);

	public default int getExecutionOrder() {
		return 1000;
	}

	@Override
	public default int compareTo(final ApplicationBootstrapExecutor other) {
		return this.getExecutionOrder() - other.getExecutionOrder();
	}
}
