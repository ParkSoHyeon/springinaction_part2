package spittr.web;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.s3.metrics.S3ServiceMetric;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.sun.xml.internal.ws.wsdl.writer.document.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spittr.Spitter;
import spittr.Spittle;
import spittr.data.SpitterRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/spitter")
public class SpitterController {
    private SpitterRepository spitterRepository;

    @Autowired
    public SpitterController(SpitterRepository spitterRepository) {
        this.spitterRepository = spitterRepository;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute(new Spitter());  //객체의 key가 spitter인 모델
        return "registerForm";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistration(
//            @RequestPart("profilePicture") byte[] profilePicture    //파일 타입 알 수 없음, 원래 파일의 이름 알 수 없음
//            @RequestPart("profilePicture") Part profilePicture  //Part 파일 업로드, StandardServletMultipartResolver 빈 설정할 필요 없음
            @RequestPart("profilePicture") MultipartFile profilePicture
            , @Valid  Spitter spitter
            , Errors errors
//            , Model model
            , RedirectAttributes model) {
        if(errors.hasErrors())
            return "registerForm";

        spitterRepository.save(spitter);

        //리다이렉션 데이터 전달 방법3
        model.addAttribute("username", spitter.getUsername());
        model.addFlashAttribute("spitter", spitter);

        return "redirect:/spitter/{username}";

        //리다이렉션 데이터 전달 방법2
//        model.addAttribute("username", spitter.getUsername());
//        return "redirect:/spitter/{username}";  //모델의 애트리뷰트와 리다이렉션 패스의 플레이스 홀더에 채워주는 방법

        //리다이렉션 데이터 전달 방법1
//        return "redirect:/spitter/" + spitter.getUsername();
    }



    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String showSpitterProfile(
            @PathVariable("username") String username
            , Model model
    ) {
        if(model.containsAttribute("spitter")) {
            model.addAttribute(spitterRepository.findByUsername(username));
        }
//        Spitter spitter = spitterRepository.findByUsername(username);
//
//        model.addAttribute(spitter);

        return "profile";
    }

}
