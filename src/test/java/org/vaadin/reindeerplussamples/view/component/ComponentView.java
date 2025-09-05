package org.vaadin.reindeerplussamples.view.component;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.vaadin.addons.reindeerplus.component.Preview;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.theme.lumo.LumoUtility.*;

import java.util.HashMap;
import java.util.Map;

public class ComponentView extends Main {

    public ComponentView() {
        addClassNames(Display.FLEX, FlexDirection.COLUMN, Padding.Bottom.LARGE, Padding.Horizontal.LARGE);
    }

    public void addH2(String text) {
        H2 h2 = new H2(text);
        h2.addClassNames(FontSize.LARGE, Margin.Bottom.MEDIUM, Margin.Top.LARGE);
        add(h2);
    }

    public void addH2(String text, String description) {
        H2 h2 = new H2(text);
        h2.addClassNames(FontSize.LARGE, Margin.Top.LARGE);

        Paragraph paragraph = new Paragraph(description);
        paragraph.addClassNames(FontSize.SMALL, Margin.Bottom.MEDIUM, Margin.Top.NONE, TextColor.SECONDARY);

        add(h2, paragraph);
    }

    public void addPreview(Component... components) {
        add(new Preview(components));
    }

    /**
     * Adds a preview with component and its code sample.
     *
     * @param component The component to preview
     * @param methodName The name of the method that creates the component
     */
    public void addPreview(Component component, String methodName) {
        HorizontalLayout layout;
        add(layout = new HorizontalLayout(new Preview(component)));
        layout.setWidthFull();
        layout.setWrap(true);
    }
}
