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
package org.apache.olingo.odata2.api.edm;

/**
 * @org.apache.olingo.odata2.DoNotImplement
 * EdmTyped indicates if an EDM element is of a special type and holds the multiplicity of that type.
 * 
 */
public interface EdmTyped extends EdmNamed {

  /**
   * See {@link EdmType} for more information about possible types.
   * 
   * @return {@link EdmType}
   * @throws EdmException
   */
  EdmType getType() throws EdmException;

  /**
   * See {@link EdmMultiplicity} for more information about possible multiplicities.
   * 
   * @return {@link EdmMultiplicity}
   * @throws EdmException
   */
  EdmMultiplicity getMultiplicity() throws EdmException;

  boolean isPaginated();
}
