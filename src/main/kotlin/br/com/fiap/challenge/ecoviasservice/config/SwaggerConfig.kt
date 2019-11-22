package br.com.fiap.challenge.ecoviasservice.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig: WebMvcConfigurationSupport() {

    @Value("\${info.build.version}")
    private lateinit var version: String

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.fiap.challenge.ecoviasservice.location.controller"))
                .paths(PathSelectors.regex("(/incidents.*|/locations.*)"))
                .build()
                .apiInfo(metaData())
    }

    private fun metaData(): ApiInfo {
        val contact = Contact (
            "EcoVias",
            "https://www.ecovias.com.br/",
            "contato@ecorodovias.com.br")

        return ApiInfoBuilder()
                .title("EcoVias Incidents & Locations - REST API")
                .description("Exposes an API of incidents and locations")
                .version(version)
                .contact(contact)
                .build()
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/")

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
    }
}