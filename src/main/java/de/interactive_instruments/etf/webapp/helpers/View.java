/**
 * Copyright 2010-2016 interactive instruments GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.interactive_instruments.etf.webapp.helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import de.interactive_instruments.etf.EtfConstants;
import de.interactive_instruments.etf.dal.dto.plan.TestProjectDto;
import de.interactive_instruments.etf.webapp.controller.EtfConfigController;

/**
 * Helper functions for views
 * @author J. Herrmann ( herrmann <aT) interactive-instruments (doT> de )
 */
public class View {

	private View() {

	}

	public static Set<Map.Entry<String, String>> getTestRunParams(final TestProjectDto dto) {
		final Map<String, String> map = new HashMap<>();
		dto.getProperties().forEach(e -> {
			if (!EtfConstants.ETF_PROPERTY_KEYS.contains(e.getKey())) {
				map.put(e.getKey(), e.getValue());
			}
		});
		return map.entrySet();
	}

	public static boolean hasTestRunParams(final TestProjectDto dto) {
		return !getTestRunParams(dto).isEmpty();
	}

	public static Set<Map.Entry<String, String>> getMetaDataProperties(final TestProjectDto dto) {
		final Map<String, String> map = new HashMap<>();
		dto.getProperties().forEach(e -> {
			if (!EtfConstants.ETF_IGNORE_PROPERTIES_PK.equals(e.getKey()) &&
					EtfConstants.ETF_PROPERTY_KEYS.contains(e.getKey())) {
				map.put(e.getKey().replace("etf.", ""), e.getValue());
			}
		});
		return map.entrySet();
	}

	public static boolean hasMetaDataProperties(final TestProjectDto dto) {
		return !getMetaDataProperties(dto).isEmpty();
	}

	public static String getWorkflowType() {
		return EtfConfigController.getInstance().getProperty(EtfConfigController.ETF_WORKFLOWS);
	}

	public static String getBrandingText() {
		return EtfConfigController.getInstance().getProperty(EtfConfigController.ETF_BRANDING_TEXT);
	}

	public static String getHelpPageURL() {
		return EtfConfigController.getInstance().getProperty(EtfConfigController.ETF_HELP_PAGE_URL);
	}

	public static String getVersion() {
		return EtfConfigController.getInstance().getVersion();
	}

	public static String getReportComparison() {
		return EtfConfigController.getInstance().getProperty(EtfConfigController.ETF_REPORT_COMPARISON);
	}

	public static String getSubmitAnalysisData() {
		return EtfConfigController.getInstance().getProperty(EtfConfigController.ETF_SUBMIT_ERRORS);
	}

}
