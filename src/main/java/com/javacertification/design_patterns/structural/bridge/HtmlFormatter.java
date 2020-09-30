package com.javacertification.design_patterns.structural.bridge;

import java.util.List;

/**
 * Created by Favio on 12/11/2017.
 */
public class HtmlFormatter implements Formatter {


    @Override
    public String format(String header, List<Detail> details) {
        StringBuilder builder = new StringBuilder();
        builder.append("<table>");
        builder.append("<th>");
        builder.append("Classification");
        builder.append("</th>");
        builder.append("<th>");
        builder.append(header);
        builder.append("</th>");

        for (Detail detail : details) {
            builder.append("<tr><td>");
            builder.append(detail.getLabel());
            builder.append("</td></td>");
            builder.append(detail.getValue());
            builder.append("</tr><8td>");
        }
        builder.append("</table>");

        return builder.toString();
    }
}
