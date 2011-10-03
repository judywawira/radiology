package org.openmrs.module.radiology.web.controller.printworklist;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.openmrs.Concept;
import org.openmrs.api.context.Context;
import org.openmrs.module.radiology.RadiologyService;
import org.openmrs.module.radiology.model.RadiologyTest;
import org.openmrs.module.radiology.util.RadiologyConstants;
import org.openmrs.module.radiology.web.util.RadiologyUtil;
import org.openmrs.module.radiology.web.util.TestModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("RadiologySearchPrintWorklistController")
@RequestMapping("/module/radiology/searchPrintWorklist.form")
public class SearchPrintWorklistController {

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public String searchTest(
			@RequestParam(value = "date", required = false) String dateStr,
			@RequestParam(value = "phrase", required = false) String phrase,
			@RequestParam(value = "investigation", required = false) Integer investigationId, HttpServletRequest request,
			Model model) {
		RadiologyService rs = (RadiologyService) Context
				.getService(RadiologyService.class);
		Concept investigationConcept = Context.getConceptService().getConcept(
				investigationId);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
			List<RadiologyTest> radiologyTests = rs.getAllRadiologyTestsByDate(date, phrase, investigationConcept);
			Map<Concept, Set<Concept>> testTreeMap = (Map<Concept, Set<Concept>>) request
			.getSession().getAttribute(
					RadiologyConstants.SESSION_TEST_TREE_MAP);
			List<TestModel> tests = RadiologyUtil.generateModelsFromTests(radiologyTests, testTreeMap);
			model.addAttribute("tests", tests);
			model.addAttribute("testNo", tests.size());			
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("Error when parsing order date!");			
			return null;
		}
		return "/module/radiology/printworklist/search";
	}
}
