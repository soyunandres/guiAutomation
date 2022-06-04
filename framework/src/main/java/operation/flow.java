package operation;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import utils.TryAndCatch;

public class flow {
    private final Screen screen;
    public Pattern pattern;

    public flow(Screen screen) {
        this.screen = screen;
    }

    public void typeKeys(String keys) {

        TryAndCatch.typeKeys(this.screen,keys);

     }
    public void wait(int time) {

        TryAndCatch.wait(time);

    }

    public void clickByText(String text, int parseInt, int parseInt1) throws FindFailed {
        TryAndCatch.clickByText(this.screen, text, parseInt, parseInt1);
    }
    public void findWordAndClick(String stringCellValue, String stringCellValue1) throws FindFailed {
        TryAndCatch.findWordAndClick(stringCellValue,stringCellValue1);
    }





    public void createRegion(String stringCellValue, int parseInt, int parseInt1, int parseInt2, int parseInt3) {
        TryAndCatch.createRegion(stringCellValue, parseInt, parseInt1, parseInt2, parseInt3);
    }

    public void createSubRegion(String stringCellValue, String stringCellValue1, int parseInt, int parseInt1, int parseInt2, int parseInt3) {
        TryAndCatch.createSubRegion(stringCellValue,stringCellValue1, parseInt, parseInt1, parseInt2, parseInt3);
    }



    public void createRegionAppByName(String stringCellValue, String stringCellValue1) {
        TryAndCatch.createRegionAppByName(stringCellValue,stringCellValue1);
    }

    public void createRegionApp(String stringCellValue) {
        TryAndCatch.createRegionApp(stringCellValue);
    }

    public void createSubRegionApp(String stringCellValue, String stringCellValue1, int parseInt, int parseInt1, int parseInt2, int parseInt3) {
        TryAndCatch.createSubRegionApp(stringCellValue,stringCellValue1, parseInt, parseInt1, parseInt2, parseInt3);
    }

    public void clickByTextInRegion(String stringCellValue, String stringCellValue1, int parseInt, int parseInt1) {
        TryAndCatch.clickByTextInRegion(stringCellValue,stringCellValue1, parseInt, parseInt1);
    }



    public void ifTypesKeys(String regionName, String stringCellValue, String[] stringCellValuesActions) {

        TryAndCatch.ifTypeKeys(this.screen, regionName, stringCellValue, stringCellValuesActions);


        }

    public void regionHasText(String stringCellValue, String stringCellValue1, int parseInt, int parseInt1) {
        TryAndCatch.regionHasText(stringCellValue,stringCellValue1, parseInt, parseInt1);
    }

    public void findWordAndCreateRegion(String stringCellValue, String stringCellValue1) throws FindFailed {
        TryAndCatch.findWordAndCreateRegion(stringCellValue,stringCellValue1);
    }
}

