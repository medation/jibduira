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
        user.addCircuit(circuit);
        userService.save(user);
        return circuit;
    }
}
