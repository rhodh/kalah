package com.rhod.kalah.models;

import java.io.IOException;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class BoardModelSerializer extends StdSerializer<BoardModel> {
    
   public BoardModelSerializer() {
       this(null);
   }
  
   public BoardModelSerializer(Class<BoardModel> t) {
       super(t);
   }

   @Override
   public void serialize(
		   BoardModel value, JsonGenerator jgen, SerializerProvider provider) 
     throws IOException, JsonProcessingException {
 
       jgen.writeStartObject();
       
       List<Integer> pits = value.getPits();
       for(int i = 0; i < pits.size(); ++i) {
           jgen.writeNumberField(Integer.toString(i+1), pits.get(i));
       }
    
       jgen.writeStringField("Player", value.getCurrentPlayer().toString());
       jgen.writeEndObject();
   }
}
