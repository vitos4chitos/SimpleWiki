package main.entity;

import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.sql.Array;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "article")
@TypeDefs({
        @TypeDef(
                name = "string-array",
                typeClass = StringArrayType.class
        )
})
public class Article {

    @Id
    @Column(name = "article_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_timestamp")
    private Timestamp createTimestamp;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @Column(name = "language")
    private String language;

    @Column(name = "wiki")
    private String wiki;

    @Column(name = "title")
    private String title;

    @Type(type = "string-array")
    @Column(name = "auxiliary_text", columnDefinition = "text[]")
    private String[] auxT;
}
