package com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response;
/**
 * Created by Ahmed Elnoor
 */

/**
 * All information contained herein is, and remains the property of Payoneer.
 * Unauthorized use, duplication, reverse engineering, any form of redistribution,
 * or use in part or in whole requires prior, express, printed and signed permission from Payoneer.
 *
 * Applicable Model Class
 *
 * @author Ahmed Elnoor
 * @version 0.1, 05-08-2021
 * @authorAccount https://github.com/a7medelnoor
 */
public class Applicable {
    private String label;
    private Links links;

    public Applicable() {
    }

    public Applicable(String label, Links links) {
        this.label = label;
        this.links = links;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "Applicable{" +
                "label='" + label + '\'' +
                ", links=" + links +
                '}';
    }
}