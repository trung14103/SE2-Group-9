package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "data")
public class GeneralData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "recovered")
    private int recovered;

    @Column(name = "infected")
    private int infected;

    @Column(name = "critical")
    private int critical;

    @Column(name = "death")
    private int death;

    @Column(name = "country_id")
    private long country_id;

    @Column(name = "city_id")
    private long city_id;

    @Column(name = "updated_day")
    private Date updatedDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;
}
