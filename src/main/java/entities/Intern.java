package entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Intern extends Candidate {

    private String major;

    private int semester;

    private String university;



}
