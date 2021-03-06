package org.ithaka.refactoringtopatterns.Session1;

import java.util.Date;

public class Loan {
    private final double commitment;
    private double outstanding;
    private final int riskRating;
    private final Date maturity;
    private final Date expiry;
    private CapitalStrategy capitalStrategy;

    //term loan
    public Loan(double commitment, int riskRating, Date maturity) {
        this(commitment, 0.00, riskRating, maturity, null);
    }

    //rctl
    public Loan(double commitment, int riskRating, Date maturity, Date expiry) {
        this(commitment, 0.00, riskRating, maturity, expiry);
}

    //rctl
    public Loan(double commitment, double outstanding,
                int riskRating, Date maturity, Date expiry) {
        this(null, commitment, outstanding, riskRating, maturity, expiry);
    }


    public Loan(CapitalStrategy capitalStrategy, double commitment,
                int riskRating, Date maturity, Date expiry) {
        this(capitalStrategy, commitment, 0.00, riskRating, maturity, expiry);
    }
    public Loan(CapitalStrategy capitalStrategy, double commitment,
                double outstanding, int riskRating,
                Date maturity, Date expiry) {
        this.commitment = commitment;
        this.outstanding = outstanding;
        this.riskRating = riskRating;
        this.maturity = maturity;
        this.expiry = expiry;
        this.capitalStrategy = capitalStrategy;

        if (capitalStrategy == null) {
            if (expiry == null)
                this.capitalStrategy = new CapitalStrategyTermLoan();
            else if (maturity == null)
                this.capitalStrategy = new CapitalStrategyRevolver();
            else
                this.capitalStrategy = new CapitalStrategyRCTL();
        }
    }
    public void makePayment(double payment) {
        outstanding -= payment;
    }

    public double getCommitment() {
        return commitment;
    }

    public double getOutstanding() {
        return outstanding;
    }

    public int getRiskRating() {
        return riskRating;
    }

    public Date getMaturity() {
        return maturity;
    }

    public Date getExpiry() {
        return expiry;
    }

    public CapitalStrategy getCapitalStrategy() {
        return capitalStrategy;
    }

    public Loan setCapitalStrategy(CapitalStrategy capitalStrategy) {
        this.capitalStrategy = capitalStrategy;
        return this;
    }

    public Loan setOutstanding(double outstanding) {
        this.outstanding = outstanding;
        return this;
    }


}
