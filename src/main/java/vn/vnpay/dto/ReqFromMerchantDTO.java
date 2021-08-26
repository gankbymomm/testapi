package vn.vnpay.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ReqFromMerchantDTO implements Serializable {

    private static final Logger logger = LogManager.getLogger(ReqFromMerchantDTO.class);

    @NotNull(message = "App id cannot null, empty")
    private String appId;

    @Size(max = 32)
    private String masterMerCode;

    @NotBlank(message = "Service code cannot null, empty")
    private String serviceCode;

    private String ccy;

    @Size(max = 13)
    private String amount;

    @NotBlank(message = "Country code cannot null, empty")
    private String countryCode;

    @NotBlank(message = "Merchant code cannot null, empty")
    private String merchantCode;

    @NotBlank(message = "Merchant name cannot null, empty")
    @Size(max = 25)
    private String merchantName;
    @Size(max = 15)
    private String merchantCity;
    @Size(max = 10)
    private String postalCode;
    @Size(max = 20)
    private String storeLabel;
    @Size(max = 25)
    private String expDate;
    @Size(max = 15)
    private String terminalId;
    @Size(max = 19)
    private String purpose;
    @Size(max = 99)
    private String addInfo;

    @NotBlank(message = "Pay type cannot null, empty")
    private String payType;

    private String txnId;
    private String crc;
    private String tipAndFee;

    @NotBlank(message = "Check sum data cannot null, empty")
    private String checksum;
    private String billNumber;

}
