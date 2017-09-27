package org.apache.olingo.odata2.jpa.processor.core.access.data;

import javax.persistence.Query;

public class JPAQueryInfo {

    private Query query = null;
    private Query countQuery=null;
    private boolean isTombstoneQuery = false;

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public boolean isTombstoneQuery() {
        return isTombstoneQuery;
    }

    public void setTombstoneQuery(boolean isTombstoneQuery) {
        this.isTombstoneQuery = isTombstoneQuery;
    }

    public Query getCountQuery() {
        return countQuery;
    }

    public void setCountQuery(Query countQuery) {
        this.countQuery = countQuery;
    }
}
