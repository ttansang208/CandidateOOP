package entities;

import entities.enums.CandidateType;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public abstract class Candidate {

    public static int CANDIDATE_COUNT = 0;

    protected UUID id;

    protected String name;

    protected LocalDate birthDay;

    protected String phone;

    protected String email;

    protected CandidateType type;

}
