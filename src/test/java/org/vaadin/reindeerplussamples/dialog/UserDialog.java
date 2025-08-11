package org.vaadin.reindeerplussamples.dialog;

import org.vaadin.reindeerplus.component.Item;
import org.vaadin.reindeerplus.component.MaterialSymbol;
import org.vaadin.reindeerplus.theme.RadioButtonTheme;
import org.vaadin.reindeerplussamples.view.template.ProfileView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.popover.Popover;
import com.vaadin.flow.component.popover.PopoverVariant;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.theme.lumo.LumoUtility.*;


public class UserDialog extends Popover {

    private String colorScheme = "";
    private String density = "";
    private String theme = "";

    public UserDialog() {
        addThemeVariants(PopoverVariant.ARROW);
        setAriaLabel("User menu");
        setWidth("16rem");

        // Links
        UnorderedList list = new UnorderedList(
                createListItem("Manage account", MaterialSymbol.ACCOUNT_CIRCLE, ProfileView.class),
                createListItem("Sign out", MaterialSymbol.LOGOUT, ProfileView.class)
        );
        list.addClassNames(ListStyleType.NONE, Margin.Vertical.NONE, Padding.XSMALL);

        // Divider
        Hr hr = new Hr();
        hr.addClassNames(Margin.Vertical.XSMALL);

        // Theme
        RadioButtonGroup<String> colorScheme = new RadioButtonGroup<>();
        colorScheme.addClassNames(BoxSizing.BORDER, Padding.XSMALL);
        colorScheme.addThemeNames(RadioButtonTheme.EQUAL_WIDTH, RadioButtonTheme.PRIMARY, RadioButtonTheme.TOGGLE);
        colorScheme.addValueChangeListener(e -> setColorScheme(e.getValue().equals(Lumo.DARK)));

        colorScheme.setAriaLabel("Color scheme");
        colorScheme.setTooltipText("Color scheme");

        colorScheme.setItems(Lumo.LIGHT, Lumo.DARK);
        colorScheme.setRenderer(new ComponentRenderer<>(this::renderColorScheme));
        colorScheme.setValue(Lumo.LIGHT);
        colorScheme.setWidthFull();

        // Density
        RadioButtonGroup<String> density = new RadioButtonGroup<>();
        density.addClassNames(BoxSizing.BORDER, Padding.XSMALL);
        density.addThemeNames(RadioButtonTheme.EQUAL_WIDTH, RadioButtonTheme.PRIMARY, RadioButtonTheme.TOGGLE);
        density.addValueChangeListener(e -> setDensity(e.getValue().equals("Compact")));

        density.setAriaLabel("Density");
        density.setTooltipText("Density");

        density.setItems("Default", "Compact");
        density.setRenderer(new ComponentRenderer<>(this::renderDensity));
        density.setValue("Default");
        density.setWidthFull();

        // Theme
        RadioButtonGroup<String> theme = new RadioButtonGroup<>();
        theme.addClassNames(BoxSizing.BORDER, Padding.XSMALL);
        theme.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        theme.addValueChangeListener(e -> setTheme(e.getValue()));

        theme.setAriaLabel("Theme");
        theme.setTooltipText("Theme");

        theme.setItems("Lumo", "Material", "Carbon", "Radix");
        theme.setValue("Lumo");
        theme.setWidthFull();

        add(list, hr, colorScheme, density, theme);
    }

    private ListItem createListItem(String text, MaterialSymbol symbol, Class<? extends Component> navigationTarget) {
        Item item = new Item(text, symbol);
        item.addClassNames(BorderRadius.MEDIUM, LineHeight.XSMALL, Padding.SMALL, "hover:bg-contrast-5");

        RouterLink link = new RouterLink(navigationTarget);
        link.addClassNames(TextColor.BODY, "no-underline");
        link.add(item);

        return new ListItem(link);
    }

    private Component renderColorScheme(String theme) {
        String text = theme.substring(0, 1).toUpperCase() + theme.substring(1);
        MaterialSymbol symbol = theme.equals(Lumo.DARK) ? MaterialSymbol.DARK_MODE : MaterialSymbol.LIGHT_MODE;

        Item item = new Item(text, symbol);
        item.addClassNames(Margin.Horizontal.AUTO);
        return item;
    }

    private void setColorScheme(boolean dark) {
        this.colorScheme = dark ? Lumo.DARK : Lumo.LIGHT;
        updateTheme();
    }

    private Component renderDensity(String density) {
        MaterialSymbol symbol = density.equals("Default") ? MaterialSymbol.DENSITY_MEDIUM : MaterialSymbol.DENSITY_SMALL;

        Item item = new Item(density, symbol);
        item.addClassNames(Margin.Horizontal.AUTO);
        return item;
    }

    private void setDensity(boolean compact) {
        this.density = compact ? "compact" : "";
        updateTheme();
    }

    private void setTheme(String theme) {
        this.theme = theme.toLowerCase();
        updateTheme();
    }

    private void updateTheme() {
        var js = "document.documentElement.setAttribute('theme', $0)";
        getElement().executeJs(js, this.colorScheme + " " + this.density + " " + this.theme);
    }

}
