package steps.dev.myfinance7.report;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import steps.dev.myfinance7.common.model.report.IPortfolioInstrumentReport;
import steps.dev.myfinance7.report.repository.PortfolioInstrumentReportRepository;

@SpringBootTest
class ReportApplicationTests {
    
    @Autowired
    private PortfolioInstrumentReportRepository repository;
    
	@Test
	void contextLoads() {
	}
        
        @Test
        void testPortfolioInstrumentReport(){
            List<IPortfolioInstrumentReport> report = repository.report();
            
            report.stream()
                    .forEach(this::printPortfolioInstrumentReport);
        }
        
        private void printPortfolioInstrumentReport(IPortfolioInstrumentReport reportItem){
            System.out.println("-------------------------------------");
            System.out.println(reportItem.getPortfolioName());
            System.out.println(reportItem.getInstrumentName());
            System.out.println(reportItem.getLongAmount());
            System.out.println(reportItem.getLongCurrencyAmount());
            System.out.println(reportItem.getCurrency());
        }
}
