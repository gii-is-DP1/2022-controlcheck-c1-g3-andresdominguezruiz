package org.springframework.samples.petclinic.recoveryroom;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class RecoveryRoomTypeFormatter implements Formatter<RecoveryRoomType>{

    private final RecoveryRoomRepository recoveryRoomRepository;

    @Autowired
    public RecoveryRoomTypeFormatter(RecoveryRoomRepository repo){
        this.recoveryRoomRepository=repo;
    }

    @Override
    public String print(RecoveryRoomType object, Locale locale) {
        return object.getName();
    }

    @Override
    public RecoveryRoomType parse(String text, Locale locale) throws ParseException {
        RecoveryRoomType recoveryRoomType=recoveryRoomRepository.getRecoveryRoomType(text);
        if(recoveryRoomType==null)
            throw new ParseException("type not found: " + text, 0);
        return recoveryRoomType;
    }
    
}
