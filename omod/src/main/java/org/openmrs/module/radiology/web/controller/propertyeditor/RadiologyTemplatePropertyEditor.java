/**
 *  Copyright 2010 Society for Health Information Systems Programmes, India (HISP India)
 *
 *  This file is part of Radiology module.
 *
 *  Radiology module is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  Radiology module is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Radiology module.  If not, see <http://www.gnu.org/licenses/>.
 *
 **/

package org.openmrs.module.radiology.web.controller.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.radiology.RadiologyService;
import org.openmrs.module.radiology.model.RadiologyTemplate;

public class RadiologyTemplatePropertyEditor extends PropertyEditorSupport {
	private Log log = LogFactory.getLog(this.getClass());

	public RadiologyTemplatePropertyEditor() {
	}

	public void setAsText(String text) throws IllegalArgumentException {
		RadiologyService rs = (RadiologyService) Context.getService(RadiologyService.class);
		if (text != null && text.trim().length() > 0) {
			try {
				setValue(rs.getRadiologyTemplate(NumberUtils.toInt(text)));
			} catch (Exception ex) {
				log.error("Error setting text: " + text, ex);
				throw new IllegalArgumentException("Radiology template not found: "
						+ ex.getMessage());
			}
		} else {
			setValue(null);
		}
	}

	public String getAsText() {
		RadiologyTemplate template = (RadiologyTemplate) getValue();
		if (template == null) {
			return null;
		} else {
			return template.getId() + "";
		}
	}
}
