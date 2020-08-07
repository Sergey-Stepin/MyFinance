/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.exchange.gate.receiver.moex;

import steps.dev.exchange.gate.config.moex.AbstractMoexReceiverConfig;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.StringUtils;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.RestTemplate;
import steps.dev.exchange.gate.receiver.moex.formatter.MoexTimeFormatter;
import steps.dev.exchange.gate.receiver.moex.formatter.MoexZonedDateTimeFormatter;
import steps.dev.exchange.gate.receiver.moex.interchange.ExchangeResponse;
import steps.dev.myfinance7.common.model.exchange.ExchangeReceiverName;
import steps.dev.myfinance7.common.model.exchange.IExchangeReceiver;
import steps.dev.myfinance7.common.model.quote.SecurityQuote;

/**
 *
 * @author stepin
 */
public abstract class AbstractMoexReceiver implements IExchangeReceiver{

    private final AbstractMoexReceiverConfig parameters;
    private final RestTemplate restTemplate;
    private final ExchangeReceiverName receiverName;
    private final KafkaTemplate<String, SecurityQuote> kafkaTemplate;
    private final String kafkaTopic;
    
    public static final MoexZonedDateTimeFormatter MOEX_ZONEDDATETIME_FORMATTER = new MoexZonedDateTimeFormatter();
    public static final MoexTimeFormatter MOEX_TIME_FORMATTER = new MoexTimeFormatter();
    
    protected abstract void update(ExchangeResponse response);
    protected abstract List<String> getTickets();

    public AbstractMoexReceiver(
            ExchangeReceiverName receiverName,
            RestTemplate restTemplate,
            KafkaTemplate<String, SecurityQuote> kafkaTemplate,
            String kafkaTopic,
            AbstractMoexReceiverConfig parameters) {
        
        super();
        
        this.restTemplate = restTemplate;
        this.parameters = parameters;
        this.receiverName = receiverName;
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTopic = kafkaTopic;
    }

    public ExchangeReceiverName getReceiverName() {
        return receiverName;
    }

    public KafkaTemplate<String, SecurityQuote> getKafkaTemplate() {
        return kafkaTemplate;
    }
    
    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    protected void sendSecurityQuote(SecurityQuote quote){
        
        System.out.println("### SENDING TO TOPIC: " + kafkaTopic + " ; QUOTE: " + quote);
        
        if(quote == null){
            System.out.println(" ### quote is null. Not sending ");
        }
        
        ListenableFuture<SendResult<String, SecurityQuote>> future = kafkaTemplate.send(kafkaTopic, quote);

        try {
            System.out.println(future.get().getProducerRecord().timestamp() + " : " + future.get().getProducerRecord().value());
        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }
        
    }
    
    @Override
    public void updateByTickets(){
        
        
        System.out.println("=================================");
        System.out.println("### receiverName:" + receiverName);
        
        String ticketsAsString = this.getTickets()
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
