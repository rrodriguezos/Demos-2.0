package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.BankService;
import services.CommentService;
import services.DemoService;
import services.DeveloperService;
import services.InstalmentService;
import services.InvestmentService;
import services.InvestorService;
import services.LoanService;
import services.SectionService;
import services.WhitePaperService;
import controllers.AbstractController;
import domain.Bank;
import domain.Demo;
import domain.Developer;
import domain.Investor;

@Controller
@RequestMapping("/dashboard/administrator")
public class DashboardAdministratorController extends AbstractController {

	// Services -------------------------------------------
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private DemoService demoService;
	
	@Autowired
	private DeveloperService developerService;
	
	@Autowired
	private InvestmentService investmentService;
	
	@Autowired
	private InstalmentService instalmentService;
	
	@Autowired
	private BankService bankService;
	
	@Autowired
	private LoanService loanService;
	
	@Autowired
	private WhitePaperService whitePaperService;
	
	@Autowired
	private InvestorService investorService;
	
	@Autowired
	private SectionService sectionService;

	
	// Constructor ----------------------------------------
	public DashboardAdministratorController() {
		super();
	}

	// Dashboard ------------------------------------------
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		ModelAndView result;
		Double avgNumberCommentsPerDemo;
		Collection<Demo> demos25PercentageMoreCommentsThanAvg;
		Collection<Developer> developersMoreCommentsThanAvg;
		Double ratioDemosWithInvestment;
		Collection<Object[]> avgStddevInvestmentsPerInvestor;
		Double avgMoneyInvestInDemos;
		Double avgNumberInstalmentsPerInvestment;
		Collection<Bank> banksWithMoreLoans;
		Double avgNumberPendingLoansPerBank;
		Double avgNumberWhitePaperPerInvestorDouble;
		Collection<Investor>investorMoreAvgWP;
		Integer minSectionWP;
		Integer maxSectionWP;
		Double avgSectionWP;
		
		avgNumberCommentsPerDemo = commentService.averageCommentsPerDemo();
		
		demos25PercentageMoreCommentsThanAvg = demoService.demos25PercentageMoreCommentsThanAvg();
		
		developersMoreCommentsThanAvg = developerService.developersMoreCommentsThanAvg();
		
		ratioDemosWithInvestment = demoService.ratioDemosWithInvestment();
		
		avgStddevInvestmentsPerInvestor = investmentService.avgStddevInvestmentsPerInvestor();
		
		avgMoneyInvestInDemos = instalmentService.avgMoneyInvestInDemos();
		
		avgNumberInstalmentsPerInvestment = instalmentService.avgNumberInstalmentsPerInvestment();
		
		banksWithMoreLoans = bankService.banksWithMoreLoans();
		
		avgNumberPendingLoansPerBank = loanService.avgPendingLoansPerBank();
		
		avgNumberWhitePaperPerInvestorDouble = whitePaperService.avgWhitePaperPerInvestor();
		
		investorMoreAvgWP = investorService.investorWithMoreAvgWhitePaperPerInvestorLastQuarter();
		
		minSectionWP = sectionService.minSectionsPerWhitePaperLastQuarter();
		
		maxSectionWP = sectionService.maxSectionsPerWhitePaperLastQuarter();
		
		avgSectionWP = sectionService.avgSectionsPerWhitePaperLastQuarter();
		
		result = new ModelAndView("administrator/dashboard");
		result.addObject("avgNumberCommentsPerDemo", avgNumberCommentsPerDemo);
		result.addObject("demos25PercentageMoreCommentsThanAvg", demos25PercentageMoreCommentsThanAvg);
		result.addObject("developersMoreCommentsThanAvg", developersMoreCommentsThanAvg);
		
		result.addObject("ratioDemosWithInvestment", ratioDemosWithInvestment);
		result.addObject("avgStddevInvestmentsPerInvestor", avgStddevInvestmentsPerInvestor);
		result.addObject("avgMoneyInvestInDemos", avgMoneyInvestInDemos);
		result.addObject("avgNumberInstalmentsPerInvestment", avgNumberInstalmentsPerInvestment);
		
		result.addObject("banksMoreLoansReq", banksWithMoreLoans);
		result.addObject("avgNumberPendingLoansPerBank", avgNumberPendingLoansPerBank);
		//TODO Faltan dos que nos tienen que confirmar.
		
		result.addObject("avgNumberWhitePaperPerInvestor", avgNumberWhitePaperPerInvestorDouble);
		result.addObject("investorWithMoreAvNumberWPLastQuarter", investorMoreAvgWP);
		result.addObject("minSectionsPerWhitePaper", minSectionWP);
		result.addObject("avgSectionsPerWhitePaper", maxSectionWP);
		result.addObject("maxSectionsPerWhitePaper", avgSectionWP);
		
		
		
		
		result.addObject("requestURI", "/dashboard/administrator/dashboard.do");

		return result;
	}

}
