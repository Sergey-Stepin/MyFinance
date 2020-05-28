/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.exchange.gate.config.moex;

/**
 *
 * @author stepin
 */
public class AbstractMoexReceiverConfig {
    
    private int order;
    private String queryUrl;
    private String queryTicketsParamName;
    private String querySuffixParams;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
    
    public String getQueryUrl() {
        return queryUrl;
    }

    public void setQueryUrl(String queryUrl) {
        this.queryUrl = queryUrl;
    }

    public String getQueryTicketsParamName() {
        return queryTicketsParamName;
    }

    public void setQueryTicketsParamName(String queryTicketsParamName) {
        this.queryTicketsParamName = queryTicketsParamName;
    }

    public String getQuerySuffixParams() {
        return querySuffixParams;
    }

    public void setQuerySuffixParams(String querySuffixParams) {
        this.querySuffixParams = querySuffixParams;
    }
}
