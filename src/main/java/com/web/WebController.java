package com.web;

import com.model.EntityModel;
import org.springframework.web.bind.annotation.*;
import com.service.Service;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class WebController {
    @Autowired
    private Service service;

    @RequestMapping(method =  RequestMethod.POST, value = "/api/create")
    public @ResponseBody EntityModel create(@RequestBody String body){

        return service.create(body);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/get")
    public @ResponseBody EntityModel read(@RequestBody String body){
        return service.get(body);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/update")
    public @ResponseBody EntityModel update(@RequestBody String body){

        return service.update(body);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/delete")
    public @ResponseBody EntityModel delete(@RequestBody String body){

        return service.delete(body);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/drop")
    public @ResponseBody Iterable<EntityModel> drop(){
        return service.drop();
    }
}
