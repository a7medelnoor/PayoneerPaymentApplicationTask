package com.a7medelnoor.payoneerpaymentapplicationtask.data;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response.Applicable;
import com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response.Links;
import com.google.common.truth.Truth;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryTest {
    List<Applicable> applicables = new ArrayList<Applicable>();
    @Mock
    Repository repository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        applicables.add(new Applicable("American", new Links("https://raw.githubusercontent.com/optile/checkout-android/develop/checkout/src/main/assets/networklogos/amex.png")));

    }

    @Test
    public void test_that_repository_get_applicable_was_called() {
     repository = Mockito.mock(Repository.class);

        when(repository.getApplicable()).thenReturn(applicables);

        repository.getApplicable();
        verify(repository, atLeastOnce()).getApplicable();
    }

    @Test
    public void test_that_repository_return_list_of_applicable() {
       repository = Mockito.mock(Repository.class);

        when(repository.getApplicable()).thenReturn(applicables);

        List<Applicable> app = repository.getApplicable();
        Truth.assertThat(app).isInstanceOf(List.class);


    }
    //TODO test the size of the list is equal to 1

    @Test
    public void test_that_repository_get_applicable_return_list_with_size_equals_one() {
       repository = Mockito.mock(Repository.class);

        when(repository.getApplicable()).thenReturn(applicables);

        List<Applicable> app = repository.getApplicable();
        Truth.assertThat(app.size()).isEqualTo(1);


    }
    //TODO test the content of applicable is equal to what we expect
    @Test
    public void test_that_repository_get_applicable_return_l_with_size_equals_to() {

        repository = Mockito.mock(Repository.class);

        when(repository.getApplicable()).thenReturn(applicables);

        List<Applicable> app = repository.getApplicable();
        Truth.assertThat(app.get(0).getLabel()).isEqualTo("American Express");


    }
}