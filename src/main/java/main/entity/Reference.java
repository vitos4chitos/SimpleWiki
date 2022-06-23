package main.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "reference")
public class Reference {

    @Id
    @Column(name = "reference_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_id")
    private Long cid;

    @Column(name = "article_id")
    private Long aid;
}
