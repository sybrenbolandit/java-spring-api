package org.shboland;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @GetMapping("")
    @Operation(tags = "Person", description = "Get a greeting")
    public String greeting(@RequestParam(value="name", defaultValue = "you") String name) {
        return "Hello " + name;
    }
}
