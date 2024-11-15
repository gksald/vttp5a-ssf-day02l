package sg.edu.nus.iss.vttp5a_ssf_day02l.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

import sg.edu.nus.iss.vttp5a_ssf_day02l.model.Item;
import sg.edu.nus.iss.vttp5a_ssf_day02l.service.ItemService;


@Controller
@RequestMapping("/items")
public class ItemController {

    @Autowired
    ItemService itemService;
    
    @GetMapping("")    
    public String cartItems(Model model) {

        List<Item> items = itemService.getItems();
        model.addAttribute("items", items);
        return "cartlist";
    } 

    @GetMapping("/filter")    
    public String filterCartItems(@RequestParam(name = "itemQuantity") String quantity, Model model) {

        List<Item> items = itemService.getItems();
        List<Item> filterItems = items.stream().filter(a -> a.getQuantity() >= Integer.parseInt(quantity)).collect(Collectors.toList());

        model.addAttribute("items", filterItems);
        return "cartlist";
    } 

}
