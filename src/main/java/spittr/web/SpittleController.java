package spittr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spittr.Spittle;
import spittr.data.SpittleRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("spittles")
public class SpittleController {
    private SpittleRepository spittleRepository;

    private static final String  MAX_LONG_AS_STRING = Long.toString(Long.MAX_VALUE);

    @Autowired
    public SpittleController(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String spittles(Model model)  {
//        model.addAttribute("spittleList", spittleRepository.findSpittles(Long.MAX_VALUE, 20));
        model.addAttribute(spittleRepository.findSpittles(Long.MAX_VALUE, 20));

        return "spittles";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String spittles(Map model) {
        model.put("spittleList", spittleRepository.findSpittles(Long.MAX_VALUE, 20));

        return "spittles";
    }

    @RequestMapping
    public List<Spittle> spittles() {
        return spittleRepository.findSpittles(Long.MAX_VALUE, 20);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Spittle> spittles(
            @RequestParam(value = "max", defaultValue = "9223372036854775807") long max
            , @RequestParam(value = "count", defaultValue = "20") int count) {
        return spittleRepository.findSpittles(max, count);
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String showSpittle(
            @RequestParam("spittle_id") long spittleId,
            Model model) {
        model.addAttribute(spittleRepository.findOne(spittleId));

        return "spittle";
    }

    @RequestMapping(value = "/{spittleId}", method = RequestMethod.GET)
    public String spittle(
            @PathVariable("spittleId") long spittleId,
            Model model) {
        Spittle spittle = spittleRepository.findOne(spittleId);

        if(spittle == null) {
            throw new SpittleNotFoundException();
        }
        model.addAttribute(spittleRepository.findOne(spittleId));

        return "spittle";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveSpittle(SpittleForm form, Model model) {
        spittleRepository.save(new Spittle(0L, form.getMessage(), new Date(), form.getLongitude(), form.getLatitude()));

        return "redirect:/spittles";

    }
}
