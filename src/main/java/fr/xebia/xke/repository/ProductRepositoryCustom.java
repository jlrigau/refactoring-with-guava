package fr.xebia.xke.repository;

import fr.xebia.xke.dto.ProductShortInfo;
import fr.xebia.xke.model.User;

import java.util.List;

public interface ProductRepositoryCustom {

    List<ProductShortInfo> findAllProductInfosBy(User user);

    List<ProductShortInfo> findUserProductInfosBy(User user);

    List<ProductShortInfo> findTeamProductInfosBy(User user);

}
