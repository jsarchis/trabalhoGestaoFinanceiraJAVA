package vianna.financaInteligente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vianna.financaInteligente.dao.ComunicacaoPoupEconDao;
import vianna.financaInteligente.dao.EconomistaDao;
import vianna.financaInteligente.dao.PoupadorDao;
import vianna.financaInteligente.dto.UserSecurityDetails;
import vianna.financaInteligente.model.ComunicacaoPoupEcon;
import vianna.financaInteligente.model.Economista;
import vianna.financaInteligente.model.MesFechado;
import vianna.financaInteligente.model.Poupador;
import vianna.financaInteligente.service.EconomistaService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/econ")
public class EconomistaController {


    @Autowired
    ComunicacaoPoupEconDao ms;

    @Autowired
    EconomistaDao ec;

    @Autowired
    EconomistaService eService;

    @Autowired
    PoupadorDao poup;

    @GetMapping("/dados")
    public String dados(Model model, Authentication use){
        Economista e = ec.findByLogin(use.getName());
        Economista eFinal = eService.findEconomistaCompleto(e.getId());
        System.out.println("Chegou aqui"+e.getId());
//        List<Poupador> poupadores = e.getPoupadores();
//        model.addAttribute("poupadores", poupadores);
        model.addAttribute("economista", eFinal);
        return "dadosEcon";
    }

    @GetMapping("/clientes")
    public String clientesEcon(Model model, Authentication use){
        Economista e = ec.findByLogin(use.getName());
        Economista eFinal = eService.findEconomistaCompleto(e.getId());
        model.addAttribute("poupadores" , eFinal.getPoupadores());
        return "clientes";
    }

    @GetMapping("/mensagens")
    public String mensagens(Model model, Authentication use){
        Economista e = ec.findByLogin(use.getName());
        model.addAttribute("msgs", e.getMensagens());
        return "mensagensEcon";
    }

    @GetMapping("/caixaMsgEcon/{id}")
    public String chamaMsg(@PathVariable("id") int id, Model model, @ModelAttribute ComunicacaoPoupEcon msg){
        Optional<ComunicacaoPoupEcon> m = ms.findById(id);
        if(m.isPresent()){
            ComunicacaoPoupEcon mFinal = m.get();
            model.addAttribute("msg", mFinal);
        }
        return "comunicacaoEconPoup";
    }

//    @PostMapping("/aceitaPoup")
//    public String salvaPoupador(Model model, @ModelAttribute ComunicacaoPoupEcon msg){
//        Optional<Economista> e = ec.findById(msg.getEconomista().getId());
//        Optional<Poupador> p = poup.findById(msg.getPoupador().getId());
//        if(e.isPresent() && p.isPresent()){
//            Economista eFinal = e.get();
//            Poupador pFinal = p.get();
//            eFinal.getPoupadores().add(pFinal);
//            ec.save(eFinal);
//            model.addAttribute("msg", msg);
//        }
//
//        return "comunicacaoEconPoup";
//    }

    @PostMapping("/aceitaPoup")
    public ResponseEntity<Map<String, Object>> acceptMentorship(@RequestBody ComunicacaoPoupEcon msg) {
        Optional<Economista> e = ec.findById(msg.getEconomista().getId());
        Optional<Poupador> p = poup.findById(msg.getPoupador().getId());
        if (e.isPresent() && p.isPresent()) {
            Economista eFinal = e.get();
            Poupador pFinal = p.get();
            eFinal.getPoupadores().add(pFinal);
            ec.save(eFinal);
            return ResponseEntity.ok(Map.of("success", true));
        }
        return ResponseEntity.status(400).body(Map.of("success", false, "message", "Economista ou Poupador não encontrado"));
    }
}
