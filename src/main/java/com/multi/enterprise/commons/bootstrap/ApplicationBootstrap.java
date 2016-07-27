/**
 * 
 */
package com.multi.enterprise.commons.bootstrap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;

/**
 * @author Robot
 *
 */
public class ApplicationBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger log = LoggerFactory.getLogger(ApplicationBootstrap.class);

	@Autowired(required = false)
	RootApplicationContextBootstrapExecutor[] rootBootstrapExecutors;

	@Autowired(required = false)
	ServletApplicationContextBootstrapExecutor[] servletBootstrapExecutors;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		final ApplicationContext context = event.getApplicationContext();
		final Environment env = context.getEnvironment();
		final String startDate = DateFormatUtils.ISO_DATE_FORMAT.format(context.getStartupDate());

		final boolean isRootContext = context.getParent() == null;

		try {
			if (isRootContext) {
				this.executBootstrapExecutors(this.rootBootstrapExecutors, context,
						"RootApplicationContextBootstrapExecutor");
				this.executBootstrapExecutors(this.servletBootstrapExecutors, context,
						"ServletAppliationBootstrapExecutors");
			}
		} catch (final RuntimeException e) {
			log.error("Runtime exeception executing bootstrap ", e);
			throw e;
		}
	}

	protected void executBootstrapExecutors(final ApplicationBootstrapExecutor[] bootstrapExecutors,
			final ApplicationContext context, final String description) {
		if (ArrayUtils.isNotEmpty(bootstrapExecutors)) {
			Arrays.sort(bootstrapExecutors);

			for (final ApplicationBootstrapExecutor executor : bootstrapExecutors) {
				executor.execute(context);
			}
		} else {
			log.info("no {} instances found ", description);
		}
	}

	private String toString(final ApplicationBootstrapExecutor[] executors) {
		final List<String> classNames = new ArrayList<>();
		for (final ApplicationBootstrapExecutor executor : executors) {
			classNames.add(executor.getClass().getName());
		}
		return classNames.toString();
	}
}
