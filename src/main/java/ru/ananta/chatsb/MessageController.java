package ru.ananta.chatsb;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class MessageController {

    private MessageRepository messageRepository;
    private HttpSession httpSession;

    @Autowired
    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String author, Map<String, Object> model, HttpSession session) {
        session.setAttribute("author", author);
        model.put("messages", messageRepository.findAll());
        model.put("author", author);
        return "chat";
    }

    @PostMapping("/add")
    public String add(@RequestParam String text, Map<String, Object> model, HttpSession session) {
        Message message = new Message(text, session.getAttribute("author").toString());
        this.messageRepository.save(message);

        Iterable<Message> messages = this.messageRepository.findAll();
        model.put("messages", messages);
        return "redirect:/chat?author=" + session.getAttribute("author");
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Map<String, Object> model, HttpSession session) {
        Iterable<Message> messages;
        if(filter != null && !filter.isEmpty()){
            messages = messageRepository.findByAuthor(filter);
        }else{
            messages = this.messageRepository.findAll();
        }
        model.put("messages", messages);
        return "chat";
    }
}
