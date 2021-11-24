package com.example.springboot.config;

import org.infinispan.protostream.GeneratedSchema;
import org.infinispan.protostream.SerializationContextInitializer;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;
import com.example.springboot.data.Pokemon;

@AutoProtoSchemaBuilder(
      includeClasses = {
            Pokemon.class
      },
      schemaFileName = "pokemon.proto",
      schemaFilePath = "proto/",
      schemaPackageName = "com.example.springboot.data")
public interface PokemonSchema extends SerializationContextInitializer {
}
