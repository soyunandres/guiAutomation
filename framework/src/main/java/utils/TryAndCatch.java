package utils;

import org.apache.commons.lang3.StringUtils;

import org.apache.commons.text.similarity.LevenshteinDetailedDistance;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.sikuli.script.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class TryAndCatch {
    //Create a treeMap for save the regions created along the execution
    private static Map<String, Region> regions = new TreeMap<String, Region>();
    //Acces  to the treeMap regions from the string key passed as parameter


    public static boolean  getRegion(String key) {
        try {
            regions.get(key);
            return true;
        } catch (Exception e) {
            return false;
        }

    }



    public static boolean createRegion(String regionName, int x, int y, int width, int height) {

        try {
            regions.put(regionName, new Region(x,y,width,height));
            System.out.println("Region created" + regions.get(regionName).highlight(2,"blue").getCenter());
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            return false;
        }
    }
    //Create a subregion of a region in the treeMap regions with the name of region passed as parameter
    public static boolean createSubRegion(String regionName, String subRegionName, int subx, int suby, int width, int height) {
        try {
            int x = regions.get(regionName).getX();
            int y = regions.get(regionName).getY();
            regions.put(subRegionName, new Region(x + subx , y + suby, width, height));
            System.out.println("SubRegion created" + regions.get(subRegionName).highlight(2,"purple").getCenter());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean createRegionAppByName(String appName, String regionName) {
        try {
            //Focus the app by name
            List app = App.getApps();
            System.out.println("App list: " + app);
            for (Object app1 : app) {
                if (app1.toString().contains(appName)) {

                    App.focus(app1.toString()).setWindow(app1.toString());
                    System.out.println("App is focused" + app1.toString());
                    //Create a region with the name of region passed as parameter
                    regions.put(regionName, App.focus(app1.toString()).focusedWindow());
                    System.out.println("Region created " + regions.get(regionName).highlight(2,"blue").getCenter());


                } else {
                    System.out.println("App not found");
                }

            } return true;
        } catch (Exception e) {
            return false;
        }
    }
    //Create region app on focused window
    public static boolean createRegionApp(String regionName) {
        try {
            regions.put(regionName, App.focusedWindow());
            System.out.println("Region created " + regions.get(regionName).highlight(2,"blue").getCenter());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Create subregion app in region focused window
    public static boolean createSubRegionApp(String regionName, String subRegionName, int subx, int suby, int subwidth, int subheight) {
        try {
            int x = regions.get(regionName).getX();
            int y = regions.get(regionName).getY();
            int width = regions.get(regionName).getW();
            int height = regions.get(regionName).getH();
            regions.put(subRegionName, new Region(x + subx , y + suby,  subwidth, subheight));
            System.out.println("SubRegion created" + regions.get(subRegionName).highlight(2,"purple").getCenter());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Click by text on a region in the treeMap regions with the name of region passed as parameter and the text passed as parameter
    public static boolean clickByTextInRegion(String regionName, String text, int stepW, int stepH) {
        try {
            int x = regions.get(regionName).getX();
            int y = regions.get(regionName).getY();
            int width = regions.get(regionName).getW();
            int height = regions.get(regionName).getH();
            int i = x;
            int j = y;
            int l = 0;
            int k = 0;

            outerloop:
            if (regions.get(regionName).text().contains(text)) {
                System.out.println("Text found");
                regions.get(regionName).getCenter().click();
                break outerloop;
            } else {
                while (i < (x + width)) {
                    i++;
                    while (j < (y + height)) {
                        j++;
                        k = 0;
                        while (k < width) {
                            k = k + stepW;
                            l = 0;
                            while (l < height) {
                                l = l + stepH;
                                regions.put(text, new Region(i, j, k, l));
                                //regions.get(text).highlight(0.1, "red");
                                String textfound = regions.get(text).text();
                                if (textfound.contains(text)) {
                                    regions.get(text).highlight(1, "green").getImage().save("src/main/resources/images/" + text + ".png");
                                    regions.get(text).click();
                                    break outerloop;
                                }
                            }
                        }


                    }
                }

            }
            return true;
        } catch (Exception e) {
            return false;


        }
    }
    //Check if some text are on a region in the treeMap regions with the name of region passed as parameter and the text passed as parameter
    public static boolean regionHasText(String regionName, String text, int stepW, int stepH) {
        try {
            int x = regions.get(regionName).getX();
            int y = regions.get(regionName).getY();
            int width = regions.get(regionName).getW();
            int height = regions.get(regionName).getH();
            int i = x;
            int j = y;
            int l = 0;
            int k = 0;


            outerloop:
            if (regions.get(regionName).text().equals(text)) {
                System.out.println("Text found");

                break outerloop;
            } else {
                while (i < (x + width)) {
                    i++;
                    while (j < (y + height)) {
                        j++;
                        k = 0;
                        while (k < width) {
                            k = k + stepW;
                            l = 0;
                            while (l < height) {
                                l = l + stepH;
                                regions.put(text, new Region(i, j, k, l));
                                //regions.get(text).highlight(0.1, "red");
                                String textfound = regions.get(text).text();
                                if (textfound.equals(text)) {
                                    regions.get(text).highlight(1, "green").getImage().save("src/main/resources/images/" + text + ".png");
                                    System.out.println("Texto encontrado" + text);
                                    break outerloop;
                                } else {
                                    System.out.println("Texto no encontrado" + text);
                                    regions.remove(text);
                                }
                            }
                        }


                    }
                }

            }
            return true;
        } catch (Exception e) {
            return false;


        }
    }
//If text is find in a region, then use an array of string to send actions
    public static boolean ifTypeKeys(Screen screen, String regionName, String text, String[] actions) {
        try {

            System.out.println("text " + regions.get(regionName).text());
            if (regions.get(regionName).text().equals(text)) {
                int i = 0;
                //Create an array separate by "else"
               while (!(actions[i].equals("else"))) {
                   typeKeys(screen, actions[i]);
                   i++;
               }
                return true;

               } else {
                int i = 0;
                while (!(actions[i].equals("else"))) {
                    i++;
                }
                while (i < actions.length) {
                    typeKeys(screen, actions[i]);
                    i++;
                }


               }



                System.out.println("Can´t send the typekeys to region");

            return true;
        } catch (Exception e) {
            return false;

        }
    }








    //Sendkeys
    public static boolean typeKeys(Screen screen, String keys) {
        try {
            if (keys.equals("Key.WIN")) {
                screen.type(Key.WIN);
            } else if (keys.equals("Key.ALT")) {
                screen.type(Key.ALT);

            } else if (keys.equals("Key.CTRL")) {
                screen.type(Key.CTRL);
            } else if (keys.equals("Key.SHIFT")) {
                screen.type(Key.SHIFT);

            } else if (keys.equals("Key.TAB")) {
                screen.type(Key.TAB);
            } else if (keys.equals("Key.ENTER")) {
                screen.type(Key.ENTER);

            } else if (keys.equals("Key.ESC")) {
                screen.type(Key.ESC);

            } else if (keys.equals("Key.DELETE")) {
                screen.type(Key.DELETE);
            } else if (keys.equals("Key.END")) {
                screen.type(Key.END);
            } else if (keys.equals("Key.HOME")) {
                screen.type(Key.HOME);
            } else if (keys.equals("Key.LEFT")) {
                screen.type(Key.LEFT);
            } else if (keys.equals("Key.RIGHT")) {
                screen.type(Key.RIGHT);
            } else if (keys.equals("Key.UP")) {
                screen.type(Key.UP);

            } else if (keys.equals("Key.DOWN")) {
                screen.type(Key.DOWN);

            } else if (keys.equals("Key.F1")) {
                screen.type(Key.F1);
            } else if (keys.equals("Key.F2")) {
                screen.type(Key.F2);
            } else if (keys.equals("Key.F3")) {
                screen.type(Key.F3);
            } else if (keys.equals("Key.F4")) {
                screen.type(Key.F4);
            } else if (keys.equals("Key.F5")) {
                screen.type(Key.F5);
            } else if (keys.equals("Key.F6")) {
                screen.type(Key.F6);

            } else if (keys.equals("Key.F7")) {
                screen.type(Key.F7);

            } else if (keys.equals("Key.F8")) {
                screen.type(Key.F8);

            } else if (keys.equals("Key.F9")) {
                screen.type(Key.F9);
            } else if (keys.equals("Key.F10")) {
                screen.type(Key.F10);
            } else if (keys.equals("Key.F11")) {
                screen.type(Key.F11);
            }  else if (keys.equals("Key.F12")) {
                screen.type(Key.F12);
            } else if (keys.equals("Key.BACKSPACE")) {
                screen.type(Key.BACKSPACE);

            } else if (keys.equals("Key.INSERT")) {
                screen.type(Key.INSERT);

            } else if (keys.equals("else")) {
                System.out.println("if then else");
            }
            else {
                screen.type(keys);
            }

            return true;
        } catch (Exception e) {
            //FormatLog.imprimirFallo("Error al crear region");
            return false;

        }
    }

    //Add a wait for a specific seconds
    public static void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Create a region and find a text in it then click in the text if it is found else the text is not found
    // then create a subregion in loop with x y

    public static void clickByText(Screen screen, String text, int stepH, int stepW) throws FindFailed {
        Region region = App.focusedWindow();
        region.highlight(2, "green");
        int x = region.getX();
        int y = region.getY();
        int width = region.getW();
        int height = region.getH();
        System.out.println("X: " + x + " Y: " + y);
        System.out.println("Width: " + width + " Height: " + height);
        for (int i = x; i < (x + width); i = i ++) {

            for (int j = y; j < (y +height); j = j ++) {

                for (int l = 0; l < height; l = l + stepH) {
                    for (int k = 0; k < width; k = k + stepW) {
                        Region subregion = Region.create(i, j, k, l);
                        subregion.highlight(0.1, "red");
                        String textFound = subregion.text();
                        if (textFound.contains(text)) {
                            subregion.highlight(2, "green").getImage().save("src/main/resources/images/" + text + ".png");


                        }
                    }
                }
            }
        }

        }

    public static  void findWordAndClick(String regionName, String word) throws FindFailed {

        try {
                regions.get(regionName).findWord(word).getText().equals(word);
                regions.put(word, regions.get(regionName).findWord(word));
                regions.get(word).highlight(1,"red").click();
        } catch (Exception e) {
                System.out.println("Can´t find the word in the region");
                System.out.println(regions.get(regionName).text());
            System.out.println(regions.get(regionName).findWords().size() +  " size");
                int i = 0;
                outerloop:
                while (i < regions.get(regionName).findWords().size()) {
                    //obtain the word in the region and get the Levenshtein Distance with the word to find
                    LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
                    int distance = LevenshteinDistance.getDefaultInstance().apply(regions.get(regionName).findWords().get(i).text(), word);
                    //System.out.println("Distance: " + distance + " Word: " + regions.get(regionName).findWords().get(i).text() + " Word to find: " + word);



                    //if the word is found in the region
                    if (distance < 2) {
                        //click in the word
                        regions.put(word, regions.get(regionName).findWords().get(i));
                        //System.out.println("Click in the word: " + regions.get(regionName).text().split(" ")[i]);
                        System.out.println("Click in the word : " + regions.get(regionName).findWords().get(i).getText());
                        regions.get(word).highlight(.1, "red").click();
                        break outerloop;
                    }
                    i++;
                }
            }

            // while scan if the wotd is found in the region







            /*System.out.println(regions.get(regionName).findWord(word));
            System.out.println(regions.get(regionName).findWords().get(0).getText());
            System.out.println(regions.get(regionName).text());
            regions.get(regionName).findWord(word).highlight(1,"red");*/

        }
    public static  void findWordAndCreateRegion(String regionName, String word) throws FindFailed {

        try {
            regions.get(regionName).findWord(word).getText().equals(word);
            regions.put(word, regions.get(regionName).findWord(word));
            regions.get(word).highlight(1,"red");
        } catch (Exception e) {
            System.out.println("Can´t find the word in the region");
            //System.out.println(regions.get(regionName).text());
            System.out.println(regions.get(regionName).findWords().size() +  " size");
            int i = 0;
            outerloop:
            while (i < regions.get(regionName).findWords().size()) {
                //obtain the word in the region and get the Levenshtein Distance with the word to find
                LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
                int distance = LevenshteinDistance.getDefaultInstance().apply(regions.get(regionName).findWords().get(i).text(), word);
                //System.out.println("Distance: " + distance + " Word: " + regions.get(regionName).findWords().get(i).text() + " Word to find: " + word);

                //if the word is found in the region
                if (distance < 2) {
                    //click in the word
                    regions.put(word, regions.get(regionName).findWords().get(i));
                    //System.out.println("Click in the word: " + regions.get(regionName).text().split(" ")[i]);
                    System.out.println("Click in the word : " + regions.get(regionName).findWords().get(i).getText());
                    regions.get(word).highlight(2, "red");
                    break outerloop;
                }
                i++;
            } if (regions.get(word) == null) {
                regions.put(word, regions.get(regionName)
                );
                System.out.println("The word is not found");
            }
        }

        // while scan if the wotd is found in the region







            /*System.out.println(regions.get(regionName).findWord(word));
            System.out.println(regions.get(regionName).findWords().get(0).getText());
            System.out.println(regions.get(regionName).text());
            regions.get(regionName).findWord(word).highlight(1,"red");*/

    }



    }



        /* //Press ALT + TAB in loop for focus on the next window and get the name of the window
       public static void  switchApp(Screen screen, String text) throws FindFailed {
            try {

                App app = new App(text);
                System.out.println("App name: " + app.getName());

                if (app.getName().contains(text)) {
                    System.out.println("Window found" + app);
                    app.focus();

                } else { System.out.println("Window not found");
                }
                System.out.println("Window name: " + app);
            } catch (Exception e) {
                //FormatLog.imprimirFallo("Error al crear region");
            }
        }*/


























