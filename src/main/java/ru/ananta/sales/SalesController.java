package ru.ananta.sales;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SalesController {

    @Autowired
    private SalesJpaRepository salesJpaRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/sales")
    public String sales(@RequestParam("sellerName") String sellerName, Model model, HttpSession session) {
        session.setAttribute("sellerName", sellerName);
        model.addAttribute("sellerName", sellerName);
        if (session.getAttribute("successMessage") != null) {
            model.addAttribute("successMessage", session.getAttribute("successMessage"));
            session.removeAttribute("successMessage");
        }
        model.addAttribute("sales", salesJpaRepository.findAll());
        return "sales";
    }

    @PostMapping("/addSale")
    public String addSale(@ModelAttribute Sale sale, RedirectAttributes redirectAttributes, HttpSession session) {
        salesJpaRepository.save(sale);
        redirectAttributes.addFlashAttribute("successMessage", "Новая запись успешно добавлена!");
        return "redirect:/sales?sellerName=" + session.getAttribute("sellerName");
    }
}
