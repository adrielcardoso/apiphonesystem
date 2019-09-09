package phonesystem.adrielcardoso.com.br.phonesystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sqlite.Function;
import phonesystem.adrielcardoso.com.br.phonesystem.PersistenceJPAConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class CustomerService
{
    @Autowired
    PersistenceJPAConfig persistenceJPAConfig;

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

    public Object findAllByRegionOk(Integer ddi) throws Exception {
        return this.parseResultData(
                this.getConnection().createStatement().executeQuery(
                        "SELECT * FROM customer u where phone REGEXP '^\\((["+ddi+"]{3})\\)' and \n" +
                                "phone REGEXP '"+this.getRegexByRegion(ddi)+"'"
                )
        );
    }

    public Object findAllByRegionNOk(Integer ddi) throws Exception {
        return this.parseResultData(
                this.getConnection().createStatement().executeQuery(
                        "SELECT * FROM customer u where phone REGEXP '^\\((["+ddi+"]{3})\\)' and \n" +
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

    private List<Object> parseResultData(ResultSet rs) throws Exception{
        ResultSetMetaData resultSetMetaData = rs.getMetaData();
        final int columnCount = resultSetMetaData.getColumnCount();
        List<Object> result = new ArrayList<>();
        while (rs.next()) {
            Object[] values = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                values[i - 1] = rs.getObject(i);
            }
            result.add(values);
        }
        return result;
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
}