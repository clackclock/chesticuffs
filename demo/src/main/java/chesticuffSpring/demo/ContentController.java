package chesticuffSpring.demo;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {
    @GetMapping("/")
    public String index(Model model) {
        String content;
        try {
            // Check your path: src/main/resources/static/updateLog.txt
            Resource resource = new ClassPathResource("static/updateLog.txt");

            if (resource.exists()) {
                content = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
            } else {
                content = "Log file not found in resources/static/";
            }
        } catch (IOException e) {
            content = "Error reading the log file: " + e.getMessage();
        }

        model.addAttribute("fileContent", content);
        return "index"; // Looks for templates/index.html
    }
}