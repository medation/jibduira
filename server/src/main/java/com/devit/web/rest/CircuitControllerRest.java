package com.devit.web.rest;

import com.devit.model.Circuit;
import com.devit.model.Monument;
import com.devit.model.User;
import com.devit.service.ISpotService;
import com.devit.service.ITourismService;
import com.devit.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class CircuitControllerRest {

    @Autowired
    public ITourismService tourismService;
    @Autowired
    public ISpotService spotService;
    @Autowired
    public IUserService userService;

    @PostMapping("/addCircuit")
    public Circuit addCircuit(@RequestBody String name){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsernameOrEmail(auth.getName());
        Circuit circuit = new Circuit(name);
        /*
        user.addCircuit(circuit);
        userService.save(user);
        */
        circuit.setUser(user);
        tourismService.saveCircuit(circuit);
        return circuit;
    }

    @RequestMapping(value={"/circuits"}, method = RequestMethod.GET)
    public List<Circuit> getCircuitsApi(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsernameOrEmail(auth.getName());
        return user.getCircuits();
    }

    @RequestMapping(value={"/{id}/circuit"}, method = RequestMethod.GET)
    public Circuit getCircuitsApi(@PathVariable String id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsernameOrEmail(auth.getName());
        Circuit circuit = user.findCircuit(Integer.parseInt(id));
        return circuit;
    }

    @DeleteMapping("/{id}/deleteCircuit")
    public List<Circuit> deleteCircuitApi(@PathVariable String id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsernameOrEmail(auth.getName());
        Circuit circuit = tourismService.findCircuitById(Integer.parseInt(id));
        if(user.deleteCircuit(circuit)){
            circuit.removeAllMonument();
            tourismService.deleteCircuit(Integer.parseInt(id));
            userService.save(user);
        }
        return user.getCircuits();
    }

    @PostMapping("/{id}/addMonumentToCircuit")
    public Circuit updateCircuitApi(@PathVariable String id,@RequestBody Monument monument){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsernameOrEmail(auth.getName());
        Circuit circuit = tourismService.findCircuitById(Integer.parseInt(id));
        if(user.containsCircuit(circuit)) {
            if(circuit.addMonument(monument)){
                tourismService.saveCircuit(circuit);
            }
        }
        return circuit;
    }
    /*
    @DeleteMapping("/{id}/deleteMonumentFromCircuit")
    public Circuit deleteMonumentApi(@PathVariable String id, @RequestBody Monument monument){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsernameOrEmail(auth.getName());
        Circuit circuit = tourismService.findCircuitById(Integer.parseInt(id));
        circuit.removeMonument(monument);
        tourismService.saveCircuit(circuit);
        return circuit;
    }
    */
}
