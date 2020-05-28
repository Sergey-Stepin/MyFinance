/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.common.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author Stepin
 */

public class SqlQuery {
    
    private final String body;
    private final List<String> whereClauses;
    private final String groupByClause;
    private final String orderByClause;
    private final String ending;
    private final String join;

    public static class SqlQueryBuilder {

        private String body;
        private String join="";
        private List<String> whereClauses;
        private String groupByClause;
        private String orderClause;
        private String ending;

        public SqlQueryBuilder() {
            body = null;
            whereClauses = new ArrayList<>();
        }

        public SqlQueryBuilder(String body) {
            this();
            this.body = body;
        }

        public SqlQueryBuilder(Resource body) throws IOException {
            this();
            this.body = readSqlFromResource(body);
        }

        public SqlQuery build() {
            return new SqlQuery(this);
        }

        public SqlQueryBuilder body(String body) {
            this.body = body;
            return this;
        }

        public SqlQueryBuilder body(Resource body) throws IOException {
            this.body = readSqlFromResource(body);
            return this;
        }

        public SqlQueryBuilder addWhereClause(String clause) {
            if(clause != null && !clause.isEmpty()){
                this.whereClauses.add(clause);
            }
            return this;
        }

        public SqlQueryBuilder addJoin(String join) {
            if(StringUtils.hasText(join)){
                this.join=join;
            }
            return this;
        }


        public SqlQueryBuilder addWhereClause(Resource clause) throws IOException {
            this.whereClauses.add(readSqlFromResource(clause));
            return this;
        }

        public SqlQueryBuilder groupByClause(String clause) {
            this.groupByClause = clause;
            return this;
        }

        public SqlQueryBuilder groupByClause(Resource clause) throws IOException {
            this.groupByClause = readSqlFromResource(clause);
            return this;
        }

        public SqlQueryBuilder orderByClause(String clause) {
            this.orderClause = clause;
            return this;
        }

        public SqlQueryBuilder orderByClause(Resource clause) throws IOException {
            this.orderClause = readSqlFromResource(clause);
            return this;
        }

        public SqlQueryBuilder ending(String ending) {
            this.ending = ending;
            return this;
        }

        public SqlQueryBuilder ending(Resource ending) throws IOException {
            this.ending = readSqlFromResource(ending);
            return this;
        }

    }

    public static String readSqlFromResource(Resource resource) throws IOException{
        try(
                InputStream is = resource.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")))){
            
            String query  = reader.lines()
                    .reduce((s1, s2) -> s1.concat("\n").concat(s2))
                    .get();

            return query;
        }catch (IOException ex){
            throw new IOException("Cannot read resource: resource", ex);
        }
    }

    public static String readSqlFromResource(final String resourceName) throws IOException{
        Resource resource = new ClassPathResource(resourceName);
        return readSqlFromResource(resource);
    }

    private SqlQuery(SqlQueryBuilder builder) {
        this.body = builder.body;
        this.whereClauses = builder.whereClauses;
        this.groupByClause = builder.groupByClause;
        this.orderByClause = builder.orderClause;
        this.ending = builder.ending;
        this.join = builder.join;
    }

    public static SqlQueryBuilder create() {
        return new SqlQueryBuilder();
    }

    public static SqlQueryBuilder create(String body) {
        return new SqlQueryBuilder(body);
    }

    @Override
    public String toString() {
        return query();
    }

    public String query() {

        Objects.requireNonNull(this.body," Body of a query cannot be null. ");


        StringBuilder query = new StringBuilder(body.replace("${JOIN_HERE}",join));

        if(this.whereClauses != null && ! this.whereClauses.isEmpty()){
            query.append("\n");
            query.append(this.whereClauses.stream()
                    .collect(Collectors.joining("\n", "where ", "")));
        }

        if (this.groupByClause != null) {
            query.append("\n");
            query.append(this.groupByClause);
        }

        if (this.orderByClause != null) {
            query.append("\n");
            query.append(this.orderByClause);
        }

        if (this.ending != null) {
            query.append("\n");
            query.append(this.ending);
        }

        return query.toString();
    }

    public String getBody() {
        return body;
    }

    public List<String> getWhereClauses() {
        return whereClauses;
    }

    public String getGroupByClause() {
        return groupByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public String getEnding() {
        return ending;
    }
}
