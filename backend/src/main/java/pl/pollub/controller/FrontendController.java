package pl.pollub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FrontendController {

    @RequestMapping(value = "/{path:^(?!.*(?:api)|(?:assets)|(?:rozdajto)|(?:^[\\w,\\s-]+(?:\\.[A-Za-z0-9]+)+$)).*$}/**")
    public String redirectToFrontendRoutingForNotApiRequest(@RequestHeader("user-agent") String userAgent,
                                                            HttpServletRequest request) {
        return "forward:/index.html";
    }
}
