package controller.strategy;

import model.bike.Bike;
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
public class RentingFeeBySecondsCalculator extends RentingFeeCalculator {
    @Override
    public int calculateCurrentRentingFees(Session session) {
        Bike bike = session.getBike();
        int baseCharge = bike.getCharge();
        try {
            long sLength = session.getSessionLength();
            if (sLength < 10) {
                return 0;
            } else if (sLength >= 10 && sLength < 30) {
                return baseCharge;
            } else {
                return (int) (baseCharge + baseCharge * 0.3f * Math.ceil((sLength - 30.0) / (15.0)));
            }
        } catch (NullPointerException e) {
            return 0;
        }
    }

    @Override
    public int calculateTotalFees(Invoice invoice) {
        try {
            Bike bike = invoice.getSession().getBike();
            int baseCharge = bike.getCharge();

            long sLength = invoice.getSession().getSessionLength();
            int totalFees;
            if (sLength < 10) {
                totalFees = 0;
            } else if (sLength >= 10 && sLength < 30) {
                totalFees = baseCharge;
            } else {
                totalFees = (int) (baseCharge + baseCharge * 0.3f * Math.ceil((sLength - 30.0) / 15.0));
            }
            invoice.setTotalFees(totalFees);
            return totalFees;
        } catch (NullPointerException e) {
            return 0;
        }
    }
}
