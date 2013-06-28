package fr.xebia.xke.repository;

import fr.xebia.xke.AbstractTest;
import org.junit.Test;

import javax.inject.Inject;

import static org.fest.assertions.api.Assertions.assertThat;

public class ProductRepositoryTest extends AbstractTest {

    @Inject
    private ProductRepository productRepository;

    @Test
    public void should_find_all_products() {
        assertThat(productRepository.findAll()).isNotEmpty();
    }

}
