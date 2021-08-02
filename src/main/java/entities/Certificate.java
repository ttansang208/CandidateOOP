package entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Certificate {
    private UUID id;
    private String name;
    private UUID experience_id;
    private UUID fresher_id;
    private UUID intern_id;
    private  int certificatecount;
}
