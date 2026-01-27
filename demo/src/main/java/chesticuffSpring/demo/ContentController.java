package chesticuffSpring.demo;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;

@Controller
public class ContentController {
    public String index(Model model) {
        try {

            Resource resource = new ClassPathResource("/static/updateLog.txt");
            String content = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

            model.addAttribute("fileContent", content);
        } catch (IOException e) {
            model.addAttribute("fileContent", "Could not load update log.");
        }
        return "index";
    }
}