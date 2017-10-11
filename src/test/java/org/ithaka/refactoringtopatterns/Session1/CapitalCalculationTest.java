package org.ithaka.refactoringtopatterns.Session1;

import org.junit.Before;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

public class CapitalCalculationTest {

    private Date maturity;
    private int riskRating;
    private double commitment;
    private CapitalStrategy riskAdjustedCapitalStrategy;
    private double outstanding;
    private Date expiry;
    private CapitalStrategy captialStrategyRCTL;

    @Before
    public void setup(){
        maturity = Date.from(LocalDate.of(2017,9,26).atStartOfDay().toInstant(ZoneOffset.UTC));
        riskRating = 345;
        commitment = 2.00;
        riskAdjustedCapitalStrategy = new CapitalStrategyTermLoan();
        outstanding = 20.00;
        expiry = Date.from(LocalDate.of(2022,9,26).atStartOfDay().toInstant(ZoneOffset.UTC));
        captialStrategyRCTL = new CapitalStrategyRCTL();
    }


}
