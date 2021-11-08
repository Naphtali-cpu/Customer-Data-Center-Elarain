import com.elarian.*;
import com.elarian.model.*;

import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    public static void main(String[] args) {
        String orgId = "el_org_eu_PieRTp";
        String appId = "el_app_Z8lUFj";
        String apiKey = "El_k_test_d23378617993f707a2c7300a146f42625543b552a2f027893c6ea826673735b2";
        Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

        Elarian client = new Elarian(apiKey, orgId, appId);
        client.connect(new ConnectionListener() {
            @Override
            public void onError(Throwable throwable) {
                System.err.println("Connnection error: " + throwable.getMessage());
            }

            @Override
            public void onPending() {

            }

            @Override
            public void onConnecting() {

            }

            @Override
            public void onClosed() {

            }

            @Override
            public void onConnected() {
                System.out.println("App is running!");

                Customer alice = new Customer(client, new CustomerNumber("+254727991993", CustomerNumber.Provider.CELLULAR));

                MessagingChannel telegramChannel = new MessagingChannel(
                        "your-telegram-bot",
                        MessagingChannel.Channel.TELEGRAM
                );

                alice.sendMessage(
                                telegramChannel,
                                new Message(new MessageBody("Hello World!"))
                        )
                        .subscribe(
                                res -> log.info(String.valueOf(res)),
                                err -> err.printStackTrace()
                        );
            }
        });

        }
    }
