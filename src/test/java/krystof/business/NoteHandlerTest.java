package krystof.business;

import krystof.Data.Repository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoteHandlerTest {

@Autowired
    private NoteHandler noteHandler;


@MockBean
    private Repository mockRepository;

    @Before
    public void setUp() throws Exception {
        when(mockRepository.findOne(anyLong())).thenReturn(new Note("mock2note", "mock2Label"));
    }

    @Test
    public void findOne() throws Exception {

        noteHandler.findOne(124L);
                verify(mockRepository, times(1)).findOne(eq(124L));
    }

}