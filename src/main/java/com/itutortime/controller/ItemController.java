package com.itutortime.controller;

import com.itutortime.model.Item;
import com.itutortime.repo.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {
    @Autowired
    ItemRepository itemRepo;

    @RequestMapping("/getAllItems")
    @ResponseBody
    public List getAllItems(){
        return itemRepo.getAllItems();
    }

    @RequestMapping("/getItem")
    @ResponseBody
    public Item getItem(@RequestParam("itemId") int itemId){
        return itemRepo.getItem(itemId);
    }

    @RequestMapping("/addItem")
    @ResponseBody
    public String addItem(@RequestParam("id") int id,@RequestParam("name") String name,
                              @RequestParam("category") String category){
        if(itemRepo.addItem(id,name,category) >= 1){
            return "Item Added Successfully";
        }else{
            return "Something went wrong !";
        }
    }
    @RequestMapping("/deteteItem")
    @ResponseBody
    public String deteteItem(@RequestParam("itemId") int itemId){
        if(itemRepo.deleteItem(itemId) >= 1){
            return "Item Deleted Successfully";
        }else{
            return "Something went wrong !";
        }
    }
}
