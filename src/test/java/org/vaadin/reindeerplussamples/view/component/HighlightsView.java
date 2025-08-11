package org.vaadin.reindeerplussamples.view.component;

import org.vaadin.reindeerplus.component.*;
import org.vaadin.reindeerplus.utility.Breakpoint;
import org.vaadin.reindeerplus.utility.Color;
import org.vaadin.reindeerplussamples.view.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility.*;


@PageTitle("Highlights")
@Route(value = "highlights", layout = MainLayout.class)
public class HighlightsView extends ComponentView {

    public static final String ORDERS = "Orders";
    public static final String ORDERS_VALUE = "42,719";
    public static final String ORDERS_CHANGE = "16.38%";
    public static final String ORDERS_WEEKLY = "+17K this week";
    public static final String SALES = "Sales";
    public static final String SALES_VALUE = "€546,110.70";
    public static final String SALES_CHANGE = "40.82%";
    public static final String SALES_WEEKLY = "+34K this week";
    public static final String VISITORS = "Visitors";
    public static final String VISITORS_VALUE = "62,806";
    public static final String VISITORS_CHANGE = "13.35%";
    public static final String VISITORS_WEEKLY = "+26K this week";
    public static final String RATING = "Rating";
    public static final String RATING_VALUE = "96.7%";

    public HighlightsView() {
        addH2("Basic");
        addPreview(createHighlights());

        addH2("Prefix");
        addPreview(createHighlightsWithPrefix());

        addH2("Details");
        addPreview(createHighlightsWithDetails());

        addH2("Suffix");
        addPreview(createHighlightsWithSuffix());

        addH2("Prefix, details & suffix");
        addPreview(createHighlightsWithPrefixDetailsSuffix());

        addH2("Breakpoint");
        addPreview(createHighlightsWithBreakpoint());

        addH2("Gap");
        addPreview(createHighlightsWithGap());

        addH2("Grid");
        addPreview(createGridHighlights());

        addH2("Utility class: divide-x");
        Highlights highlights = createHighlights();
        highlights.addClassNames(Divide.X);
        addPreview(highlights);
    }

    private Highlights createHighlights() {
        return new Highlights(
                new Highlight(ORDERS, ORDERS_VALUE),
                new Highlight(SALES, SALES_VALUE),
                new Highlight(VISITORS, VISITORS_VALUE)
        );
    }

    private Highlights createHighlightsWithPrefix() {
        Highlights highlights = new Highlights();

        Highlight highlight = new Highlight(
                createIcon(MaterialSymbol.DEPLOYED_CODE, Color.Background.PRIMARY_10, Color.Text.PRIMARY),
                ORDERS, ORDERS_VALUE
        );
        highlights.add(highlight);

        highlight = new Highlight(
                createIcon(MaterialSymbol.BAR_CHART, Color.Background.SUCCESS_10, Color.Text.SUCCESS),
                SALES, SALES_VALUE
        );
        highlights.add(highlight);

        highlight = new Highlight(
                createIcon(MaterialSymbol.PERSON, Color.Background.ERROR_10, Color.Text.ERROR),
                VISITORS, VISITORS_VALUE
        );
        highlights.add(highlight);

        return highlights;
    }

    private Component createIcon(MaterialSymbol symbol, Color.Background background, Color.Text color) {
        Layout container = new Layout(symbol.create(IconSize.LARGE));
        container.addClassNames(background.getClassName(), BorderRadius.LARGE, Height.XLARGE, color.getClassName(),
                Width.XLARGE);
        container.setAlignItems(Layout.AlignItems.CENTER);
        container.setJustifyContent(Layout.JustifyContent.CENTER);
        return container;
    }

    private Highlights createHighlightsWithDetails() {
        Highlights highlights = new Highlights();

        Highlight highlight = new Highlight(ORDERS, ORDERS_VALUE);
        highlight.setDetails(
                new Tag(MaterialSymbol.ARROW_UPWARD_ALT, ORDERS_CHANGE, Color.Text.SUCCESS),
                new Tag(ORDERS_WEEKLY)
        );
        highlights.add(highlight);

        highlight = new Highlight(SALES, SALES_VALUE);
        highlight.setDetails(
                new Tag(MaterialSymbol.ARROW_UPWARD_ALT, SALES_CHANGE, Color.Text.SUCCESS),
                new Tag(SALES_WEEKLY)
        );
        highlights.add(highlight);

        highlight = new Highlight(VISITORS, VISITORS_VALUE);
        highlight.setDetails(
                new Tag(MaterialSymbol.ARROW_UPWARD_ALT, VISITORS_CHANGE, Color.Text.SUCCESS),
                new Tag(VISITORS_WEEKLY)
        );
        highlights.add(highlight);

        return highlights;
    }

    private Highlights createHighlightsWithSuffix() {
        Highlights highlights = new Highlights();

        Highlight highlight = new Highlight(ORDERS, ORDERS_VALUE, createSuffix(ORDERS));
        highlights.add(highlight);

        highlight = new Highlight(SALES, SALES_VALUE, createSuffix(SALES));
        highlights.add(highlight);

        highlight = new Highlight(VISITORS, VISITORS_VALUE, createSuffix(VISITORS));
        highlights.add(highlight);

        return highlights;
    }

    private RouterLink createSuffix(String label) {
        RouterLink link = new RouterLink("", HighlightsView.class);
        link.add(MaterialSymbol.ARROW_RIGHT_ALT.create(IconSize.SMALL, TextColor.SECONDARY));
        link.addClassNames(AlignItems.CENTER, Display.FLEX, Height.MEDIUM, JustifyContent.CENTER, Width.MEDIUM);
        link.getElement().setAttribute("aria-label", label);
        link.getElement().setAttribute("title", label);
        return link;
    }

    private Highlights createHighlightsWithPrefixDetailsSuffix() {
        Highlights highlights = new Highlights();

        Highlight highlight = new Highlight(
                createIcon(MaterialSymbol.DEPLOYED_CODE, Color.Background.PRIMARY_10, Color.Text.PRIMARY),
                ORDERS, ORDERS_VALUE, createSuffix(ORDERS)
        );
        highlight.setDetails(
                new Tag(MaterialSymbol.ARROW_UPWARD_ALT, ORDERS_CHANGE, Color.Text.SUCCESS),
                new Tag(ORDERS_WEEKLY)
        );
        highlights.add(highlight);

        highlight = new Highlight(
                createIcon(MaterialSymbol.BAR_CHART, Color.Background.SUCCESS_10, Color.Text.SUCCESS),
                SALES, SALES_VALUE, createSuffix(SALES)
        );
        highlight.setDetails(
                new Tag(MaterialSymbol.ARROW_UPWARD_ALT, SALES_CHANGE, Color.Text.SUCCESS),
                new Tag(SALES_WEEKLY)
        );
        highlights.add(highlight);

        highlight = new Highlight(
                createIcon(MaterialSymbol.PERSON, Color.Background.ERROR_10, Color.Text.ERROR),
                VISITORS, VISITORS_VALUE, createSuffix(VISITORS)
        );
        highlight.setDetails(
                new Tag(MaterialSymbol.ARROW_UPWARD_ALT, VISITORS_CHANGE, Color.Text.SUCCESS),
                new Tag(VISITORS_WEEKLY)
        );
        highlights.add(highlight);

        return highlights;
    }

    private Highlights createGridHighlights() {
        Highlights highlights = createHighlights();

        // Add a fourth item for a nice 2x2 grid
        highlights.add(new Highlight(RATING, RATING_VALUE));

        highlights.setDisplay(Layout.Display.GRID);
        highlights.setColumns(Layout.GridColumns.COLUMNS_2);
        return highlights;
    }

    private Component[] createHighlightsWithBreakpoint() {
        Highlights highlights = createHighlights();

        RadioButtonGroup<Breakpoint> rbc = new RadioButtonGroup<>("Breakpoint", Breakpoint.values());
        rbc.addThemeVariants(RadioGroupVariant.LUMO_HELPER_ABOVE_FIELD);
        rbc.addValueChangeListener(event -> highlights.setBreakpoint(event.getValue()));
        rbc.setHelperText("Resize the browser to see the difference");
        rbc.setItemLabelGenerator((ItemLabelGenerator<Breakpoint>) item -> switch (item) {
            case SMALL -> "S";
            default -> "M";
            case LARGE -> "L";
            case XLARGE -> "XL";
            case XXLARGE -> "XXL";
        });

        return new Component[]{rbc, highlights};
    }

    private Component[] createHighlightsWithGap() {
        Highlights highlights = createHighlights();

        RadioButtonGroup<Layout.Gap> rbc = new RadioButtonGroup<>("Gap", Layout.Gap.values());
        rbc.setItemLabelGenerator((ItemLabelGenerator<Layout.Gap>) item -> switch (item) {
            case XSMALL -> "XS";
            case SMALL -> "S";
            default -> "M";
            case LARGE -> "L";
            case XLARGE -> "XL";
        });
        rbc.addValueChangeListener(event -> highlights.setGap(event.getValue()));

        return new Component[]{rbc, highlights};
    }

}
