package org.springframework.samples.petclinic.recoveryroom;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.pet.Visit;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="recovery_rooms")
public class RecoveryRoom {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotEmpty
    @Size(min=3,max=50)
    private String name;

    @Min(value=0)
    @NotNull
    private double size;

    @NotNull
    @NotEmpty
    private boolean secure=false;

    @OneToMany
    private Collection<Visit> visits;


    @ManyToOne(optional = false)
    @JoinColumn(name="recovery_room_type_id")
    private RecoveryRoomType roomType;
}
