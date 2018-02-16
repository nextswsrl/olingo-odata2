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
package org.apache.olingo.odata2.core.uri;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.olingo.odata2.api.commons.InlineCount;
import org.apache.olingo.odata2.api.edm.EdmEntityContainer;
import org.apache.olingo.odata2.api.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.edm.EdmFunctionImport;
import org.apache.olingo.odata2.api.edm.EdmLiteral;
import org.apache.olingo.odata2.api.edm.EdmProperty;
import org.apache.olingo.odata2.api.edm.EdmType;
import org.apache.olingo.odata2.api.uri.KeyPredicate;
import org.apache.olingo.odata2.api.uri.NavigationPropertySegment;
import org.apache.olingo.odata2.api.uri.NavigationSegment;
import org.apache.olingo.odata2.api.uri.SelectItem;
import org.apache.olingo.odata2.api.uri.UriInfo;
import org.apache.olingo.odata2.api.uri.expression.FilterExpression;
import org.apache.olingo.odata2.api.uri.expression.OrderByExpression;

/**
 *  
 */
public class UriInfoImpl implements UriInfo {

  private UriType uriType;

  private EdmEntityContainer entityContainer;
  private EdmEntitySet startEntitySet;
  private EdmEntitySet targetEntitySet;
  private EdmFunctionImport functionImport;
  private EdmType targetType;
  private List<KeyPredicate> keyPredicates = Collections.emptyList();
  private List<NavigationSegment> navigationSegments = Collections.emptyList();
  private List<EdmProperty> propertyPath = Collections.emptyList();
  private boolean count;
  private boolean value;
  private boolean links;

  private String format;
  private FilterExpression filter;
  private InlineCount inlineCount;
  private OrderByExpression orderBy;
  private String skipToken;
  private Integer skip;
  private Integer top;
  private List<ArrayList<NavigationPropertySegment>> expand = Collections.emptyList();
  private List<SelectItem> select = Collections.emptyList();
  private Map<String, EdmLiteral> functionImportParameters = Collections.emptyMap();
  private Map<String, String> customQueryOptions = Collections.emptyMap();

  //il body (payload) della request
  private InputStream functionImportPayload;

  public UriType getUriType() {
    return uriType;
  }

  public void setUriType(final UriType uriType) {
    this.uriType = uriType;
  }

  public void setEntityContainer(final EdmEntityContainer entityContainer) {
    this.entityContainer = entityContainer;
  }

  @Override
  public EdmEntityContainer getEntityContainer() {
    return entityContainer;
  }

  public void setStartEntitySet(final EdmEntitySet edmEntitySet) {
    startEntitySet = edmEntitySet;
  }

  @Override
  public EdmEntitySet getStartEntitySet() {
    return startEntitySet;
  }

  public void setTargetEntitySet(final EdmEntitySet targetEntitySet) {
    this.targetEntitySet = targetEntitySet;
  }

  @Override
  public EdmEntitySet getTargetEntitySet() {
    return targetEntitySet;
  }

  public void setFunctionImport(final EdmFunctionImport functionImport) {
    this.functionImport = functionImport;
  }

  @Override
  public EdmFunctionImport getFunctionImport() {
    return functionImport;
  }

  public void setTargetType(final EdmType targetType) {
    this.targetType = targetType;
  }

  @Override
  public EdmType getTargetType() {
    return targetType;
  }

  public void setKeyPredicates(final List<KeyPredicate> keyPredicates) {
    this.keyPredicates = keyPredicates;
  }

  @Override
  public List<KeyPredicate> getKeyPredicates() {
    return keyPredicates;
  }

  @Override
  public List<KeyPredicate> getTargetKeyPredicates() {
    return navigationSegments.isEmpty() ?
        keyPredicates :
        navigationSegments.get(navigationSegments.size() - 1).getKeyPredicates();
  }

  public void addNavigationSegment(final NavigationSegment navigationSegment) {
    if (navigationSegments.equals(Collections.EMPTY_LIST)) {
      navigationSegments = new ArrayList<NavigationSegment>();
    }

    navigationSegments.add(navigationSegment);
  }

  @Override
  public List<NavigationSegment> getNavigationSegments() {
    return navigationSegments;
  }

  public void addProperty(final EdmProperty property) {
    if (propertyPath.equals(Collections.EMPTY_LIST)) {
      propertyPath = new ArrayList<EdmProperty>();
    }

    propertyPath.add(property);
  }

  @Override
  public List<EdmProperty> getPropertyPath() {
    return propertyPath;
  }

  public void setCount(final boolean count) {
    this.count = count;
  }

  @Override
  public boolean isCount() {
    return count;
  }

  public void setValue(final boolean value) {
    this.value = value;
  }

  @Override
  public boolean isValue() {
    return value;
  }

  public void setLinks(final boolean links) {
    this.links = links;
  }

  @Override
  public boolean isLinks() {
    return links;
  }

  public void setFormat(final String contentType) {
    format = contentType;
  }

  @Override
  public String getFormat() {
    return format;
  }

  public void setFilter(final FilterExpression filter) {
    this.filter = filter;
  }

  @Override
  public FilterExpression getFilter() {
    return filter;
  }

  public void setInlineCount(final InlineCount inlineCount) {
    this.inlineCount = inlineCount;
  }

  @Override
  public InlineCount getInlineCount() {
    return inlineCount;
  }

  public void setOrderBy(final OrderByExpression orderBy) {
    this.orderBy = orderBy;
  }

  @Override
  public OrderByExpression getOrderBy() {
    return orderBy;
  }

  public void setSkipToken(final String skipToken) {
    this.skipToken = skipToken;
  }

  @Override
  public String getSkipToken() {
    return skipToken;
  }

  public void setSkip(final Integer skip) {
    this.skip = skip;
  }

  @Override
  public Integer getSkip() {
    return skip;
  }

  public void setTop(final Integer top) {
    this.top = top;
  }

  @Override
  public Integer getTop() {
    return top;
  }

  public void setExpand(final List<ArrayList<NavigationPropertySegment>> expand) {
    this.expand = expand;
  }

  @Override
  public List<ArrayList<NavigationPropertySegment>> getExpand() {
    return expand;
  }

  public void setSelect(final List<SelectItem> select) {
    this.select = select;
  }

  @Override
  public List<SelectItem> getSelect() {
    return select;
  }

  public void addFunctionImportParameter(final String name, final EdmLiteral value) {
    if (functionImportParameters.equals(Collections.EMPTY_MAP)) {
      functionImportParameters = new HashMap<String, EdmLiteral>();
    }

    functionImportParameters.put(name, value);
  }

  @Override
  public Map<String, EdmLiteral> getFunctionImportParameters() {
    return functionImportParameters;
  }

  @Override
  public InputStream getFunctionImportPayload() {
    return functionImportPayload;
  }

  public void setFunctionImportPayload(InputStream functionImportContent) {
    this.functionImportPayload = functionImportContent;
  }

  @Override
  public Map<String, String> getCustomQueryOptions() {
    return customQueryOptions;
  }

  public void setCustomQueryOptions(final Map<String, String> customQueryOptions) {
    this.customQueryOptions = customQueryOptions;
  }

  @Override
  public String toString() {
    return "UriParserResult: uriType=" + uriType + ", "
        + "entityContainer=" + entityContainer + ", "
        + "entitySet=" + startEntitySet + ", "
        + "targetEntitySet=" + targetEntitySet + ", "
        + "functionImport=" + functionImport + ", "
        + "targetType=" + targetType + ", "
        + "keyPredicates=" + keyPredicates + ", "
        + "navigationSegments=" + navigationSegments + ", "
        + "propertyPath=" + propertyPath + ", "
        + "isCount=" + count + ", "
        + "isValue=" + value + ", "
        + "isLinks=" + links + ", "
        + "contentType=" + format + ", "
        + "filter=" + filter + ", "
        + "inlineCount=" + inlineCount + ", "
        + "orderBy=" + orderBy + ", "
        + "skipToken=" + skipToken + ", "
        + "skip=" + skip + ", "
        + "top=" + top + ", "
        + "expand=" + expand + ", "
        + "select=" + select + ", "
        + "functionImportParameters=" + functionImportParameters + ", "
        + "customQueryOptions=" + customQueryOptions;
  }
}
