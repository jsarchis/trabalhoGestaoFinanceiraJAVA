package vianna.financaInteligente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vianna.financaInteligente.dao.EconomistaDao;
import vianna.financaInteligente.dao.MesFechadoDao;
import vianna.financaInteligente.dao.PoupadorDao;
import vianna.financaInteligente.dto.DadosUserDTO;
import vianna.financaInteligente.model.Economista;
import vianna.financaInteligente.model.MesFechado;
import vianna.financaInteligente.model.Poupador;
import vianna.financaInteligente.service.PoupadorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/poup")
public class PoupadorController {

    @Autowired
    PoupadorDao pp;

    @Autowired
    PoupadorService pService;

    @Autowired
    MesFechadoDao mes;

    @Autowired
    EconomistaDao ec;

    @GetMapping("/dados")
    public String dados(Model model, Authentication use){
        Poupador p = pp.findByLogin(use.getName());
        System.out.println("Chegou aqui"+p.getId());
        model.addAttribute("poupador", p);
        return "meusDados";
    }

    @GetMapping("/showEconomistas")
    public String showEconomistas(Model model, Authentication use){
       Poupador p = pp.findByLogin(use.getName());
            if(p.getEconomista() == null) {
                List<Economista> ecs = new ArrayList<>();
                ecs = ec.findAll();
                //        model.addAttribute("poupador", p);
                model.addAttribute("economistas", ecs);
                return "showEconomistas";
            }else {
                model.addAttribute("economista" , p.getEconomista());
                return "economistaContratado";
            }
    }

    @PostMapping("/fechaMes")
    private String fechaMes(Model model, Authentication use, @ModelAttribute Poupador poupador){
            try {
                pService.fechaMes(pp.findByLogin(use.getName()).getId(), poupador);
                System.out.println("MesFechado");
                return "redirect:/poup/historicoPoupador";
            }catch (Exception e){
                model.addAttribute("poupador", poupador);
                model.addAttribute("erro", e.getMessage());
                System.out.println("Chegou deu erro");
                return "redirect:/poup/dados";
            }
    }



    @GetMapping("/historicoPoupador")
    public String historico(Model model, Authentication use){
        Poupador p = pp.findByLogin(use.getName());
        List<MesFechado> m = new ArrayList<>();
        m = p.getMesesFechados();
        model.addAttribute("meses", m);
        return "historicoPoupador";
    }

    @GetMapping("/dashboard/{id}")
    public String chamaDashboard(@PathVariable("id") int id, Model model){
        Optional<MesFechado> m = mes.findById(id);
        if(m.isPresent()){
            MesFechado mf = m.get();
            model.addAttribute("mes", mf);
        }
        return "dashboard";
    }

    @PostMapping("")
    private String postSaveUser(Model model, Authentication use, @ModelAttribute Poupador poupador){
        try {
//            System.out.println("Chegou pre salvar"+poupador.getId());
            pService.updatePoupador(pp.findByLogin(use.getName()).getId(), poupador);
            model.addAttribute("poupador", poupador);
            model.addAttribute("msg", "Dados Salvos!");
//            System.out.println("Chegou salvou");
            return "redirect:/poup/dados";
        } catch (Exception e){
            model.addAttribute("poupador", poupador);
            model.addAttribute("erro", e.getMessage());
//            System.out.println("Chegou deu erro");
            return "homeLogado";
        }
    }
}
