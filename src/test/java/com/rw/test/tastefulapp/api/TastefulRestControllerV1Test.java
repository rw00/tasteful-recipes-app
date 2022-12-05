package com.rw.test.tastefulapp.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rw.test.tastefulapp.common.api.ProblemResponse;
import com.rw.test.tastefulapp.common.model.ModelMapper;
import com.rw.test.tastefulapp.repository.CookingRecipesRepository;
import com.rw.test.tastefulapp.service.TastefulService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TastefulRestControllerV1.class)
class TastefulRestControllerV1Test {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockitoBean
    CookingRecipesRepository mockRepository;

    @Test
    void controller_responds_with_problem_for_non_existing_entity() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/v1/recipes/497dcba3-ecbf-4587-a2dd-5eb0665e6880"))
                                     .andExpect(status().isNotFound())
                                     .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        ProblemResponse problem = objectMapper.readValue(contentAsString, ProblemResponse.class);
        assertThat(problem.type()).isEqualTo("NOT_FOUND");
        assertThat(problem.status()).isEqualTo(404);
        assertThat(problem.detail()).isEqualTo("No recipe with id=497dcba3-ecbf-4587-a2dd-5eb0665e6880");
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        TastefulService service(CookingRecipesRepository repository) {
            return new TastefulService(repository, null, new ModelMapper());
        }
    }
}
