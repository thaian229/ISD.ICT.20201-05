package common.exception.cardException;

import common.exception.FormException;

/**
 * class for ...
 *
 * @author mHoang
 * <p>
 * created_at: 21/12/2020
 * <p>
 * project name: EBR
 * <p>
 * teacher's name: Dr. Nguyen Thi Thu Trang
 * <p>
 * class name: TT.CNTT ICT 02 K62
 * <p>
 * helpers: teacher's teaching assistants
 */
public class CardUsedException extends FormException {
    public CardUsedException() {
        super("Card used for another session");
    }
}
