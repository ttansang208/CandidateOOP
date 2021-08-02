package entities;

import entities.enums.GraduationRank;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Fresher extends Candidate {

    private LocalDate graduation;

    private GraduationRank rank;

    private String university;


}
