package vianna.financaInteligente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vianna.financaInteligente.dto.DadosUserDTO;
import vianna.financaInteligente.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userv;

    @GetMapping("/new")
    public String getNewUser(Model model, @ModelAttribute DadosUserDTO dados){

        model.addAttribute("dados", dados);

        return "cadastro";
    }

    @PostMapping("")
    private String postSaveUser(Model model, @ModelAttribute DadosUserDTO dados){
        try {
            userv.salvarUser(dados);
            return "redirect:/user";
        } catch (Exception e){
            model.addAttribute("dados", dados);
            model.addAttribute("erro", e.getMessage());
            return "cadastro";
        }
    }
}
