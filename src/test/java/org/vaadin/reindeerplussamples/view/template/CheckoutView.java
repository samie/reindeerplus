package org.vaadin.reindeerplussamples.view.template;

import org.vaadin.reindeerplus.component.*;
import org.vaadin.reindeerplus.component.field.ExpirationDateField;
import org.vaadin.reindeerplus.theme.ButtonTheme;
import org.vaadin.reindeerplus.theme.InputTheme;
import org.vaadin.reindeerplus.theme.RadioButtonTheme;
import org.vaadin.reindeerplus.utility.Breakpoint;
import org.vaadin.reindeerplussamples.view.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@PageTitle("Checkout")
@Route(value = "checkout", layout = MainLayout.class)
public class CheckoutView extends Main {

    public CheckoutView() {
        addClassNames(BoxSizing.BORDER, Display.FLEX, FlexDirection.COLUMN, FlexDirection.Breakpoint.Medium.ROW,
                Margin.Horizontal.AUTO, MaxWidth.SCREEN_LARGE);
        add(createForm(), createSummary());
    }

    public static LocalDate getNextBusinessDay(LocalDate date) {
        if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
            date = date.plusDays(2);
        } else if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            date = date.plusDays(1);
        }
        return date;
    }

    private Component createForm() {
        Layout layout = new Layout(createShippingInformation(), createDeliveryMethod(), createPaymentInformation());
        layout.addClassNames(Padding.LARGE);
        layout.setBoxSizing(Layout.BoxSizing.BORDER);
        layout.setFlexDirection(Layout.FlexDirection.COLUMN);
        return layout;
    }

    private Component createShippingInformation() {
        H2 title = new H2("Shipping information");
        title.addClassNames(FontSize.XLARGE, Margin.Bottom.SMALL, Margin.Top.MEDIUM);

        TextField address = new TextField("Address");
        TextField city = new TextField("City");
        ComboBox<String> state = new ComboBox<>("State");
        TextField zip = new TextField("ZIP");

        TextField phone = new TextField("Phone");
        phone.setPrefixComponent(MaterialSymbol.PHONE.create());

        EmailField email = new EmailField("Email");
        email.setPrefixComponent(MaterialSymbol.EMAIL.create());

        Layout layout = new Layout(title, address, city, state, zip, phone, email);
        // Viewport < 1024px
        layout.setFlexDirection(Layout.FlexDirection.COLUMN);
        // Viewport > 1024px
        layout.setDisplay(Breakpoint.LARGE, Layout.Display.GRID);
        layout.setColumns(Layout.GridColumns.COLUMNS_4);
        layout.setColumnGap(Layout.Gap.MEDIUM);
        layout.setColumnSpan(Layout.ColumnSpan.COLUMN_SPAN_2, city, phone, email);
        layout.setColumnSpan(Layout.ColumnSpan.COLUMN_SPAN_FULL, title, address);
        return layout;
    }

    private Component createDeliveryMethod() {
        H2 title = new H2("Delivery method");
        title.addClassNames(FontSize.XLARGE, Margin.Bottom.SMALL, Margin.Top.XLARGE);

        RadioButtonGroup<DeliveryMethod> deliveryMethod = new RadioButtonGroup<>("Delivery method");
        deliveryMethod.addThemeNames(RadioButtonTheme.EQUAL_WIDTH, RadioButtonTheme.TOGGLE);
        deliveryMethod.setItems(DeliveryMethod.values());
        deliveryMethod.setRenderer(new ComponentRenderer<>(this::renderDeliveryMethod));
        deliveryMethod.setValue(DeliveryMethod.STANDARD);

        Layout layout = new Layout(title, deliveryMethod);
        layout.setFlexDirection(Layout.FlexDirection.COLUMN);
        return layout;
    }

    private Component renderDeliveryMethod(DeliveryMethod method) {
        Span name = new Span(method.getDisplayName());
        name.addClassNames(FontWeight.MEDIUM);

        Span cost = new Span();

        Span date = new Span();
        date.addClassNames(FontSize.SMALL, TextColor.SECONDARY);

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, MMM d, yyyy", Locale.ENGLISH);

        switch (method) {
            case EXPRESS:
                cost.setText("42,00 €");
                date.setText(getNextBusinessDay(today.plusDays(2)).format(formatter));
                break;
            case STANDARD:
            default:
                cost.setText("Free");
                date.setText(getNextBusinessDay(today.plusDays(7)).format(formatter));
                break;
        }

        Span primary = new Span(name, cost);
        primary.addClassNames(Display.FLEX, FlexWrap.WRAP, JustifyContent.BETWEEN);

        Span span = new Span(primary, date);
        span.addClassNames(Display.FLEX, Flex.GROW, FlexDirection.COLUMN,
                Gap.XSMALL, Padding.SMALL);
        return span;
    }

    private Component createPaymentInformation() {
        H2 title = new H2("Payment information");
        title.addClassNames(FontSize.XLARGE, Margin.Bottom.SMALL, Margin.Top.XLARGE);

        RadioButtonGroup<PaymentMethod> paymentMethod = new RadioButtonGroup<>("Payment method");
        paymentMethod.addThemeNames(RadioButtonTheme.EQUAL_WIDTH, RadioButtonTheme.TOGGLE);
        paymentMethod.setItems(PaymentMethod.values());
        paymentMethod.setRenderer(new ComponentRenderer<>(this::renderPaymentMethod));
        paymentMethod.setValue(PaymentMethod.CREDIT_CARD);

        TextField creditCard = new TextField("Card number");
        ExpirationDateField expiration = new ExpirationDateField("Expiration date");
        TextField securityCode = new TextField("Security code");

        Layout layout = new Layout(title, paymentMethod, creditCard, expiration, securityCode);
        // Viewport < 1024px
        layout.setFlexDirection(Layout.FlexDirection.COLUMN);
        // Viewport > 1024px
        layout.setDisplay(Breakpoint.LARGE, Layout.Display.GRID);
        layout.setColumns(Layout.GridColumns.COLUMNS_2);
        layout.setColumnGap(Layout.Gap.MEDIUM);
        layout.setColumnSpan(Layout.ColumnSpan.COLUMN_SPAN_FULL, title, paymentMethod, creditCard);
        return layout;
    }

    private Component renderPaymentMethod(PaymentMethod method) {
        return switch (method) {
            case APPLE_PAY ->
                    createImage("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b0/Apple_Pay_logo.svg/2560px-Apple_Pay_logo.svg.png", method.getDisplayName());
            case PAYPAL ->
                    createImage("https://upload.wikimedia.org/wikipedia/commons/b/b5/PayPal.svg", method.getDisplayName());
            case GOOGLE_PAY ->
                    createImage("https://upload.wikimedia.org/wikipedia/commons/f/f2/Google_Pay_Logo.svg", method.getDisplayName());
            default -> createCreditCard();
        };
    }

    private Component createCreditCard() {
        Image visa = new Image("https://upload.wikimedia.org/wikipedia/commons/d/d6/Visa_2021.svg", "Visa");
        visa.setHeight(1, Unit.REM);

        Image mastercard = new Image("https://upload.wikimedia.org/wikipedia/commons/a/a4/Mastercard_2019_logo.svg", "Mastercard");
        mastercard.setHeight(2, Unit.REM);

        Layout layout = new Layout(visa, mastercard);
        layout.addClassNames(Margin.Horizontal.AUTO, Padding.SMALL);
        layout.setAlignItems(Layout.AlignItems.CENTER);
        layout.setGap(Layout.Gap.SMALL);
        return layout;
    }

    private Component createImage(String src, String alt) {
        Image img = new Image(src, alt);
        img.setHeight(2, Unit.REM);

        Layout layout = new Layout(img);
        layout.addClassNames(Margin.Horizontal.AUTO, Padding.SMALL);
        layout.setAlignItems(Layout.AlignItems.CENTER);
        return layout;
    }

    private Component createSummary() {
        H2 title = new H2("Order summary");
        title.addClassNames(FontSize.XLARGE);

        KeyValuePairs pairs = new KeyValuePairs(
                new KeyValuePair("Subtotal", "3 950,00 €"),
                new KeyValuePair("Delivery", "0,00 €"),
                new KeyValuePair("Total", "3 950,00 €")
        );
        pairs.addClassNames(Divide.Y);
        pairs.setKeyWidthFull();
        pairs.removeBackgroundColor();
        pairs.removeHorizontalPadding();

        TextField code = new TextField("Enter a promo code");
        code.addClassNames(Flex.GROW);
        code.addThemeName(InputTheme.OUTLINE);

        Button apply = new Button("Apply");
        apply.addClassNames(Background.BASE);
        apply.addThemeName(ButtonTheme.OUTLINE);

        InputGroup inputGroup = new InputGroup(code, apply);

        RouterLink confirmOrder = new RouterLink("Confirm order", ShoppingCartView.class);
        confirmOrder.addClassNames(AlignItems.CENTER, Background.PRIMARY,
                BorderRadius.MEDIUM, Display.FLEX, FontWeight.SEMIBOLD,
                Height.MEDIUM, JustifyContent.CENTER, TextColor.PRIMARY_CONTRAST);

        Layout layout = new Layout(title, pairs, inputGroup, confirmOrder);
        layout.addClassNames(Background.CONTRAST_5, BorderRadius.LARGE, Padding.LARGE);
        layout.setBoxSizing(Layout.BoxSizing.BORDER);
        layout.setFlexDirection(Layout.FlexDirection.COLUMN);
        layout.setGap(Layout.Gap.MEDIUM);

        Section section = new Section(layout);
        section.addClassNames(BoxSizing.BORDER, Padding.LARGE);
        section.setMinWidth(24, Unit.REM);
        return section;
    }

    public enum DeliveryMethod {
        STANDARD("Standard delivery"),
        EXPRESS("Express delivery");

        private final String displayName;

        DeliveryMethod(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    public enum PaymentMethod {
        CREDIT_CARD("Credit card"),
        PAYPAL("PayPal"),
        APPLE_PAY("Apple Pay"),
        GOOGLE_PAY("Google Pay");

        private final String displayName;

        PaymentMethod(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

}
