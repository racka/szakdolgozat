package com.nooon.szakdolgozat.client.mvp.model.visualprocess;


import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Widget;
import com.nooon.szakdolgozat.client.mvp.model.sitecallback.SiteCallback;

/**
 * @author racka
 */
public class FadeAnimation extends SiteAnimation {

    private RGB startColor;
    private RGB targetColor;

    private Style widgetStyle;


    public FadeAnimation(AbsolutePanel container, Widget target, SiteCallback callback, int duration,
                         String targetColor) {
        super(container, target, callback, duration);

        widgetStyle = target.getElement().getStyle();
        this.targetColor = new RGB(targetColor);
        this.startColor = new RGB(widgetStyle.getBackgroundColor());
    }

    @Override
    public void animationLogic(double progress) {
        double fineTune = this.interpolate(progress);

        int actualRed = (int)Math.round(startColor.getRed() + fineTune * (targetColor.getRed() - startColor.getRed()));
        int actualGreen = (int)Math.round(startColor.getGreen() + fineTune * (targetColor.getGreen() - startColor.getGreen()));
        int actualBlue = (int)Math.round(startColor.getBlue() + fineTune * (targetColor.getBlue() - startColor.getBlue()));

        widgetStyle.setBackgroundColor(new RGB(actualRed, actualGreen, actualBlue).toString());
    }

    private static class RGB {
        private int red;
        private int green;
        private int blue;

        private RGB(int red, int green, int blue) {
            this.red = red;
            this.green = green;
            this.blue = blue;
        }


        private RGB(String color) {
            if (color.matches("rgb\\(\\s*[0-9]+\\s*,\\s*[0-9]+\\s*,\\s*[0-9]+\\s*\\)")) {
                color = color.replace(" ", "").replace("rgb(", "").replace(")", "");
                String[] rgb = color.split(",");
                this.red = Integer.parseInt(rgb[0]);
                this.green = Integer.parseInt(rgb[1]);
                this.blue = Integer.parseInt(rgb[2]);
            }
        }

        public String toString() {
            return "rgb(" + red + "," + green + "," + blue + ")";
        }

        public int getRed() {
            return red;
        }

        public int getGreen() {
            return green;
        }

        public int getBlue() {
            return blue;
        }
    }

}
