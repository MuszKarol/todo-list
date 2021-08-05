package pl.pieshakelbery.todo.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableGenerator(name = "user_tab", table = "ID_GEN",
        pkColumnName = "GEN_KEY",
        valueColumnName = "GEN_VALUE",
        pkColumnValue = "ID", initialValue = 0, allocationSize = 1)
@Table(name = "user_account")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "user_tab")
    @Column(name = "id")
    private int id;

    @Column(unique = true)
    private String email;

    private String password;
    private Date age;

    @Type(type= "org.hibernate.type.BooleanType")
//    @Column(columnDefinition = "BOOL default true")
    private boolean active = true;

//    @Column(columnDefinition = "TINYINT")
//    @Type(type = "org.hibernate.type.NumericBooleanType")
//    public boolean active = true;


    private String role = "USER";
}
