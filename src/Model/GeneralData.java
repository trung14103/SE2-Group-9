package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private int country_id;

    @Column(name = "city_id")
    private int city_id;
}
