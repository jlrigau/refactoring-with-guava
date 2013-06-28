package fr.xebia.xke.service;

import fr.xebia.xke.dto.ProductShortInfo;
import fr.xebia.xke.model.User;

import java.util.List;

public interface BlotterService {

    List<ProductShortInfo> findProductsForAllProductsBlotter(User user);

    List<ProductShortInfo> findProductsForTeamBlotter(User user);

    List<ProductShortInfo> findProductsForUserBlotter(User user);

}
