package trainSimulator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by mitron-wojtek on 06.12.16.
 */
@Controller
@RequestMapping("/simulation")
public class SimulationController {
    @RequestMapping(method = RequestMethod.GET)
    public String getSimulation() {
        return "simulation";
    }
}
