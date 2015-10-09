package edu.umich.icpsr.sead.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.umich.icpsr.sead.dao.SeadDAO;
import edu.umich.icpsr.sead.model.ResearchObjectOreMap;
import edu.umich.icpsr.sead.model.ResearchObjectRequest;
import edu.umich.icpsr.sead.service.SeadDepositService;
import edu.umich.icpsr.util.ExceptionUtil;

@Controller
public class HomeController {
	@Autowired
	SeadDepositService seadDepositService;

	@Autowired
	SeadDAO seadDAO;

	@RequestMapping("/")
	public String welcome(HttpServletRequest req) {
		return "index";
	}

	@RequestMapping("/sead-cp")
	public String seadRequests(HttpServletRequest req) {
		return "sead-cp";
	}

	@RequestMapping("/invoke")
	public void invokeRequests(HttpServletRequest req, HttpServletResponse res, @RequestParam String researchObjectUrl, @RequestParam(required = false) String ack,
			@RequestParam(required = false) String doi) {
		try {
			PrintWriter writer = res.getWriter();
			writer.println("************** BEGIN *********************");
			writer.flush();
			seadDepositService.processPendingDeposits(researchObjectUrl, writer, ack != null, doi != null);
			writer.println("************** END *********************");
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/testRO")
	public void testRO(HttpServletRequest req, HttpServletResponse res, @RequestParam String urlStr) {
		PrintWriter writer = null;
		try {
			writer = res.getWriter();
			writer.println("************** BEGIN *********************");
			writer.flush();
			writer.println("Processing " + urlStr);
			writer.flush();
			ResearchObjectRequest roRequest = seadDAO.getResearchObjectsRequest(urlStr);
			writer.println("SUCCESS");
			writer.flush();
			writer.println(roRequest.toString());
			writer.flush();
		} catch (Exception e) {
			writer.println("FAILED; " + e.getMessage());
			ExceptionUtil.print(e, writer);
			writer.flush();
		} finally {
			writer.println("************** END *********************");
			writer.flush();
			writer.close();
		}
	}

	@RequestMapping("/testROOreMap")
	public void testROOreMap(HttpServletRequest req, HttpServletResponse res, @RequestParam String urlStr) {

		PrintWriter writer = null;
		try {
			writer = res.getWriter();
			writer.println("************** BEGIN *********************");
			writer.flush();
			writer.println("Processing " + urlStr);
			writer.flush();
			ResearchObjectOreMap oreMap = seadDAO.getResearchObjectOreMap(urlStr);
			writer.println("SUCCESS");
			writer.flush();
			writer.println(oreMap.toString());
			writer.flush();
		} catch (Exception e) {
			writer.println("FAILED; ");
			ExceptionUtil.print(e, writer);
			writer.flush();
		} finally {
			writer.println("************** END *********************");
			writer.flush();
			writer.close();
		}
	}

}
