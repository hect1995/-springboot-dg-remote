package com.example.springboot.config;

import com.example.springboot.data.Pokemon;
import com.example.springboot.controller.HelloController;
import org.infinispan.client.hotrod.DataFormat;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.TransactionMode;
import org.infinispan.client.hotrod.impl.query.RemoteQuery;
import org.infinispan.client.hotrod.transaction.lookup.RemoteTransactionManagerLookup;
import org.infinispan.commons.dataconversion.MediaType;
import org.infinispan.commons.marshall.UTF8StringMarshaller;
import org.infinispan.query.remote.client.ProtobufMetadataManagerConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Priority;
import javax.annotation.Resources;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class AppConfiguration {

    @Autowired
    RemoteCacheManager remoteCacheManager;

    Logger logger = LoggerFactory.getLogger(AppConfiguration.class);

    DataFormat jsonStringFormat = DataFormat.builder()
            .valueType(MediaType.APPLICATION_JSON)
            .valueMarshaller(new UTF8StringMarshaller())
            .build();

//    @PostConstruct
//    public void registerProtoSchemas() throws IOException, URISyntaxException {
//        logger.warn("Start method registerProtoSchemas()" +
//                Paths.get(AppConfiguration.class.getClassLoader().getResource("proto/book.proto").toURI()).toAbsolutePath());
//        // Configure your proto schemas here
////        Path protoPath = Paths.get(RemoteQuery.class.getClassLoader().getResource("proto/book.proto").toURI());
////        String proto = Files.readString(protoPath);
//
//        Path path = Paths.get(AppConfiguration.class.getClassLoader().getResource("proto/book.proto").toURI());
//        String proto = Files.readString(path);
//
//        logger.debug("--> Proto schema: " + System.lineSeparator() + proto + System.lineSeparator());
//
//        RemoteCache<String, String> protoCache = remoteCacheManager.getCache(ProtobufMetadataManagerConstants.PROTOBUF_METADATA_CACHE_NAME);
//        protoCache.put("book.proto", proto);
//
//        String errors = protoCache.get(ProtobufMetadataManagerConstants.ERRORS_KEY_SUFFIX);
//        if (errors != null) {
//            throw new IllegalStateException("Some Protobuf schema files contain errors:\n" + errors);
//        }
//
//        logger.warn("Protobuf cache now contains " + protoCache.entrySet().size() + " entries: " +
//        protoCache.entrySet().toString());
//
//    }

    @Bean
    RemoteCache<Integer, String> stringPokemonCache(){
        return remoteCacheManager.getCache("pokemon").withDataFormat(jsonStringFormat);
    }

    @Bean
    RemoteCache<Integer, Pokemon> protostreamPokemonCache(){
        return remoteCacheManager.getCache("pokemon");
    }

}
