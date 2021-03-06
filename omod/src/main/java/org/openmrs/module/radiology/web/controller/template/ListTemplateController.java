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

package org.openmrs.module.radiology.web.controller.template;

import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiology.RadiologyService;
import org.openmrs.module.radiology.model.RadiologyTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("RadiologyListTemplateController")
@RequestMapping("/module/radiology/listTemplate.form")
public class ListTemplateController {
		
	@ModelAttribute("templates")
	public List<RadiologyTemplate> getTemplates(){
		RadiologyService rs = (RadiologyService) Context.getService(RadiologyService.class);
		return rs.getAllRadiologyTemplates();
	}
	

	@RequestMapping(method = RequestMethod.GET)
	public String listForms(
			Model model) {
		return "/module/radiology/template/list";
	}
}
