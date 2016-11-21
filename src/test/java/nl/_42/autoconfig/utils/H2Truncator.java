package nl._42.autoconfig.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.MetaDataAccessException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class H2Truncator implements DatabaseTruncator {

    public static final String LIQUIBASE_CHANGE_LOG =       "databasechangelog";
    public static final String LIQUIBASE_CHANGE_LOG_LOCK =  "databasechangeloglock";
    public static final String TABLE_NAME_KEY =             "TABLE_NAME";

    List<String> tables = new ArrayList<>();

    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void determineTables() {
        this.tables.addAll(jdbcTemplate.queryForList("SHOW TABLES").stream()
                .map(tableInfo -> (String)tableInfo.get(TABLE_NAME_KEY))
                .filter(tableName -> !tableName.equals(LIQUIBASE_CHANGE_LOG) && !tableName.equals(LIQUIBASE_CHANGE_LOG_LOCK))
                .collect(Collectors.toList()));
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void truncate() throws MetaDataAccessException {
        jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY FALSE;");
        tables.forEach(table -> jdbcTemplate.execute("TRUNCATE TABLE " + table));
        jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY TRUE;");
    }

}
