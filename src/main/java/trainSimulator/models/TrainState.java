package trainSimulator.models;

/**
 * Created by mitron-wojtek on 06.12.16.
 */
public enum TrainState {
    //PLANNED means train is created and before it's moving, ARRIVING is like 90 seconds before station,
    // ONSTATION is -30:0 seconds to departing, DEPARTED - oh shit, it departed, CANCELLED - admin cancelled train for some odd reason
    //I chose enum, because it is much more type safe than string (which is even deprecated since newest java)
    PLANNED, ARRIVING, ONSTATION, DEPARTED, CANCELLED;
}
