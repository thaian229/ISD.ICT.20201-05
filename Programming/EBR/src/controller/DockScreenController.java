package controller;

import model.bike.Bike;
import model.bike.BikeManager;

import java.util.ArrayList;

public class DockScreenController extends BaseController {

    public DockScreenController() {
    }

    public ArrayList<Bike> getBikeList() {
        return BikeManager.getInstance().getBikeList();
    }
}
