package vianna.financaInteligente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vianna.financaInteligente.dao.AdmDao;
import vianna.financaInteligente.dao.EconomistaDao;
import vianna.financaInteligente.dao.PoupadorDao;
import vianna.financaInteligente.model.Adm;
import vianna.financaInteligente.model.Economista;
import vianna.financaInteligente.model.Poupador;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    PoupadorDao poup;

    @Autowired
    EconomistaDao ec;

    @Autowired
    AdmDao ad;

    @GetMapping("")
    public String home(){return "home";}

    @GetMapping("login")
    public String login(){return "login";}

    @GetMapping("/quemsomos")
    public String quemSomos(){return "quemsomos";}

    @GetMapping("/homeLogado")
    public String homeLogado(Model model, Authentication use){

        Poupador p = poup.findByLogin(use.getName());
        if(p != null){
            model.addAttribute("poupador", p);
        } else {
            Economista e = ec.findByLogin(use.getName());
            if (e != null) {
                model.addAttribute("economista", e);
            } else {
                Adm a = ad.findByLogin(use.getName());
                if (a != null) {
                    model.addAttribute("adm", a);
                }
            }
        }

        return "homeLogado";}
}
