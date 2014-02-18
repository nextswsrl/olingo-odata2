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
package org.apache.olingo.odata2.jpa.processor.core.access.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.olingo.odata2.jpa.processor.api.access.JPAPaging;

public class JPAPage implements JPAPaging {

  private int pageSize;
  private int startPage;
  private int nextPage;
  private List<Object> pagedEntries;

  protected JPAPage(final int startPage, final int nextPage, final List<Object> pagedEntities, final int pageSize) {
    this.pageSize = pageSize;
    this.startPage = startPage;
    this.nextPage = nextPage;
    pagedEntries = pagedEntities;
  }

  @Override
  public int getPageSize() {
    return pageSize;
  }

  @Override
  public List<Object> getPagedEntities() {
    return pagedEntries;
  }

  @Override
  public int getNextPage() {
    return nextPage;
  }

  @Override
  public int getStartPage() {
    return startPage;
  }

  public static class JPAPageBuilder {

    private int pageSize;
    private int startPage;
    private int nextPage;
    private int top = -1;
    private int skip;
    private int skipToken;
    private Query query;

    public JPAPageBuilder() {}

    public JPAPageBuilder pageSize(final int pageSize) {
      this.pageSize = pageSize;
      return this;
    }

    public JPAPageBuilder query(final Query query) {
      this.query = query;
      return this;
    }

    @SuppressWarnings("unchecked")
    public JPAPage build() {

      List<Object> entities = new ArrayList<Object>();
      int size = 0;
      if (pageSize <= 0) {
        if (skip > 0) {
          query.setFirstResult(skip);
        }
        if (top > 0) {
          query.setMaxResults(top);
        }
        entities = query.getResultList();
      } else {
        if (skip >= pageSize) { // No Records to fetch
          startPage = skipToken;
          nextPage = 0;
        } else {
          // Max Results
          size = top + skip;
          if (size > pageSize) {
            if (skip == 0) {
              query.setMaxResults(pageSize);
            } else {
              query.setMaxResults(pageSize - skip);
            }
          } else {
            if (top > 0) {
              query.setMaxResults(top);
            } else {
              query.setMaxResults(pageSize);
            }
          }

          startPage = skipToken;
          if (skip > 0) {
            query.setFirstResult(startPage + skip);
          } else {
            query.setFirstResult(startPage);
          }

          entities = query.getResultList();
          if (entities.size() == 0) {
            nextPage = 0;
          } else {
            nextPage = startPage + pageSize;
          }
        }
      }
      return new JPAPage(startPage, nextPage, entities, pageSize);
    }

    public JPAPageBuilder skip(final int skip) {
      this.skip = skip;
      if (skip < 0) {
        this.skip = 0;
      } else {
        this.skip = skip;
      }
      return this;
    }

    public JPAPageBuilder skipToken(final String skipToken) throws NumberFormatException {
      if (skipToken == null) {
        this.skipToken = 0;
      } else {
        this.skipToken = new Integer(skipToken).intValue();
        if (this.skipToken < 0) {
          this.skipToken = 0;
        }
      }

      return this;
    }

    public JPAPageBuilder top(final int top) {
      if (top < 0) {
        this.top = 0;
      } else {
        this.top = top;
      }
      return this;
    }
  }
}