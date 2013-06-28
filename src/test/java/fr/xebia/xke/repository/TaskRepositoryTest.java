package fr.xebia.xke.repository;

import fr.xebia.xke.AbstractTest;
import org.junit.Test;

import javax.inject.Inject;

import static org.fest.assertions.api.Assertions.assertThat;

public class TaskRepositoryTest extends AbstractTest {

    @Inject
    private TaskRepository taskRepository;

    @Test
    public void should_find_all_tasks() {
        assertThat(taskRepository.findAll()).isNotEmpty();
    }

}
