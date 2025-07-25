package com.tigo.test.config;

import com.fasterxml.jackson.databind.type.SimpleType;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverterContext;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Schema;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.lang.reflect.Type;

/**
 *
 * @author LLince
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("REST Api Documentation")
                        .description("REST Api Documentation")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Lorenzo Lince")
                                .url("https://www.linkedin.com/in/lorenzo-lince/")
                                .email("lorenzolince@gmail.com"))
                        .license(new License()
                                .name("tigo-test 2.0")
                                .url("https://www.linkedin.com/in/lorenzo-lince/")) // opcional
                );
    }

    @Bean
    public GroupedOpenApi apiGroup() {
        return GroupedOpenApi.builder()
                .packagesToScan("com.tigo.test")
                .pathsToMatch("/api/**")
                .group("default")
                .build();
    }

    @Bean
    public ModelConverter customDateModelConverter() {
        return new ModelConverter() {
            @Override
            public Schema<?> resolve(AnnotatedType type, ModelConverterContext context, Iterator<ModelConverter> chain) {
                Schema<?> schema = null;
                if (type.getType() instanceof Class<?>) {
                    Class<?> clazz = (Class<?>) type.getType();
                    if (Date.class.equals(clazz)) {
                        schema = new Schema<>()
                                .type("string")
                                .format("date")
                                .pattern("yyyy-MM-dd")
                                .example(new Date());
                    } else if (java.sql.Date.class.equals(clazz)) {
                        schema = new Schema<>()
                                .type("string")
                                .format("date")
                                .pattern("yyyy-MM-dd")
                                .example(new Date());
                    } else if (Timestamp.class.equals(clazz)) {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String formattedDate = formatter.format(new Date());
                        schema = new Schema<>()
                                .type("string")
                                .format("yyyy-MM-dd HH:mm:ss")
                                .pattern(formattedDate)
                                .example(formattedDate);
                    } else {

                    }

                    if (schema != null) {
                        return schema;
                    }
                }

                if (chain.hasNext()) {
                    schema = chain.next().resolve(type, context, chain);
                    applyRecursiveFormat(schema, type.getType(), new HashSet<>());
                    return schema;
                }
                return null;
            }
        };
    }

    private void applyRecursiveFormat(Schema<?> schema, Type type, Set<Schema<?>> visited) {
        if (schema == null || visited.contains(schema)) {
            return;
        }
        visited.add(schema);
        if ("date".equals(schema.getFormat())) {
            schema = new Schema<>()
                    .type("string")
                    .format("date")
                    .pattern("yyyy-MM-dd")
                    .example(new Date());
        }

        if ("date-time".equals(schema.getFormat())) {
            if (type instanceof SimpleType) {
                SimpleType simpleType = (SimpleType) type;
                Class<?> rawClass = simpleType.getRawClass();
                if (rawClass.equals(Date.class)) {
                    schema
                            .type("string")
                            .format("date")
                            .pattern("yyyy-MM-dd")
                            .example(new Date());
                } else if (rawClass.equals(java.sql.Date.class)) {
                    schema
                            .type("string")
                            .format("date")
                            .pattern("yyyy-MM-dd")
                            .example(new Date());
                } else {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = formatter.format(new Date());
                    schema
                            .type("string")
                            .format("yyyy-MM-dd HH:mm:ss")
                            .pattern(formattedDate)
                            .example(formattedDate);
                }
            }
        }
    }
}

