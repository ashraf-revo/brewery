package io.spring.cloud.samples.brewery.aggregating;

import io.spring.cloud.samples.brewery.common.model.Ingredient;
import io.spring.cloud.samples.brewery.common.model.IngredientType;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static io.spring.cloud.samples.brewery.common.TestConfigurationHolder.TEST_COMMUNICATION_TYPE_HEADER_NAME;

@FeignClient(Collaborators.ZUUL)
interface IngredientsProxy {

	@RequestMapping(value = "/ingredient/{ingredient}", method = RequestMethod.POST)
	Ingredient ingredients(@PathVariable("ingredient") IngredientType ingredientType,
						   @RequestHeader("PROCESS-ID") String processId,
						   @RequestHeader(TEST_COMMUNICATION_TYPE_HEADER_NAME) String testCommunicationType);
}