package fr.xebia.xke.repository.query;

public final class ProductRepositoryQuery {

    public static final String GET_SALES_TRADER_ALL_PRODUCTS_QUERY = ""
            + "SELECT product.id ID, product.name NAME, product.nominal NOMINAL, sales.name SALES,\n"
            + "       pricer.name PRICER, swaptrader.name SWAPTRADER\n"
            + "FROM product product\n"
            + "       left join user sales on sales.id = product.sales_id\n"
            + "       left join user pricer on pricer.id = product.pricer_id\n"
            + "       left join user swaptrader on swaptrader.id = product.swaptrader_id,\n"
            + "     user initUser\n"
            + "       inner join user_role initUserRole on initUserRole.user_id = initUser.id\n"
            + "         inner join role initRole on initRole.id = initUserRole.roles_id\n"
            + "       inner join team_members_by_user members on members.user_id = initUser.id\n"
            + "         inner join user member on member.id = members.member_id\n"
            + "           inner join user_role memberUserRole on memberUserRole.user_id = member.id\n"
            + "             inner join role memberRole on memberRole.id = memberUserRole.roles_id\n"
            + "WHERE initUser.id = :userId\n"
            + "  AND ((initRole.name IN ('Sales', 'Sales-Analyst') AND memberRole.name = 'Sales' AND (product.sales_id = member.id OR product.salesinitiator_id = member.id))\n"
            + "    OR (initRole.name = 'Trader' AND memberRole.name = 'Trader' AND (product.cdstrader_id = member.id OR product.xccytrader_id = member.id OR product.swaptrader_id = member.id)))\n";

    public static final String GET_ALL_PRODUCTS_QUERY = ""
            + "SELECT product.id ID, product.name NAME, product.nominal NOMINAL, sales.name SALES,\n"
            + "       pricer.name PRICER, swaptrader.name SWAPTRADER\n"
            + "FROM product product\n"
            + "       left join user sales on sales.id = product.sales_id\n"
            + "       left join user pricer on pricer.id = product.pricer_id\n"
            + "       left join user swaptrader on swaptrader.id = product.swaptrader_id\n";

}
