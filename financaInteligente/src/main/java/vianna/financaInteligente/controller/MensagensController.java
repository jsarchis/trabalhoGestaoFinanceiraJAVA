package vianna.financaInteligente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vianna.financaInteligente.dao.ComunicacaoPoupEconDao;
import vianna.financaInteligente.model.ComunicacaoPoupEcon;
import vianna.financaInteligente.model.MesFechado;
import vianna.financaInteligente.model.Poupador;
import vianna.financaInteligente.service.ComunicacaoService;
import vianna.financaInteligente.service.PoupadorService;

import java.util.Optional;

@Controller
@RequestMapping("/msg")
public class MensagensController {


    @Autowired
    ComunicacaoService msgServise;

    @Autowired
    PoupadorService pServ;

    @GetMapping("/pedirAuxilio/{id}")
    public String pedirAuxilio(@PathVariable("id") int id, Model model, Authentication use) {
        Poupador p = pServ.findByLoginName(use.getName());
        model.addAttribute("msg", msgServise.criarMsg(id,p.getId()));
        return "comunicacao";
    }


    @PostMapping("")
    private String postEnvio(Model model, Authentication use, @ModelAttribute ComunicacaoPoupEcon msg){
        System.out.println("Chegou controler msg");
            msgServise.msgToEcon(msg);
            model.addAttribute("msg", msg);
            return "testemsg";
    }
}
