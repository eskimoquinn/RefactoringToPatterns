package org.ithaka.refactoringtopatterns.Session1;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class CapitalCalculationTest {

    private Date maturity;
    private int riskRating;
    private double commitment;
    private CapitalStrategy riskAdjustedCapitalStrategy;
    private double outstanding;
    private Date expiry;

    @Before
    public void setup(){
        maturity = Date.from(LocalDate.of(2017,9,26).atStartOfDay().toInstant(ZoneOffset.UTC));
        riskRating = 345;
        commitment = 2.00;
        riskAdjustedCapitalStrategy = new CapitalStrategyTermLoan();
        outstanding = 20.00;
        expiry = Date.from(LocalDate.of(2022,9,26).atStartOfDay().toInstant(ZoneOffset.UTC));

    }

    @Test
    public void testTermLoanNoPayments() throws Exception {

        Loan termLoan = new Loan(commitment, riskRating, maturity);

        assertThat(termLoan.getCommitment() , is(commitment));
        assertThat(termLoan.getRiskRating() , is(riskRating));
        assertThat(termLoan.getMaturity() , is(maturity));
        assertThat(termLoan.getOutstanding() , is(0.00));
        assertThat(termLoan.getExpiry() , nullValue());
    }

    @Test
    public void testTermLoanOnePayment() throws Exception {
        Loan termLoan = new Loan(commitment, riskRating, maturity);
        termLoan.setOutstanding(outstanding);
        termLoan.makePayment(3.00);

        assertThat(termLoan.getOutstanding(), is(17.0));

    }

    @Test
    public void testTermLoanWithRiskAdjustedCapitalStrategy() {
        Loan termLoan = new Loan(riskAdjustedCapitalStrategy, commitment,
                outstanding, riskRating, maturity, null); //Null in a constructor, bad practice!

        assertThat(termLoan.getCommitment() , is(commitment));
        assertThat(termLoan.getRiskRating() , is(riskRating));
        assertThat(termLoan.getMaturity() , is(maturity));
        assertThat(termLoan.getOutstanding() , is(outstanding));
        assertThat(termLoan.getExpiry() , nullValue());
    }

    @Test
    public void testRevolver() throws Exception {
        Loan revolverLoan = new Loan(commitment, outstanding, riskRating, null, expiry);

        assertThat(revolverLoan.getCommitment(), is(commitment));
        assertThat(revolverLoan.getMaturity(), nullValue());
    }

    @Test
    public void testRctl(){
        Loan revolverLoan = new Loan(commitment, outstanding, riskRating, maturity, expiry);

        assertThat(revolverLoan.getMaturity(), is(maturity));
        assertThat(revolverLoan.getExpiry(), is(expiry));
    }
}
