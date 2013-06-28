package fr.xebia.xke.repository;

import fr.xebia.xke.AbstractTest;
import fr.xebia.xke.model.User;
import org.junit.Test;

import javax.inject.Inject;

import static org.fest.assertions.api.Assertions.assertThat;

public class UserRepositoryTest extends AbstractTest {

    @Inject
    private UserRepository userRepository;

    @Test
    public void should_find_by_name() {
        // Given
        String name = "user.sales.europe";

        // When
        User user = userRepository.findByName(name);

        // Then
        assertThat(user.getName()).isEqualTo(name);
    }

}
