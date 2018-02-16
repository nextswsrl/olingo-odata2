/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 ******************************************************************************/
package org.apache.olingo.odata2.api.annotation.edm;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * Come deve essere elaborato il tipo di payload,
 * todo c'è logica applicativa che potrebbe essere spostata su altri pacchetti dichiarando questa enum abstract
 */
public enum EdmFunctionImportPayloadType {
  RAW(InputStream.class) {
    @Override
    public Object convert(InputStream inputStream, Class returnType) throws IOException {
      return inputStream;
    }
  },
  JSON(Object.class) {
    @Override
    public Object convert(InputStream inputStream, Class returnType) throws IOException {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.readValue(inputStream,returnType);
    }
  };


  protected Class<?> typeManaged;

  EdmFunctionImportPayloadType(Class<?> typeManaged) {
    this.typeManaged = typeManaged;
  }

  public Class<?> getTypeManaged() {
    return typeManaged;
  }

  public abstract Object convert(InputStream inputStream, Class returnType) throws IOException;
}
