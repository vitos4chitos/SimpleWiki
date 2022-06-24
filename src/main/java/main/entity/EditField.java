package main.entity;

import lombok.*;

@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class EditField {
    private String[] auxT;
    private String title;
    private String[] categories;

}
