package phonesystem.adrielcardoso.com.br.phonesystem.entity;

import org.springframework.lang.NonNull;

import javax.persistence.*;

@Table(name = "customer")
@Entity
public class CustomerEntity
{
    @NonNull
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(columnDefinition = "char(50)")
    private String name;

    @Column(columnDefinition = "char(50)")
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}