package com.example.springboot.config;

import org.infinispan.commons.marshall.ProtoStreamMarshaller;
import org.infinispan.spring.starter.remote.InfinispanRemoteCacheCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Configuration
public class InfinispanConfiguration {

   @Bean
   @Order(Ordered.HIGHEST_PRECEDENCE)
   public InfinispanRemoteCacheCustomizer caches() {
      return b -> {
         b.remoteCache("pokemon")
                 .configuration("<distributed-cache name=pokemon>" +
                         "<encoding media-type=\"application/x-protostream\"/>" +
                         "</distributed-cache>");

         b.remoteCache("pokemon").marshaller(ProtoStreamMarshaller.class);

         // Add marshaller in the client, the class is generated from the interface in compile time
         b.addContextInitializer(new PokemonSchemaImpl());
      };
   }
}