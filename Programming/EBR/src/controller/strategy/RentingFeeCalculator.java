package controller.strategy;

import model.invoice.Invoice;
import model.session.Session;

/**
 * description
 *
 * @author Nguyen Thai An
 * <p>
 * creted at: 25/12/2020
 * <p>
 * project name: EBR
 * <p>
 * teacher's name: Dr. Nguyen Thi Thu Trang
 * <p>
 * class name: TT.CNTT ICT 02 - K62
 */
public abstract class RentingFeeCalculator {

    /**
     * Calculate renting fee for a session
     * @param session {@link Session} session to be calculated for
     * @return calculated fee
     */
    public abstract int calculateCurrentRentingFees(Session session);

    /**
     * Calculate renting fee for a session
     * @param invoice {@link Invoice} invoice to be calculated for
     * @return calculated fee
     */
    public abstract int calculateTotalFees(Invoice invoice);
}
