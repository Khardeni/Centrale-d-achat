package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@Getter
@Setter
public class HolidaysCalender {

    private LocalDate date;
    private String title;

    public HolidaysCalender(LocalDate date, String title) {
        super();
        this.date = date;
        this.title = title;
    }


}
