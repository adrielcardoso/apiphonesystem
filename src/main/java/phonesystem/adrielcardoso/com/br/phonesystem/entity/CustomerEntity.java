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

    @Transient
    private String country;
    @Transient
    private Boolean state;
    @Transient
    private String countryCode;
    @Transient
    private String phoneNumber;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}