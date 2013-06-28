package fr.xebia.xke.repository.extractor;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;
import fr.xebia.xke.dto.ProductShortInfo;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class ProductShortInfoExtractor implements ResultSetExtractor<List<ProductShortInfo>> {

    @Override
    public List<ProductShortInfo> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        List<ProductShortInfo> productShortInfos = Lists.newArrayList();

        while (resultSet.next()) {
            productShortInfos.add(buildProductShortInfo(resultSet));
        }

        return productShortInfos;
    }

    @VisibleForTesting
    ProductShortInfo buildProductShortInfo(ResultSet resultSet) throws SQLException {
        ProductShortInfo productShortInfo = new ProductShortInfo();

        productShortInfo.setId(resultSet.getLong("ID"));
        productShortInfo.setName(resultSet.getString("NAME"));
        productShortInfo.setNominal(resultSet.getBigDecimal("NOMINAL"));
        productShortInfo.setSales(resultSet.getString("SALES"));
        productShortInfo.setPricer(resultSet.getString("PRICER"));
        productShortInfo.setTrader(resultSet.getString("SWAPTRADER"));

        return productShortInfo;
    }

}
