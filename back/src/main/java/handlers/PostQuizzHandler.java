package handlers;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.AppendValuesResponse;
import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.RoutingContext;
import utils.SheetsServiceUtil;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PostQuizzHandler implements Handler<RoutingContext> {

    private static Sheets sheetsService;
    private static String SPREADSHEET_ID = "1QmLrHpiUpFDrSYaeoeLMAQ250-tyTUoHJh3AUKx-JlQ";


    @Override
    public void handle(RoutingContext routingContext) {
        String firstname = routingContext.getBodyAsJson().getString("firstname");
        String lastname = routingContext.getBodyAsJson().getString("lastname");
        JsonArray answers = routingContext.getBodyAsJson().getJsonArray("answers");

        try {
            sheetsService = SheetsServiceUtil.getSheetsService();
            ValueRange appendBody = new ValueRange().setValues(getValuesToInsert(firstname, lastname, answers));
            AppendValuesResponse appendResult = sheetsService.spreadsheets().values()
                .append(SPREADSHEET_ID, "A1", appendBody)
                .setValueInputOption("USER_ENTERED")
                .setInsertDataOption("INSERT_ROWS")
                .setIncludeValuesInResponse(true)
                .execute();
        } catch(GeneralSecurityException gse) {
            routingContext.response().end("Security exception : " + gse.getMessage());
        } catch(IOException ioe) {
            routingContext.response().end("I/O exception : " + ioe.getMessage());
        }

        routingContext.response().end(firstname);
    }

    private List<List<Object>> getValuesToInsert(String firstname, String lastname, JsonArray answers) {
        List<Object> values = new ArrayList<>();
        values.add(lastname);
        values.add(firstname);
        values.addAll(answers.getList());
        return Arrays.asList(values);
    }
}