package com.example.springboot.data;

import lombok.*;

import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;
import org.infinispan.protostream.annotations.ProtoDoc;

import java.io.Serializable;

/**
 * Annotated with Protostream
 */
@ProtoDoc("@Indexed")
@Getter
@Setter
public class Pokemon implements Serializable {

   @ProtoField(number = 1)
   int id;
  
   @ProtoField(number = 2)
   String pokemon;

   @ProtoField(number = 3)
   String type;

   public Pokemon() {

   }

   @ProtoFactory
   public Pokemon(int id, String pokemon, String type) {
	  this.id = id;
      this.pokemon = pokemon;
      this.type = type;
   }

   @Override
   public String toString() {
      return "Pokemon{" +
              "ID=" + id +
              "Name=" + pokemon +
              ", type='" + type + '\'' +
              '}';
   }


}
