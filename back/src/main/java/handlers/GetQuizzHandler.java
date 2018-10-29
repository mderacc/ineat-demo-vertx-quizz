package handlers;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import utils.SheetsServiceUtil;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

public class GetQuizzHandler implements Handler<RoutingContext> {

    private static Sheets sheetsService;
    private static String SPREADSHEET_ID = "1QmLrHpiUpFDrSYaeoeLMAQ250-tyTUoHJh3AUKx-JlQ";

    @Override
    public void handle(RoutingContext routingContext) {
        String firstname = routingContext.request().params().get("firstname");
        String lastname = routingContext.request().params().get("lastname");
        try {
            sheetsService = SheetsServiceUtil.getSheetsService();
            List<String> ranges = Arrays.asList("C3","C6");
            BatchGetValuesResponse readResult = sheetsService.spreadsheets().values()
                    .batchGet(SPREADSHEET_ID)
                    .setRanges(ranges)
                    .execute();
        } catch(GeneralSecurityException gse) {
            routingContext.response().end("Security exception : " + gse.getMessage());
        } catch(IOException ioe) {
            routingContext.response().end("I/O exception : " + ioe.getMessage());
        }

        routingContext.response().end(firstname);
    }
}