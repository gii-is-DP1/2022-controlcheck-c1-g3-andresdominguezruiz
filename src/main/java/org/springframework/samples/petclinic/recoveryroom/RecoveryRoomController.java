package org.springframework.samples.petclinic.recoveryroom;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/recoveryroom")
public class RecoveryRoomController {

    private RecoveryRoomService recoveryRoomService;

    private final String RECOVERY_ROOM_CREATE_OR_UPDATE_FORM="recoveryroom/createOrUpdateRecoveryRoomForm";
    private final String WELCOME_VIEW="welcome";


    @Autowired
    private RecoveryRoomController(RecoveryRoomService serv){
        this.recoveryRoomService=serv;
    }

    @GetMapping("/create")
    public ModelAndView newRecoveryRoom(){
        RecoveryRoom rec=new RecoveryRoom();
        ModelAndView result=new ModelAndView(RECOVERY_ROOM_CREATE_OR_UPDATE_FORM);
        result.addObject("recoveryRoom", rec);
        return result;
    }

    @PostMapping("/create")
    public ModelAndView proccessCreatedRecoveryRoom(@Valid RecoveryRoom newRec,BindingResult br){
        ModelAndView res=null;
        if(br.hasErrors()){
            res= new ModelAndView(RECOVERY_ROOM_CREATE_OR_UPDATE_FORM,br.getModel());
        }
        else{
            try{
                this.recoveryRoomService.save(newRec);
                res=new ModelAndView(WELCOME_VIEW);
                res.addObject("message","Perfecto");
            } catch(DuplicatedRoomNameException e){
                br.rejectValue("name", "duplicate", "already exists");
                res= new ModelAndView(RECOVERY_ROOM_CREATE_OR_UPDATE_FORM,br.getModel());
            }
        }
        return res;
    }




    
}
