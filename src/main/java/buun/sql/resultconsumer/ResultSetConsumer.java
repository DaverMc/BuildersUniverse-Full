package buun.sql.resultconsumer;

import java.sql.ResultSet;

public interface ResultSetConsumer<T> {

    T accept(ResultSet resultSet);

}
