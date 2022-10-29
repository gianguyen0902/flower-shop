package com.FlowerShop.FlowerShop.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {
    private RegistrationService registrationService;

    @PostMapping
    public ModelAndView register(RegistrationRequest request) {
        registrationService.register(request);
        ModelAndView mv = new ModelAndView("redirect:/loginpsage");
        return mv;

    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

}
