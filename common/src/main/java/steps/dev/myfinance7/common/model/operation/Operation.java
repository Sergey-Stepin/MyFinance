/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.common.model.operation;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author stepin
 */
@Data
@Entity
@Table(name = "operation_journal")
@Inheritance(strategy = InheritanceType.JOINED)
public class Operation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long operationId;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OperationType opertaionType;

    @NotNull
    @Column(nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Timestamp operationTimestamp = Timestamp.from(Instant.now());

    @Temporal(TemporalType.DATE)
    private Date paymentDate;

    private String operationComment;

    @Embedded
    @AssociationOverrides({
        @AssociationOverride(name = "instrument", joinColumns = @JoinColumn(name = "long_instrument_id"))
        ,
        @AssociationOverride(name = "portfolio", joinColumns = @JoinColumn(name = "long_portfolio_id"))})
    @AttributeOverrides({
        @AttributeOverride(name = "currency", column = @Column(name = "long_currency"))
        ,
        @AttributeOverride(name = "currencyRate", column = @Column(name = "long_currency_rate"))
        ,
        @AttributeOverride(name = "currencyAmmount", column = @Column(name = "long_currency_amount"))
        ,
        @AttributeOverride(name = "amount", column = @Column(name = "long_amount"))})
    private OperationPart longPart;

    @Embedded
    @AssociationOverrides({
        @AssociationOverride(name = "instrument", joinColumns = @JoinColumn(name = "short_instrument_id"))
        ,
        @AssociationOverride(name = "portfolio", joinColumns = @JoinColumn(name = "short_portfolio_id"))})
    @AttributeOverrides({
        @AttributeOverride(name = "currency", column = @Column(name = "short_currency"))
        ,
        @AttributeOverride(name = "currencyRate", column = @Column(name = "short_currency_rate"))
        ,
        @AttributeOverride(name = "currencyAmmount", column = @Column(name = "short_currency_amount"))
        ,
        @AttributeOverride(name = "amount", column = @Column(name = "short_amount"))})
    private OperationPart shortPart;
}
