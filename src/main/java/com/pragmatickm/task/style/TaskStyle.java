/*
 * pragmatickm-task-style - Default style for tasks nested within SemanticCMS pages and elements.
 * Copyright (C) 2016, 2017, 2020  AO Industries, Inc.
 *     support@aoindustries.com
 *     7262 Bull Pen Cir
 *     Mobile, AL 36695
 *
 * This file is part of pragmatickm-task-style.
 *
 * pragmatickm-task-style is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * pragmatickm-task-style is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with pragmatickm-task-style.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.pragmatickm.task.style;

import com.aoindustries.web.resources.registry.Group;
import com.aoindustries.web.resources.registry.Style;
import com.aoindustries.web.resources.servlet.RegistryEE;
import com.pragmatickm.task.model.Task;
import com.semanticcms.core.servlet.SemanticCMS;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener("Registers the styles for tasks in SemanticCMS in RegistryEE and SemanticCMS.")
public class TaskStyle implements ServletContextListener {

	public static final Group.Name RESOURCE_GROUP = new Group.Name("pragmatickm-task-style");

	// TODO: Change to Group.Name once we have group-level ordering
	public static final Style PRAGMATICKM_TASK = new Style("/pragmatickm-task-style/pragmatickm-task.css");

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();

		// Add our CSS file
		RegistryEE.Application.get(servletContext)
			.activate(RESOURCE_GROUP) // TODO: Activate as-needed
			.getGroup(RESOURCE_GROUP)
			.styles
			.add(PRAGMATICKM_TASK);

		SemanticCMS semanticCMS = SemanticCMS.getInstance(servletContext);
		// Add link CSS class
		semanticCMS.addLinkCssClass(Task.class, "pragmatickm-task-link");
		// Add list item CSS class
		semanticCMS.addListItemCssClass(Task.class, "pragmatickm-task-list-item");
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// Do nothing
	}
}
