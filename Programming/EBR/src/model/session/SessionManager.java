package model.session;

import model.bike.Bike;
import model.payment.CreditCard;
import model.payment.PaymentTransaction;

import java.util.ArrayList;

public class SessionManager {
    private static SessionManager instance; //singleton
    private ArrayList<Session> sessions = new ArrayList<>();

    private SessionManager() {
    }

    public SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public Session createSession(Bike bike, CreditCard card, PaymentTransaction rentTransaction) {
        Session newSession = new Session(bike, card, rentTransaction);
        this.updateSessionsData(newSession);
        return newSession;
    }

    private void updateSessionsData(Session newSession) {
        this.sessions.add(newSession);
        //TODO: DB access here
    }

    public Session findSessionById(String id) {
        //TODO: DB access for find sessions by id;

//        for(Session session : sessions) {
//            if (session.getId().equals(id)) {
//                return session;
//            }
//        }
        return null;
    }
}
