package vn.vnpay;

import org.eclipse.jetty.server.Server;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.skyscreamer.jsonassert.JSONAssert;
import vn.vnpay.constant.SystemConstant;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class JettyServerTest {

    private static Server server;
    private static WebTarget target;

    @BeforeAll
    public static void startServer(){
        server = JettyServer.startServer();
        Client client = ClientBuilder.newClient();
        target = client.target(SystemConstant.BASE_URI);
    }

    @AfterAll
    static void stopServer(){
        try {
            server.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testResponse() throws JSONException {
        Object responseFromClient = target.path("merchant/response")
                        .request(MediaType.APPLICATION_JSON_TYPE)
                        .get(Object.class);

        JSONObject actual = new JSONObject((String) responseFromClient);
        String expected = "{\"code\":\"00\"," +
                            "\"message\":\"Success\"," +
                            "\"data\":\"00020101021226300010A000000775011203609300147552120360930014755303704540855000.005802CN5914BEST " +
                                        "TRANSPORT6007BEIJING610510000624902041234051021071707550708A60086670805COSO20902ME63048TTM\"}";
        JSONAssert.assertEquals(expected, actual, false);

    }

}
/*String expected = "{\"appId\":\"VNPAY\",\"serviceCode\":\"03\",\"masterMerCode\":\"A000000775\"," +
                            "\"merchantCode\":\"036093001475\",\"ccy\":\"704\",\"amount\":\"55000.00\"," +
                            "\"countryCode\":\"CN\",\"merchantName\":\"BEST TRANSPORT\",\"merchantCity\":\"BEIJING\"," +
                            "\"postalCode\":\"10000\",\"billNumber\":\"987654\",\"storeLabel\":\"1234\",\"terminalId\":\"A6008667\"," +
                            "\"purpose\":\"COSO2\",\"addInfo\":\"ME\",\"payType\":\"01\",\"txnId\":\"123113277\",\"expDate\":\"2107170755\"," +
                            "\"checksum\":\"3C927D42F84B5420502AAF29B97402C5\",\"crc\":\"8TTM\"}";*/

