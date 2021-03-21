package hx.mall.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Api(tags = "ToolController")
@RequestMapping("/tool")
@RestController
public class ToolController {

    @GetMapping
    public ModelAndView _(){
        return new ModelAndView("redirect:/swagger-ui.html");
    }
}
