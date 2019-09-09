package phonesystem.adrielcardoso.com.br.phonesystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sqlite.Function;
import phonesystem.adrielcardoso.com.br.phonesystem.PersistenceJPAConfig;
import phonesystem.adrielcardoso.com.br.phonesystem.entity.CustomerEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CustomerService
{
    @Autowired
    PersistenceJPAConfig persistenceJPAConfig;

    public Object findAllByAnything() throws Exception {
        return this.parseResultData(
                this.getConnection().createStatement().executeQuery(
                        "SELECT * FROM customer u"
                )
        );
    }

    public Object findAllByOk() throws Exception {
        return this.parseResultData(
                this.getConnection().createStatement().executeQuery(
                        "SELECT * FROM customer u where \n" +
                                "phone REGEXP '\\(237\\)\\ ?[2368]\\d{7,8}$' or\n" +
                                "phone REGEXP '\\(251\\)\\ ?[1-59]\\d{8}$' or \n" +
                                "phone REGEXP '\\(212\\)\\ ?[5-9]\\d{8}$ ' or \n" +
                                "phone REGEXP '\\(258\\)\\ ?[28]\\d{7,8}$' or \n" +
                                "phone REGEXP '\\(256\\)\\ ?\\d{9}$'"
                )
        );
    }

    public Object findAllByNOk() throws Exception {
        return this.parseResultData(
                this.getConnection().createStatement().executeQuery(
                        "SELECT * FROM customer u where \n" +
                                "phone not REGEXP '\\(237\\)\\ ?[2368]\\d{7,8}$' and\n" +
                                "phone not REGEXP '\\(251\\)\\ ?[1-59]\\d{8}$' and\n" +
                                "phone not REGEXP '\\(212\\)\\ ?[5-9]\\d{8}$ ' and\n" +
                                "phone not REGEXP '\\(258\\)\\ ?[28]\\d{7,8}$' and \n" +
                                "phone not REGEXP '\\(256\\)\\ ?\\d{9}$'"
                )
        );
    }

    public Object findAllByRegion(Integer ddi) throws Exception {
        return this.parseResultData(
                this.getConnection().createStatement().executeQuery(
                        "SELECT * FROM customer u where phone like '("+ddi+")%'"
                )
        );
    }

    public Object findAllByRegionOk(Integer ddi) throws Exception {
        return this.parseResultData(
                this.getConnection().createStatement().executeQuery(
                        "SELECT * FROM customer u where phone  like '("+ddi+")%' and \n" +
                                "phone REGEXP '"+this.getRegexByRegion(ddi)+"'"
                )
        );
    }

    public Object findAllByRegionNOk(Integer ddi) throws Exception {
        return this.parseResultData(
                this.getConnection().createStatement().executeQuery(
                        "SELECT * FROM customer u where phone  like '("+ddi+")%' and \n" +
                                "phone not REGEXP '"+this.getRegexByRegion(ddi)+"'"
                )
        );
    }

    private Connection getConnection() throws Exception{
        Connection conn = persistenceJPAConfig.entityManagerFactory().getDataSource().getConnection();
        this.createFunctionRegex(conn);
        return conn;
    }

    private void createFunctionRegex(Connection conn) throws Exception{
        Function.create(conn, "REGEXP", new Function() {
            @Override
            protected void xFunc() throws SQLException {
                String expression = value_text(0);
                String value = value_text(1);
                if (value == null)
                    value = "";
                Pattern pattern=Pattern.compile(expression);
                result(pattern.matcher(value).find() ? 1 : 0);
            }
        });
    }

    private List<CustomerEntity> parseResultData(ResultSet rs) throws Exception{
        List<CustomerEntity> result = new ArrayList<>();
        while (rs.next()) {
            CustomerEntity temp = new CustomerEntity();
            temp.setName(String.valueOf(rs.getObject(2)));
            temp.setPhone(String.valueOf(rs.getObject(3)));
            Integer ddi = this.getDDI(temp.getPhone());
            temp.setCountryCode(String.valueOf(ddi));
            temp.setState(this.stateNumberPhone(
                    temp.getPhone(), this.getRegexByRegion(ddi)
            ));
            temp.setCountry(this.getCountryNameByDDI(ddi));
            result.add(temp);
        }
        return result;
    }

    private Boolean stateNumberPhone(String phone, String regex) throws Exception{
        return Pattern.matches(regex, phone);
    }

    private Integer getDDI(String phone) throws Exception{
        final String regex = "^\\(([0-9]{3})\\)";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(
                phone
        );
        while (matcher.find()) {
            return Integer.valueOf(matcher.group(1));
        }
        throw new Exception("DDI not found");
    }

    private String getRegexByRegion(Integer ddi) throws Exception{
        if(ddi == 237){
            return "\\(237\\)\\ ?[2368]\\d{7,8}$";
        }
        if(ddi == 251){
            return "\\(251\\)\\ ?[1-59]\\d{8}$";
        }
        if(ddi == 212){
            return "\\(212\\)\\ ?[5-9]\\d{8}$";
        }
        if(ddi == 258){
            return "\\(258\\)\\ ?[28]\\d{7,8}$";
        }
        if(ddi == 256){
            return "\\(256\\)\\ ?\\d{9}$";
        }
        throw new Exception("Region not found");
    }

    private String getCountryNameByDDI(Integer ddi) throws Exception{
        if(ddi == 237){
            return "Cameroon";
        }
        if(ddi == 251){
            return "Ethiopia";
        }
        if(ddi == 212){
            return "Morocco";
        }
        if(ddi == 258){
            return "Mozambique";
        }
        if(ddi == 256){
            return "Uganda";
        }
        throw new Exception("Country not found");
    }
}