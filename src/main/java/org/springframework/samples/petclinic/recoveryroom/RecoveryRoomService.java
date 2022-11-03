package org.springframework.samples.petclinic.recoveryroom;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RecoveryRoomService {

    private final RecoveryRoomRepository recoveryRoomRepository;

    @Autowired
    public RecoveryRoomService(RecoveryRoomRepository repo){
        this.recoveryRoomRepository=repo;
    }

    @Transactional(readOnly = true)
    public List<RecoveryRoom> getAll(){
        return recoveryRoomRepository.findAll();
    }

    public List<RecoveryRoomType> getAllRecoveryRoomTypes(){
        return null;
    }

    @Transactional(readOnly = true)
    public RecoveryRoomType getRecoveryRoomType(String typeName) {
        return recoveryRoomRepository.getRecoveryRoomType(typeName);
    }

    @Transactional(rollbackFor = DuplicatedRoomNameException.class)
    public RecoveryRoom save(RecoveryRoom p) throws DuplicatedRoomNameException {
        RecoveryRoom r=recoveryRoomRepository.findByName(p.getName()).get();
        if(r==null){
            throw new DuplicatedRoomNameException();
        }else{
            return recoveryRoomRepository.save(p);
        }
    }

    
}
