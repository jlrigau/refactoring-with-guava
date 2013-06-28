package fr.xebia.xke.repository.impl;

import fr.xebia.xke.dto.ProductShortInfo;
import fr.xebia.xke.model.Role;
import fr.xebia.xke.model.User;
import fr.xebia.xke.repository.ProductRepositoryCustom;
import fr.xebia.xke.repository.extractor.ProductShortInfoExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.util.List;

import static fr.xebia.xke.repository.query.ProductRepositoryQuery.GET_ALL_PRODUCTS_QUERY;
import static fr.xebia.xke.repository.query.ProductRepositoryQuery.GET_SALES_TRADER_ALL_PRODUCTS_QUERY;

@Repository
public class ProductRepositoryImpl extends JdbcDaoSupport implements ProductRepositoryCustom {

    private ProductShortInfoExtractor productShortInfoExtractor;

    @Inject
    public ProductRepositoryImpl(DataSource dataSource, ProductShortInfoExtractor productShortInfoExtractor) {
        this.productShortInfoExtractor = productShortInfoExtractor;

        setDataSource(dataSource);
    }

    @Override
    public List<ProductShortInfo> findAllProductInfosBy(User user) {
        List<String> rolesName = user.getRolesName();

        if (rolesName.contains(Role.SALES) || rolesName.contains(Role.TRADER)) {
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("userId", user.getId());

            NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getJdbcTemplate());

            return namedParameterJdbcTemplate.query(GET_SALES_TRADER_ALL_PRODUCTS_QUERY, parameters, productShortInfoExtractor);
        } else {
            return getJdbcTemplate().query(GET_ALL_PRODUCTS_QUERY, productShortInfoExtractor);
        }
    }

    @Override
    public List<ProductShortInfo> findTeamProductInfosBy(User user) {
        return getJdbcTemplate().query(GET_ALL_PRODUCTS_QUERY, productShortInfoExtractor);
    }

    @Override
    public List<ProductShortInfo> findUserProductInfosBy(User user) {
        return getJdbcTemplate().query(GET_ALL_PRODUCTS_QUERY, productShortInfoExtractor);
    }

}
