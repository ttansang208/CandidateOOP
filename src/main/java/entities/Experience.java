package entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Experience extends Candidate {

    private int expYear;

    private String skill;

}
