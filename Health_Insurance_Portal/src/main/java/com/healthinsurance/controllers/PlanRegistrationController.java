package com.healthinsurance.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.healthinsurance.model.PlanRegistration;
import com.healthinsurance.service.PlanRegisterService;

@Controller
public class PlanRegistrationController {
	@Autowired
	private PlanRegisterService service;

	@GetMapping("/planreg")
	public String loadPlanRegisterForm(Model model) {

		PlanRegistration reg = new PlanRegistration();
		model.addAttribute("planreg", reg);
		model.addAttribute("status");
		return "planRegistration";
	}

	@PostMapping("/planRegister")
	public String handleRegisterButton(@ModelAttribute("plandetails") PlanRegistration plandetails,
			RedirectAttributes msg, Model model) {
		String status = service.savePlanDetails(plandetails);
		msg.addFlashAttribute("status", status);
		return "redirect:/planreg";
	}

	@GetMapping("/viewplans")
	public String getAllPlanDetails(Model model) {

		List<PlanRegistration> allPlans = service.getAllPlanDetails();
		model.addAttribute("allPlans", allPlans);
		return "viewplans";

	}

	@GetMapping("/editPlan")
	public String editPlanBasedOnPlanId(@RequestParam("planId") Integer planId, Model model) {
		PlanRegistration planDetails = service.updatePlanDetailsBasedOnPlanId(planId);
		model.addAttribute("planreg", planDetails);
		return "planRegistration";
	}

	@GetMapping("/deletePlan")
	public String deleteBasedOnPlanId(@RequestParam("planId") Integer planId, RedirectAttributes redirect,
			Model model) {
		PlanRegistration planStatus = service.checkAccountActivationSwitchToDeleteOrActivate(planId);
		if (planStatus.getActiveSwitch().equalsIgnoreCase("N")) {
			redirect.addFlashAttribute("status", "account activated sucessfully");
		} else {
			redirect.addFlashAttribute("status", "account deletes sucessfully");

		}
		return "redirect:/viewplans";

	}
}
