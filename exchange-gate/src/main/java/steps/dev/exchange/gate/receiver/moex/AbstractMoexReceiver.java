/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.exchange.gate.receiver.moex;

import com.netflix.discovery.util.StringUtil;
import steps.dev.exchange.gate.config.moex.AbstractMoexReceiverConfig;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import steps.dev.exchange.gate.client.InstrumentClient;
import steps.dev.exchange.gate.receiver.moex.formatter.MoexTimeFormatter;
import steps.dev.exchange.gate.receiver.moex.formatter.MoexZonedDateTimeFormatter;
import steps.dev.exchange.gate.receiver.moex.interchange.ExchangeResponse;
import steps.dev.exchange.gate.receiver.moex.interchange.Marketdata;
import steps.dev.exchange.gate.receiver.moex.interchange.MarketdataDescription;
import steps.dev.myfinance7.common.model.exchange.IExchangeReceiver;

/**
 *
 * @author stepin
 */
public abstract class AbstractMoexReceiver implements IExchangeReceiver{

    private final AbstractMoexReceiverConfig parameters;
    private final RestTemplate restTemplate;
    
    public static final MoexZonedDateTimeFormatter MOEX_ZONEDDATETIME_FORMATTER = new MoexZonedDateTimeFormatter();
    public static final MoexTimeFormatter MOEX_TIME_FORMATTER = new MoexTimeFormatter();
    
    protected abstract void update(ExchangeResponse response);

    public AbstractMoexReceiver(
            RestTemplate restTemplate,
            AbstractMoexReceiverConfig parameters) {
        
        this.restTemplate = restTemplate;
        this.parameters = parameters;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
    
    @Override
    public void updateByTickets(List<String> tickets){
        
        String ticketsAsString = tickets
                .stream()
                .collect(Collectors.joining(","));
        
        if( !StringUtils.hasText(ticketsAsString)){
            System.out.println("### No tickets for the receiver");
            return;
        }
        
        System.out.println("### ticketsAsString=" + ticketsAsString);
        
        try {
            ExchangeResponse response = receiveQuotes(ticketsAsString);
            System.out.println("### response=" + response);
            update(response);
            
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
    }
    
    private ExchangeResponse receiveQuotes(String ticketsAsString) throws URISyntaxException{
        
//        Map<String, String> parameters = this.getDescriptor().getParameters();
//        
//        String url = parameters.get("query_url");
//        String ticketsParamName = parameters.get("query_tickets_param_name");
//        String suffixParams = parameters.get("query_suffix_params");
        
        String request = 
                parameters.getQueryUrl() + 
                "?" + 
                parameters.getQueryTicketsParamName() + "=" + ticketsAsString + 
                parameters.getQuerySuffixParams();
        
        System.out.println("### request=" + request);
        
//        RequestEntity<Void> requestEntity = RequestEntity
//                .get(new URI(""))
//                .build();
        
        ResponseEntity<ExchangeResponse> responseEntity = restTemplate.getForEntity(request, ExchangeResponse.class);
        
        System.out.println("### ResponseEntity=" + responseEntity);
        
        ExchangeResponse response = responseEntity.getBody();
        
        return responseEntity.getBody();
        
    }
}
