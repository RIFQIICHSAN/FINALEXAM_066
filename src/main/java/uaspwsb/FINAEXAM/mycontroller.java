/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uaspwsb.FINAEXAM;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Rifqi Ichsan A
 * 20200140066
 */
@RestController
@CrossOrigin
public class mycontroller {
    
    Person data = new Person();
    PersonJpaController pctrl = new PersonJpaController();
    
    @RequestMapping("/GET")
    @ResponseBody
    public List<Person> getDatabyID(){
        List<Person> dataP = new ArrayList<>();
        try {dataP = pctrl.findPersonEntities();}
        catch(Exception error) {}        
        return dataP;
    }
    
    
    @RequestMapping(value ="/POST", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    @ResponseBody
    public String sendData(HttpEntity<String> kiriman) throws Exception{
        String message="no action";
        String json_receive = kiriman.getBody();
        ObjectMapper mapper = new ObjectMapper();
        Person data = new Person(); 
        data = mapper.readValue(json_receive, Person.class);
        pctrl.create(data);
        message ="Data " + data.getNama()+" Saved";
        return message;
    }
    
    
    @RequestMapping(value ="/PUT", method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public String editData(HttpEntity<String> kiriman) throws Exception{
        String message="no action";
        String json_receive = kiriman.getBody();
        ObjectMapper mapper = new ObjectMapper();
        Person newdataP = new Person(); 
        
        newdataP = mapper.readValue(json_receive, Person.class);
        try {pctrl.edit(newdataP);} catch (Exception e){}
        message ="Data " + newdataP.getNama()+" Updated";
        return message;
    }
    
    
    @RequestMapping(value ="/DELETE", method = RequestMethod.DELETE, consumes = APPLICATION_JSON_VALUE)
    public String deleteData(HttpEntity<String> kiriman) throws Exception{
        String message="no action";
        String json_receive = kiriman.getBody();
        ObjectMapper mapper = new ObjectMapper();
        Person newdataP = new Person();     
        newdataP = mapper.readValue(json_receive, Person.class);
        pctrl.destroy(newdataP.getId());
        return  "Deleted";
    }
    

}